open module anitrack.base {

    exports moe.anitrack.base;
    exports moe.anitrack.base.util;
    exports moe.anitrack.base.util.form;

    requires transitive java.sql;

    requires spring.context;

    requires transitive org.immutables.value;

}
