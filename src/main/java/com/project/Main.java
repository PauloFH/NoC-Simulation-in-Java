package com.project;
import com.project.model.Simulator;

public class Main {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(8);
        int[][] blocks = {{1,2},{2,1},{4,4},{5,5},{6,6}};
        simulator.addBlocks(blocks);
       simulator.addBlock(7, 6);
        int[] data = {1, 2, 3, 4};
        simulator.SendPackage(2, 2, 7, 7, data);
    }
}