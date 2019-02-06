open module anitrack.thirdparties.common {
    exports moe.anitrack.thirdparties.common;
    exports moe.anitrack.thirdparties.common.objects;

    requires java.annotation;

    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;

    requires org.immutables.value;
    requires slf4j.api;
}
