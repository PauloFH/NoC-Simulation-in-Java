package com.project.model;

public class Flit {
    private final int sourceX, sourceY;
    private final int destX, destY;
    private final int data;
    private int hops;

    public Flit(int sourceX, int sourceY, int destX, int destY, int data) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destX = destX;
        this.destY = destY;
        this.data = data;
        this.hops = 0;
    }

    public int getSourceX() {
        return sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }


}
