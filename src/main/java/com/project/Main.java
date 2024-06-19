package com.project;

import com.project.model.Flit;
import com.project.model.Simulator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        int[][] blocks = {
                {1, 1}, {1, 2}, {1, 3},
                {2, 1},         {2, 3},
                {3, 1}, {3, 2}, {3, 3}, {3, 4},{3, 5}, {3, 6},
                                {4, 3},
                                {5, 3},
                                {6, 3}
                                                                    };

        List<int[]> blockedRouters = new ArrayList<>(Arrays.asList(blocks));
        simulator.setBlockedRouters(blockedRouters);
        int data = 255;
        List<Flit> flits = new ArrayList<>();
        flits.add(new Flit(0, 1, 0, 3, data));
        flits.add(new Flit(0, 0, 2, 2, data));
        flits.add(new Flit(7, 0, 0, 7, data));
        flits.add(new Flit(2, 2, 6, 6, data));
        flits.add(new Flit(6, 6, 0, 0, data));
        flits.add(new Flit(3, 3, 7, 0, data));
        flits.add(new Flit(7, 7, 0, 0, data));
        flits.add(new Flit(4, 4, 7, 7, data));

        simulator.run(flits);


    }
}