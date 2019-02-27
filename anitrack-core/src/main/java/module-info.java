open module anitrack.core {
    exports moe.anitrack.core;
    exports moe.anitrack.core.thirdparties;
    exports moe.anitrack.core.thirdparties.choice;

    requires anitrack.base;
    requires anitrack.server;
    requires anitrack.thirdparties.common;
    requires anitrack.thirdparty.kitsu;
    requires anitrack.thirdparty.local;

    requires java.annotation;
    requires spring.context;

    requires slf4j.api;
    requires spring.beans;
    requires spring.boot.autoconfigure;

}
