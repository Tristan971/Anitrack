open module anitrack.gui {

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;

    requires easyfxml;

    requires anitrack.server;
    requires anitrack.core;
    requires javafx.fxml;
    requires javafx.graphics;
    requires anitrack.thirdparties.common;
    requires javafx.controls;
}
