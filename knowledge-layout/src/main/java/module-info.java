module dev.ikm.komet_test.layout {

    requires dev.ikm.komet_test.framework;
    requires dev.ikm.jpms.recordbuilder.core;
    requires java.compiler;

    exports dev.ikm.komet_test.layout;
    exports dev.ikm.komet_test.layout.action;
    exports dev.ikm.komet_test.layout.component;
    exports dev.ikm.komet_test.layout.component.multi;
    exports dev.ikm.komet_test.layout.component.version.field;
    exports dev.ikm.komet_test.layout.component.version;
    exports dev.ikm.komet_test.layout.container;
    exports dev.ikm.komet_test.layout.context;
    exports dev.ikm.komet_test.layout.event;
    exports dev.ikm.komet_test.layout.orchestration;
    exports dev.ikm.komet_test.layout.orchestration.process;
    exports dev.ikm.komet_test.layout.preferences;
    exports dev.ikm.komet_test.layout.selection;
    exports dev.ikm.komet_test.layout.selection.element;
    exports dev.ikm.komet_test.layout.window;

    opens dev.ikm.komet_test.layout to javafx.fxml;
    opens dev.ikm.layout.app to javafx.fxml;
    exports dev.ikm.komet_test.layout.component.field;

}