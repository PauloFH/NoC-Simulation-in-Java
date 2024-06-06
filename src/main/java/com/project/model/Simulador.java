package com.project.model;

import java.util.LinkedList;
import java.util.Queue;

public class Simulador {
    private Rede rede;
    private int tamanho;

    public Simulador(int tamanho) {
        this.tamanho = tamanho;
        this.rede = new Rede(tamanho);
    }

    public void adicionarBloqueio(int x, int y) {
        rede.bloquearRoteador(x, y);
    }

    public void enviarPacote(int origemX, int origemY, int destinoX, int destinoY, int[] dados) {
        Flit flit = new Flit(origemX, destinoX, 1, dados); // ID do flit é arbitrário aqui
        roteamentoAdaptativo(flit, origemX, origemY, destinoX, destinoY);
    }

    private void roteamentoAdaptativo(Flit flit, int origemX, int origemY, int destinoX, int destinoY) {
        // Implementar o algoritmo de roteamento adaptativo
        Queue<int[]> rota = new LinkedList<>();
        boolean chegou = false;
        int hops = 0;
        int x = origemX;
        int y = origemY;

        while (!chegou) {
            rota.add(new int[]{x, y});
            if (x == destinoX && y == destinoY) {
                chegou = true;
                break;
            }
            // Algoritmo adaptativo aqui (exemplo simplificado)
            if (x < destinoX && !rede.isBloqueado(x + 1, y)) {
                x++;
            } else if (y < destinoY && !rede.isBloqueado(x, y + 1)) {
                y++;
            } else if (x > destinoX && !rede.isBloqueado(x - 1, y)) {
                x--;
            } else if (y > destinoY && !rede.isBloqueado(x, y - 1)) {
                y--;
            } else {
                // Tratamento para deadlock/livelock
            }
            hops++;
        }

        // Exibir a rota e os saltos
        System.out.println("Rota:");
        for (int[] pos : rota) {
            System.out.println("(" + pos[0] + ", " + pos[1] + ")");
        }
        System.out.println("Total de saltos: " + hops);
    }
}