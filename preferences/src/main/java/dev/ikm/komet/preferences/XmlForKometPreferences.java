/*
 * Copyright © 2015 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.komet_test.preferences;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * TODO: Copied XmlSupport content from java.tools.prefs because they where package private.
 * Consider relicensing this module under standard java classpath exception clause, or replace
 * XmlSupport with newly developed content.
 * 
 */
public class XmlForKometPreferences {
    // The required DTD URI for exported preferences
    private static final String PREFS_DTD_URI =
            "http://java.sun.com/dtd/preferences.dtd";

    // The actual DTD corresponding to the URI
    private static final String PREFS_DTD =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                    "<!-- DTD for preferences -->"               +

                    "<!ELEMENT preferences (root) >"             +
                    "<!ATTLIST preferences"                      +
                    " EXTERNAL_XML_VERSION CDATA \"0.0\"  >"     +

                    "<!ELEMENT root (map, node*) >"              +
                    "<!ATTLIST root"                             +
                    "          type (system|user) #REQUIRED >"   +

                    "<!ELEMENT node (map, node*) >"              +
                    "<!ATTLIST node"                             +
                    "          name CDATA #REQUIRED >"           +

                    "<!ELEMENT map (entry*) >"                   +
                    "<!ATTLIST map"                              +
                    "  MAP_XML_VERSION CDATA \"0.0\"  >"         +
                    "<!ELEMENT entry EMPTY >"                    +
                    "<!ATTLIST entry"                            +
                    "          key CDATA #REQUIRED"              +
                    "          value CDATA #REQUIRED >"          ;
    /**
     * Version number for the format exported preferences files.
     */
    private static final String EXTERNAL_XML_VERSION = "1.0";

    /*
     * Version number for the internal map files.
     */
    private static final String MAP_XML_VERSION = "1.0";

    /**
     * Export the specified preferences node and, if subTree is true, all
     * subnodes, to the specified output stream.  Preferences are exported as
     * an XML document conforming to the definition in the Preferences spec.
     *
     * @throws IOException if writing to the specified output stream
     *         results in an <tt>IOException</tt>.
     * @throws BackingStoreException if preference data cannot be read from
     *         backing store.
     * @throws IllegalStateException if this node (or an ancestor) has been
     *         removed with the {@link Preferences#removeNode()} method.
     */
    static void export(OutputStream os, final Preferences p, boolean subTree)
            throws IOException, BackingStoreException {
        if (((KometPreferencesImpl)p).isRemoved()) {
            throw new IllegalStateException("Node has been removed");
        }
        Document doc = createPrefsDoc("preferences");
        Element preferences =  doc.getDocumentElement() ;
        preferences.setAttribute("EXTERNAL_XML_VERSION", EXTERNAL_XML_VERSION);
        Element xmlRoot =  (Element)
                preferences.appendChild(doc.createElement("root"));
        xmlRoot.setAttribute("type", (p.isUserNode() ? "user" : "system"));

        // Get bottom-up list of nodes from p to root, excluding root
        List<Preferences> ancestors = new ArrayList<>();

        for (Preferences kid = p, dad = kid.parent(); dad != null;
             kid = dad, dad = kid.parent()) {
            ancestors.add(kid);
        }
        Element e = xmlRoot;
        for (int i=ancestors.size()-1; i >= 0; i--) {
            e.appendChild(doc.createElement("map"));
            e = (Element) e.appendChild(doc.createElement("node"));
            e.setAttribute("name", ancestors.get(i).name());
        }
        putPreferencesInXml(e, doc, p, subTree);

        writeDoc(doc, os);
    }

