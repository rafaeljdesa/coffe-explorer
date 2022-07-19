package br.com.coffe.explorer.coffe.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoffeJsonModel(
        @JsonProperty("id") String id,
        @JsonProperty("description") String description,
        @JsonProperty("created_by") String createdBy,
        @JsonProperty("flavor_code") String flavorCode
) {
}
