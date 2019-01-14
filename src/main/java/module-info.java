open module kitsumonogatari {
    requires slf4j.api;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.web;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires java.compiler;
    requires java.sql;

    requires easyfxml;

    requires jackson.annotations;
    requires com.fasterxml.jackson.databind;

    requires org.immutables.value;
}
