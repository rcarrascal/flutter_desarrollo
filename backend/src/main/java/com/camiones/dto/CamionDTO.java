package com.camiones.dto;

public class CamionDTO {

    private Long id;
    private String placa;
    private String tipoCamion;
    private Long usuarioId;

    public CamionDTO() {
    }

    public CamionDTO(Long id, String placa, String tipoCamion, Long usuarioId) {
        this.id = id;
        this.placa = placa;
        this.tipoCamion = tipoCamion;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
