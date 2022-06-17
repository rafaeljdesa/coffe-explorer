package br.com.coffe.explorer.core.domain.model;

public record FlavorModel(
        String code,
        String name,
        FlavorModel parentFlavor) {
}
