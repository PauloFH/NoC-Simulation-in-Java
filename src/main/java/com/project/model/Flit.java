package com.project.model;

public class Flit {
    private final int sourceX, sourceY;
    private final int destX, destY;
    private final int data;

    public Flit(int sourceX, int sourceY, int destX, int destY, int data) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destX = destX;
        this.destY = destY;
        this.data = data;
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
    @Override
    public String toString() {
        return "-------------------------\norigem: (" + sourceX + ", " + sourceY + ")\n destino: (" + destX + ", " + destY + ") \n dados: " + data + "-------------------------";
    }
}