package com.project;


import com.project.model.Simulador;

public class Main {
    public static void main(String[] args) {
        Simulador simulador = new Simulador(8);
        simulador.adicionarBloqueio(2, 2);
        int[] dados = {1, 2, 3, 4};
        simulador.enviarPacote(0, 0, 7, 7, dados);
    }
}