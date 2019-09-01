open module anitrack.core {
    exports moe.anitrack.core;
    exports moe.anitrack.core.model.thirdparties;
    exports moe.anitrack.core.model.events.authentication;

    requires anitrack.persistence;
    requires anitrack.server;
    requires anitrack.thirdparties.common;
    requires anitrack.thirdparty.kitsu;
    requires anitrack.thirdparty.local;

    requires java.annotation;

    requires spring.beans;
    requires spring.context;

    requires slf4j.api;
}
