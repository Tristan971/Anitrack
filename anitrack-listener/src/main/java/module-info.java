module anitrack.listener {

    requires java.annotation;

    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.web;

    requires com.fasterxml.jackson.databind;
    requires jackson.annotations;

    requires org.immutables.value;
    requires slf4j.api;

}
