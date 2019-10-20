open module moe.tristan.anitrack.thirdparty {

	requires moe.tristan.anitrack.commons;

	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.data.jpa;
	requires spring.orm;
	requires spring.web;

	requires java.persistence;
	requires java.sql;
	requires com.zaxxer.hikari;
	requires liquibase.core;

	requires org.slf4j;
	requires java.annotation;
	requires org.immutables.value;
	requires immutables.styles;

}
