package com.project.model;

public class Network {
    private final Router[][] network;

    public Network(int size_network) {
        network = new Router[size_network][size_network];
        for (int i = 0; i < size_network; i++) {
            for (int j = 0; j < size_network; j++) {
                network[i][j] = new Router(i, j);
            }
        }
    }

    public void block_Router(int x, int y) {
        network[x][y].setBlock(true);
    }

    public boolean isBlocked(int x, int y) {
        return network[x][y].isBlock();
    }

    public Router getRouter(int x, int y) {
        return network[x][y];
    }
}
