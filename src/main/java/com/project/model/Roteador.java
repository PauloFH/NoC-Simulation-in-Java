package com.project.model;

public class Roteador {
    private int x;
    private int y;
    private boolean bloqueado;

    public Roteador(int x, int y) {
        this.x = x;
        this.y = y;
        this.bloqueado = false;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

}

