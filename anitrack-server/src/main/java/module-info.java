open module anitrack.server {
    exports moe.anitrack.server;
    exports moe.anitrack.server.objects;
    exports moe.anitrack.server.reactive;
    exports moe.anitrack.server.reactive.bindings;

    requires java.annotation;

    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.web;

    requires com.fasterxml.jackson.databind;

    requires org.immutables.value;
    requires slf4j.api;
    requires spring.boot.autoconfigure;
    requires anitrack.base;

}