    /**
     * Put the preferences in the specified Preferences node into the
     * specified XML element which is assumed to represent a node
     * in the specified XML document which is assumed to conform to
     * PREFS_DTD.  If subTree is true, create children of the specified
     * XML node conforming to all of the children of the specified
     * Preferences node and recurse.
     *
     * @throws BackingStoreException if it is not possible to read
     *         the preferences or children out of the specified
     *         preferences node.
     */
    private static void putPreferencesInXml(Element elt, Document doc,
                                            Preferences prefs, boolean subTree) throws BackingStoreException
    {
        Preferences[] kidsCopy = null;
        String[] kidNames = null;

        // Node is locked to export its contents and get a
        // copy of children, then lock is released,
        // and, if subTree = true, recursive calls are made on children
        synchronized (((KometPreferencesImpl)prefs).getLock()) {
            // Check if this node was concurrently removed. If yes
            // remove it from XML Document and return.
            if (((KometPreferencesImpl)prefs).isRemoved()) {
                elt.getParentNode().removeChild(elt);
                return;
            }
            // Put map in xml element
            String[] keys = prefs.keys();
            Element map = (Element) elt.appendChild(doc.createElement("map"));
            for (int i=0; i<keys.length; i++) {
                Element entry = (Element)
                        map.appendChild(doc.createElement("entry"));
                entry.setAttribute("key", keys[i]);
                // NEXT STATEMENT THROWS NULL PTR EXC INSTEAD OF ASSERT FAIL
                entry.setAttribute("value", prefs.get(keys[i], null));
            }
            // Recurse if appropriate
            if (subTree) {
                /* Get a copy of kids while lock is held */
                kidNames = prefs.childrenNames();
                kidsCopy = new Preferences[kidNames.length];
                for (int i = 0; i <  kidNames.length; i++) {
                    kidsCopy[i] = prefs.node(kidNames[i]);
                }
            }
            // release lock
        }

        if (subTree) {
            for (int i=0; i < kidNames.length; i++) {
                Element xmlKid = (Element)
                        elt.appendChild(doc.createElement("node"));
                xmlKid.setAttribute("name", kidNames[i]);
                putPreferencesInXml(xmlKid, doc, kidsCopy[i], subTree);
            }
        }
    }

    /**
     * Import preferences from the specified input stream, which is assumed
     * to contain an XML document in the format described in the Preferences
     * spec.
     *
     * @throws IOException if reading from the specified output stream
     *         results in an <tt>IOException</tt>.
     * @throws InvalidPreferencesFormatException Data on input stream does not
     *         constitute a valid XML document with the mandated document type.
     */
    static void importPreferences(InputStream is)
            throws IOException, InvalidPreferencesFormatException
    {
        try {
            Document doc = loadPrefsDoc(is);
            String xmlVersion =
                    doc.getDocumentElement().getAttribute("EXTERNAL_XML_VERSION");
            if (xmlVersion.compareTo(EXTERNAL_XML_VERSION) > 0) {
                throw new InvalidPreferencesFormatException(
                        "Exported preferences file format version " + xmlVersion +
                                " is not supported. This java installation can read" +
                                " versions " + EXTERNAL_XML_VERSION + " or older. You may need" +
                                " to install a newer version of JDK.");
            }

            Element xmlRoot = (Element) doc.getDocumentElement().
                    getChildNodes().item(0);
            Preferences prefsRoot =
                    (xmlRoot.getAttribute("type").equals("user") ?
                            Preferences.userRoot() : Preferences.systemRoot());
            ImportSubtree(prefsRoot, xmlRoot);
        } catch(SAXException e) {
            throw new InvalidPreferencesFormatException(e);
        }
    }

