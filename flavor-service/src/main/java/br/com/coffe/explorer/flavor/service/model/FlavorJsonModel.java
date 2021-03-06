package br.com.coffe.explorer.flavor.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FlavorJsonModel (
        @JsonProperty("code") String code,
        @JsonProperty("name") String name,
        @JsonProperty("parent") FlavorJsonModel parentFlavor
) { }
