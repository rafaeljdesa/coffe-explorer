package br.com.coffe.explorer.flavor.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record FlavorGroupedJsonModel(
        @JsonProperty("name") String name,
        @JsonProperty("child_flavors") @JsonInclude(Include.NON_EMPTY) List<FlavorGroupedJsonModel> childFlavors
) { }
