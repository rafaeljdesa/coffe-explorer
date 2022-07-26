package br.com.coffe.explorer.core.domain.model;

public record CoffeImageResultModel(
        String fileName,
        String url,
        String status,
        String reason
) { }
