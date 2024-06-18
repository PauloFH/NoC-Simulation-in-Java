package com.project.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Router {
    private int x, y;
    private boolean blocked;
    private boolean receivedFlit;
    private boolean processing;
    private final Set<Integer> occupiedPorts;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public Router(int x, int y) {
        this.x = x;
        this.y = y;
        this.blocked = false;
        this.receivedFlit = false;
        this.processing = false;
        this.occupiedPorts = new HashSet<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean hasReceivedFlit() {
        return receivedFlit;
    }

    public void setReceivedFlit(boolean receivedFlit) {
        this.receivedFlit = receivedFlit;
    }


    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isProcessing() {
        return processing;
    }

    public boolean isPortOccupied(int port) {
        return occupiedPorts.contains(port);
    }

    public void occupyPort(int port) {
        occupiedPorts.add(port);
    }

    public void releasePorts() {
        occupiedPorts.clear();
    }

    public void processFlits() {
        // Implementação do processamento de pacotes
    }

    public char getStatusChar() {
        if (blocked) return 'X';
        if (receivedFlit) return '*';
        if (processing) return 'O';
        return '.';
    }

    public boolean isAccessible() {
        return !blocked;
    }
}
