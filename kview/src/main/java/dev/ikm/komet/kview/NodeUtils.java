package dev.ikm.komet_test.kview;

import javafx.scene.Node;

public class NodeUtils {
    public static void setShowing(Node node, boolean showing) {
        node.setVisible(showing);
        node.setManaged(showing);
    }
}