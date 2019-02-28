open module anitrack.persistence {
    exports moe.anitrack.persistence;

    requires java.persistence;

    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

}
