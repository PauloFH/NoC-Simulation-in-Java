package com.project.model;

public class Flit {

    private int origem;
    private int destino;
    private int id;
    private int[] dados;

    public Flit(int origem, int destino, int id, int[] dados) {
        this.origem = origem;
        this.destino = destino;
        this.id = id;
        this.dados = dados;
    }
    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getDados() {
        return dados;
    }

    public void setDados(int[] dados) {
        this.dados = dados;
    }

}
