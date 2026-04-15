package com.camiones.dto;

import jakarta.validation.constraints.NotBlank;

public class CamionRequest {

    @NotBlank
    private String placa;

    @NotBlank
    private String tipoCamion;

    public CamionRequest() {
    }

    public CamionRequest(String placa, String tipoCamion) {
        this.placa = placa;
        this.tipoCamion = tipoCamion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoCamion() {
        return tipoCamion;
    }

    public void setTipoCamion(String tipoCamion) {
        this.tipoCamion = tipoCamion;
    }
}
