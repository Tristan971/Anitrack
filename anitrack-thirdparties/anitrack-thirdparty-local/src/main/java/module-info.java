open module anitrack.thirdparty.local {
    exports moe.anitrack.thirdparties.thirdparty.local;
    exports moe.anitrack.thirdparties.thirdparty.local.model;
    exports moe.anitrack.thirdparties.thirdparty.local.repository;

    requires anitrack.thirdparties.common;

    requires java.annotation;
    requires java.persistence;
    requires java.sql;

    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.data.jpa;
    requires spring.orm;
    requires spring.tx;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires h2;
    requires net.bytebuddy;
    requires slf4j.api;
    requires spring.boot;
}
