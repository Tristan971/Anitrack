open module anitrack.thirdparties {
    exports moe.anitrack.thirdparties;

    requires anitrack.thirdparties.common;
    requires anitrack.thirdparties.kitsu;
    requires spring.context;
    requires slf4j.api;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires java.annotation;
}
