package com.leiamais.services;

import com.leiamais.models.Usuario;

public class UsuarioSession {

    private static UsuarioSession instance;
    private Usuario loggedInUsuario;


    private UsuarioSession() {
    }

    public static UsuarioSession getInstance() {
        if (instance == null) {
            instance = new UsuarioSession();
        }
        return instance;
    }

    public Usuario getLoggedInUsuario() {
        return loggedInUsuario;
    }

    public void setLoggedInUsuario(Usuario loggedInUsuario) {
        this.loggedInUsuario = loggedInUsuario;
    }

    public boolean isLoggedIn() {
        return this.loggedInUsuario != null;
    }

    public void logout() {
        this.loggedInUsuario = null;
    }

}
