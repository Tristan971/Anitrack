open module kitsumonogatari {
    requires slf4j.api;

    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.web;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires easyfxml;

    requires org.immutables.value;
    requires com.fasterxml.jackson.databind;
}
