package com.project.model;

public class Rede {
    private Roteador[][] rede;

    public Rede(int tamanho) {
        rede = new Roteador[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                rede[i][j] = new Roteador(i, j);
            }
        }
    }

    public void bloquearRoteador(int x, int y) {
        rede[x][y].setBloqueado(true);
    }

    public boolean isBloqueado(int x, int y) {
        return rede[x][y].isBloqueado();
    }

    public Roteador getRoteador(int x, int y) {
        return rede[x][y];
    }
}
