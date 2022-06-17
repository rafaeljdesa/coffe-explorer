module br.com.coffe.explorer.flavor.service {
    requires spring.context;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.annotation;
    requires br.com.coffe.explorer.core.domain;

    opens br.com.coffe.explorer.flavor.service to spring.core;
    opens br.com.coffe.explorer.flavor.service.config to spring.core;

    exports br.com.coffe.explorer.flavor.service to spring.beans, spring.context;
    exports br.com.coffe.explorer.flavor.service.config to spring.beans, spring.context;
    exports br.com.coffe.explorer.flavor.service.controller to spring.beans, spring.context;
    exports br.com.coffe.explorer.flavor.service.model;
    exports br.com.coffe.explorer.flavor.service.model.factory;
}