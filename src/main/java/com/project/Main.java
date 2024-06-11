package com.project;
import com.project.model.Simulator;

public class Main {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(8);
        int[][] blocks = {{2,2},{3,3},{4,4},{5,5},{6,6}};
        simulator.addBlocks(blocks);
       //simulator.addBlock(2, 2);
       //simulator.addBlock(3, 3);
       //simulator.addBlock(4, 4);
       //simulator.addBlock(7, 3);
       //simulator.addBlock(7, 6);
        int[] data = {1, 2, 3, 4};
        simulator.SendPackage(1, 2, 7, 7, data);
    }
}