open module anitrack.server {
    exports moe.anitrack.server;
    exports moe.anitrack.server.objects;
    exports moe.anitrack.server.reactive;
    exports moe.anitrack.server.reactive.bindings;

    requires anitrack.base;

    requires java.annotation;

    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.context;
    requires spring.web;

    requires com.fasterxml.jackson.databind;
    requires jackson.annotations;
    requires org.immutables.value;
    requires slf4j.api;

}
