package com.project.model;

public class Flit {
    private int origem;
    private int destino;
    private int id; // Identificador único do flit
    private int[] dados; // Dados do flit

    public Flit(int origem, int destino, int id, int[] dados) {
        this.origem = origem;
        this.destino = destino;
        this.id = id;
        this.dados = dados;
    }

    // Getters e Setters
}
