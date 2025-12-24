package com.api.via.cep.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    @com.google.gson.annotations.SerializedName("cep")
    private String postalCode;

    @com.google.gson.annotations.SerializedName("logradouro")
    private String street;

    @com.google.gson.annotations.SerializedName("bairro")
    private String neighborhood;

    @com.google.gson.annotations.SerializedName("regiao")
    private String region;

    @com.google.gson.annotations.SerializedName("estado")
    private String state;

    @com.google.gson.annotations.SerializedName("uf")
    private String stateCode;
}
