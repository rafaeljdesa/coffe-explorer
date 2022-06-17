package br.com.coffe.explorer.core.domain.model;

public record CoffeModel(
        String id,
        String description,
        String createdBy,
        String flavorCode
) {
}
