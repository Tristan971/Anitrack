open module anitrack.thirdparties.common {
    exports moe.anitrack.thirdparties.common;
    exports moe.anitrack.thirdparties.common.model.authentication.pre;
    exports moe.anitrack.thirdparties.common.model.authentication.post;
    exports moe.anitrack.thirdparties.common.model.media;
    exports moe.anitrack.thirdparties.common.model.presentation;

    requires spring.beans;
    requires spring.context;
    requires spring.web;

    requires com.fasterxml.jackson.databind;
    requires org.immutables.value;
    requires slf4j.api;
}
