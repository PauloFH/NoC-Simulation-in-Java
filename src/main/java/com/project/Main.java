package com.project;
import com.project.model.Simulator;

public class Main {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(8);
        simulator.addBlock(2, 2);
        simulator.addBlock(3, 3);
        simulator.addBlock(4, 4);
        simulator.addBlock(7, 3);
        int[] dads = {1, 2, 3, 4};
        simulator.SendPackage(0, 0, 7, 7, dads);
    }
}