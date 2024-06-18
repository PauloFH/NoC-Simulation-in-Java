package com.project;

import com.project.model.Simulator;

public class Main {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(8);

        int[][] blocks = {
                {1, 1}, {1, 2}, {1, 3},
                {2, 1}, {3, 1}, {2, 3},
                {3, 3}, {4, 3}, {5, 3},
                {6, 3}, {3, 2}, {3, 4},
                {3, 5}, {3, 6}};
        simulator.addBlocks(blocks);

        int data = 255;
        simulator.addFlit(0, 0, 7, 0, data);  //  possível
        simulator.addFlit(0, 0, 2, 2, data);  //  bloqueado no meio

    /*simulator.addFlit(0, 0, 2, 2, data);  //  bloqueado no meio
    simulator.addFlit(7, 0, 0, 7, data);  //  possível
    simulator.addFlit(2, 2, 6, 6, data);  //  bloqueado no fim
    simulator.addFlit(0, 7, 7, 0, data);  //  possível
    simulator.addFlit(7, 7, 0, 0, data);  //  possível
    simulator.addFlit(4, 4, 7, 7, data);  //  possível
    simulator.addFlit(3, 3, 7, 0, data);  //  possível
    simulator.addFlit(6, 6, 0, 0, data);  //  bloqueado
     */
        simulator.run();
    }
}