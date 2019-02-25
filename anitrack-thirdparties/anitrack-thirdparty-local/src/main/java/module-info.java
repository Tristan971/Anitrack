open module anitrack.thirdparty.local {
    exports moe.anitrack.thirdparties.thirdparty.local;
    exports moe.anitrack.thirdparties.thirdparty.local.model;
    exports moe.anitrack.thirdparties.thirdparty.local.repository;

    requires anitrack.thirdparties.common;
    requires spring.context;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires spring.data.jpa;
    requires java.persistence;
    requires h2;
    requires spring.core;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires slf4j.api;
    requires java.annotation;
    requires net.bytebuddy;
    requires com.fasterxml.jackson.core;
}