    /**
     * Create a new prefs XML document.
     */
    private static Document createPrefsDoc( String qname ) {
        try {
            DOMImplementation di = DocumentBuilderFactory.newInstance().
                    newDocumentBuilder().getDOMImplementation();
            DocumentType dt = di.createDocumentType(qname, null, PREFS_DTD_URI);
            return di.createDocument(null, qname, dt);
        } catch(ParserConfigurationException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Load an XML document from specified input stream, which must
     * have the requisite DTD URI.
     */
    private static Document loadPrefsDoc(InputStream in)
            throws SAXException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setValidating(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringComments(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setEntityResolver(new Resolver());
            db.setErrorHandler(new EH());
            return db.parse(new InputSource(in));
        } catch (ParserConfigurationException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Write XML document to the specified output stream.
     */
    private static void writeDoc(Document doc, OutputStream out)
            throws IOException
    {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            try {
                tf.setAttribute("indent-number", 2);
            } catch (IllegalArgumentException iae) {
                //Ignore the IAE. Should not fail the writeout even the
                //transformer provider does not support "indent-number".
            }
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            //Transformer resets the "indent" info if the "result" is a StreamResult with
            //an OutputStream object embedded, creating a Writer object on top of that
            //OutputStream object however works.
            t.transform(new DOMSource(doc),
                    new StreamResult(new BufferedWriter(new OutputStreamWriter(out, "UTF-8"))));
        } catch(TransformerException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Recursively traverse the specified preferences node and store
     * the described preferences into the system or current user
     * preferences tree, as appropriate.
     */
    private static void ImportSubtree(Preferences prefsNode, Element xmlNode) {
        NodeList xmlKids = xmlNode.getChildNodes();
        int numXmlKids = xmlKids.getLength();
        /*
         * We first lock the node, import its contents and get
         * child nodes. Then we unlock the node and go to children
         * Since some of the children might have been concurrently
         * deleted we check for this.
         */
        Preferences[] prefsKids;
        /* Lock the node */
        synchronized (((KometPreferencesImpl)prefsNode).getLock()) {
            //If removed, return silently
            if (((KometPreferencesImpl)prefsNode).isRemoved()) {
                return;
            }

            // Import any preferences at this node
            Element firstXmlKid = (Element) xmlKids.item(0);
            ImportPrefs(prefsNode, firstXmlKid);
            prefsKids = new Preferences[numXmlKids - 1];

            // Get involved children
            for (int i=1; i < numXmlKids; i++) {
                Element xmlKid = (Element) xmlKids.item(i);
                prefsKids[i-1] = prefsNode.node(xmlKid.getAttribute("name"));
            }
        } // unlocked the node
        // import children
        for (int i=1; i < numXmlKids; i++) {
            ImportSubtree(prefsKids[i-1], (Element)xmlKids.item(i));
        }
    }

    /**
     * Import the preferences described by the specified XML element
     * (a map from a preferences document) into the specified
     * preferences node.
     */
    private static void ImportPrefs(Preferences prefsNode, Element map) {
        NodeList entries = map.getChildNodes();
        for (int i=0, numEntries = entries.getLength(); i < numEntries; i++) {
            Element entry = (Element) entries.item(i);
            prefsNode.put(entry.getAttribute("key"),
                    entry.getAttribute("value"));
        }
    }

    /**
     * Export the specified Map<String,String> to a map document on
     * the specified OutputStream as per the prefs DTD.  This is used
     * as the internal (undocumented) format for FileSystemPrefs.
     *
     * @throws IOException if writing to the specified output stream
     *         results in an <tt>IOException</tt>.
     */
    static void exportMap(OutputStream os, Map<String, String> map) throws IOException {
        Document doc = createPrefsDoc("map");
        Element xmlMap = doc.getDocumentElement( ) ;
        xmlMap.setAttribute("MAP_XML_VERSION", MAP_XML_VERSION);

        for (Iterator<Map.Entry<String, String>> i = map.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<String, String> e = i.next();
            Element xe = (Element)
                    xmlMap.appendChild(doc.createElement("entry"));
            xe.setAttribute("key",   e.getKey());
            xe.setAttribute("value", e.getValue());
        }

        writeDoc(doc, os);
    }

    /**
     * Import Map from the specified input stream, which is assumed
     * to contain a map document as per the prefs DTD.  This is used
     * as the internal (undocumented) format for FileSystemPrefs.  The
     * key-value pairs specified in the XML document will be put into
     * the specified Map.  (If this Map is empty, it will contain exactly
     * the key-value pairs int the XML-document when this method returns.)
     *
     * @throws IOException if reading from the specified output stream
     *         results in an <tt>IOException</tt>.
     * @throws InvalidPreferencesFormatException Data on input stream does not
     *         constitute a valid XML document with the mandated document type.
     */
    static void importMap(InputStream is, Map<String, String> m)
            throws IOException, InvalidPreferencesFormatException
    {
        try {
            Document doc = loadPrefsDoc(is);
            Element xmlMap = doc.getDocumentElement();
            // check version
            String mapVersion = xmlMap.getAttribute("MAP_XML_VERSION");
            if (mapVersion.compareTo(MAP_XML_VERSION) > 0) {
                throw new InvalidPreferencesFormatException(
                        "Preferences map file format version " + mapVersion +
                                " is not supported. This java installation can read" +
                                " versions " + MAP_XML_VERSION + " or older. You may need" +
                                " to install a newer version of JDK.");
            }

            NodeList entries = xmlMap.getChildNodes();
            for (int i=0, numEntries=entries.getLength(); i<numEntries; i++) {
                Element entry = (Element) entries.item(i);
                m.put(entry.getAttribute("key"), entry.getAttribute("value"));
            }
        } catch(SAXException e) {
            throw new InvalidPreferencesFormatException(e);
        }
    }

    private static class Resolver implements EntityResolver {
        @Override
        public InputSource resolveEntity(String pid, String sid)
                throws SAXException
        {
            if (sid.equals(PREFS_DTD_URI)) {
                InputSource is;
                is = new InputSource(new StringReader(PREFS_DTD));
                is.setSystemId(PREFS_DTD_URI);
                return is;
            }
            throw new SAXException("Invalid system identifier: " + sid);
        }
    }

    private static class EH implements ErrorHandler {
        @Override
        public void error(SAXParseException x) throws SAXException {
            throw x;
        }
        @Override
        public void fatalError(SAXParseException x) throws SAXException {
            throw x;
        }
        @Override
        public void warning(SAXParseException x) throws SAXException {
            throw x;
        }
    }
}
