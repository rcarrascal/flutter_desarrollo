package com.camiones.dto;

public class UsuarioDTO {

    private Long id;
    private String username;
    private String email;
    private String empresa;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String username, String email, String empresa) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
