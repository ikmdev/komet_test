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
package dev.ikm.komet_test.framework;


/**
 * CSS Style Classes for JavaFX nodes.
 *
 * 
 */
public enum StyleClasses {
    AND_CLAUSE, OR_CLAUSE, AND_CLAUSE_CHILD, OR_CLAUSE_CHILD,
    COMPONENT_BADGE,
    COMPONENT_PANEL,
    COMPONENT_COLLAPSIBLE_PANEL,
    COMPONENT_VERSION_PANEL,
    COMPONENT_VERSION_BORDER_PANEL,
    STAMP_LABEL,


    CONCEPT_DETAIL_PANE,
    COMPONENT_DETAIL_BACKGROUND, COMPONENT_VERSION_WHAT_CELL,
    COMPONENT_TEXT, VERSION_PANEL, HEADER_PANEL, HEADER_TEXT, ADD_DESCRIPTION_BUTTON,
    ADD_ATTACHMENT, STAMP_INDICATOR, VERSION_GRAPH_TOGGLE, ADD_TAB_MENU_BUTTON,
    MULTI_PARENT_TREE_NODE, MULTI_PARENT_TREE_CELL, TOP_GRID_PANE, EDIT_COMPONENT_BUTTON, HBOX, ASSEMBLAGE_DETAIL,
    // TODO change CONCEPT_LABEL to ENTITY_LABEL

    CONCEPT_LABEL, ASSEMBLAGE_NAME_TEXT, DEFINITION_TEXT, CONCEPT_TEXT, SEMANTIC_TEXT, ERROR_TEXT,
    DATE_TEXT, CONCEPT_COMPONENT_REFERENCE, SEMANTIC_COMPONENT_REFERENCE, CANCEL_BUTTON, COMMIT_BUTTON,
    ALERT_PANE, ALERT_TITLE, ALERT_DESCRIPTION, MORE_ALERT_DETAILS, ALERT_ICON,
    PROP_SHEET_ENTITY_LABEL,
    PROP_SHEET_PROPERTY_NAME,

    DEF_ROOT, DEF_ROLE, DEF_ROLE_GROUP, DEF_CONCEPT, DEF_EMBEDDED_CONCEPT, DEF_FEATURE,
    DEF_SUFFICIENT_SET, DEF_NECESSARY_SET, DEF_INCLUSION_SET, DEF_LITERAL,

    OPEN_CONCEPT_BUTTON,

    INTERVAL_BOUND, MEASURE,

    RESET_SEARCH, NEXT_MATCH, PREVIOUS_MATCH, SEARCH_MAGNIFY,

    NAVIGATION_BADGE,

    CONCEPT_LIST_EDITOR_TOOLBAR,

    DESCRIPTION_CELL,
    DESCRIPTION_TEXT,
    STAMP_PANEL, STAMP_STATUS_PANEL, STAMP_TIME_PANEL, STAMP_AUTHOR_PANEL, STAMP_MODULE_PANEL, STAMP_PATH_PANEL,
    SEARCH_MATCH,
    SEARCH_NOT_MATCHED,
    SEARCH_TOP_COMPONENT;

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', '-');
    }

}
