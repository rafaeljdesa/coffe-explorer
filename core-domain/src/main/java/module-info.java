module br.com.coffe.explorer.core.domain {
    requires transitive static lombok;
    exports br.com.coffe.explorer.core.domain.port.input;
    exports br.com.coffe.explorer.core.domain.port.output;
    exports br.com.coffe.explorer.core.domain.model;
    exports br.com.coffe.explorer.core.domain.entity;
    exports br.com.coffe.explorer.core.domain.service;
    exports br.com.coffe.explorer.core.domain.exception;
}