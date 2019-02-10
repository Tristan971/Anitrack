open module anitrack.thirdparties.kitsu {

    exports moe.anitrack.thirdparties.thirdparty.kitsu;
    exports moe.anitrack.thirdparties.thirdparty.kitsu.objects.media;
    exports moe.anitrack.thirdparties.thirdparty.kitsu.objects.authentication;
    exports moe.anitrack.thirdparties.thirdparty.kitsu.objects.media.common;

    requires anitrack.thirdparties.common;

    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.web;

    requires com.fasterxml.jackson.databind;
    requires jackson.annotations;
    requires java.annotation;

    requires org.immutables.value;
    requires slf4j.api;
    requires spring.boot.autoconfigure;

}
