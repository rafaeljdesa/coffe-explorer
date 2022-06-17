package br.com.coffe.explorer.core.domain.model;

import java.util.List;

public record FlavorGroupedModel(
        String code,
        String name,
        List<FlavorGroupedModel> childFlavors) {
}
