open module anitrack.thirdparty.local {
    exports moe.anitrack.thirdparties.thirdparty.local;
    exports moe.anitrack.thirdparties.thirdparty.local.model;
    exports moe.anitrack.thirdparties.thirdparty.local.repository;

    requires anitrack.persistence;
    requires anitrack.thirdparties.common;

    requires java.persistence;

    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires slf4j.api;
}
