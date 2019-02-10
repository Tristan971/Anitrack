open module anitrack.thirdparties.common {
    exports moe.anitrack.thirdparties.common;
    exports moe.anitrack.thirdparties.common.model.authentication.pre;
    exports moe.anitrack.thirdparties.common.model.authentication.post;
    exports moe.anitrack.thirdparties.common.model.input;

    requires java.annotation;

    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;

    requires org.immutables.value;
    requires slf4j.api;
}
