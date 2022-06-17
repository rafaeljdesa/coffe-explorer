package br.com.coffe.explorer.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Flavor {

    private String code;
    private String name;
    private Flavor parentFlavor;

}
