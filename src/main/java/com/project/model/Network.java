package com.project.model;

import java.util.HashSet;
import java.util.Set;

public class Network {
    private final int size;
    private final boolean[][] blocked;
    private final Set<String> occupiedRouters;


    public Network(int size) {
        this.size = size;
        this.blocked = new boolean[size][size];
        this.occupiedRouters = new HashSet<>();
    }

    public void blockRouter(int x, int y) {
        blocked[x][y] = true;
    }

    public boolean isBlocked(int x, int y) {
        return blocked[x][y];
    }

    public boolean isRouterAvailable(int x, int y, int clock) {
        String key = x + "," + y + "," + clock;
        return !occupiedRouters.contains(key);
    }

    public void occupyRouter(int x, int y, int clock) {
        String key = x + "," + y + "," + clock;
        occupiedRouters.add(key);
    }
}