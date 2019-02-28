open module anitrack.core {
    exports moe.anitrack.core;
    exports moe.anitrack.core.thirdparties;
    exports moe.anitrack.core.thirdparties.choice;

    requires anitrack.base;
    requires anitrack.persistence;
    requires anitrack.server;
    requires anitrack.thirdparties.common;
    requires anitrack.thirdparty.kitsu;
    requires anitrack.thirdparty.local;

    requires java.annotation;
    requires java.persistence;
    requires java.sql;
    requires slf4j.api;

    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires spring.boot;
    requires spring.orm;

}
