package com.project.model;

import java.util.Random;

public class Flit {

    private int[] origem;
    private int[] destino;
    private int[] dados;

    public Flit(int[] origem, int destino[], int[] dados) {
        this.origem = origem;
        this.destino = destino;
        this.dados = dados;
    }
    public int[] getOrigem() {
        return origem;
    }

    public void setOrigem(int[] origem) {
        this.origem = origem;
    }

    public int[] getDestino() {
        return destino;
    }

    public void setDestino(int[] destino) {
        this.destino = destino;
    }

    public int[] getDados() {
        return dados;
    }

    public void setDados(int[] dados) {
        this.dados = dados;
    }
    public int getOriginX(){
        return origem[0];
    }
    public int getDestinX(){
        return destino[0];
    }
    public int getOriginY(){
        return origem[1];
    }
    public int getDestinY(){
        return destino[1];
    }
}
