open module anitrack.thirdparties.common {
    exports moe.anitrack.thirdparties.common;
    exports moe.anitrack.thirdparties.common.model.authentication;
    exports moe.anitrack.thirdparties.common.model.media;
    exports moe.anitrack.thirdparties.common.model.presentation;

    requires transitive anitrack.base;

    requires java.annotation;

    requires spring.beans;
    requires spring.context;
    requires spring.web;
    requires spring.boot.autoconfigure;

    requires com.fasterxml.jackson.databind;
    requires jackson.annotations;
    requires org.immutables.value;
    requires slf4j.api;

}
