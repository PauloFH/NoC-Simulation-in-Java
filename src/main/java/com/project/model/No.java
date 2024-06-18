package com.project.model;
public class No implements Comparable<No> {
    private int x, y;
    private int gCost;
    private int hCost;
    private No pai;


    public No(int x, int y, No pai, int gCost, int hCost) {
        this.x = x;
        this.y = y;
        this.pai = pai;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public No getPai() {
        return pai;
    }

    public int getGCost() {
        return gCost;
    }

    @Override
    public int compareTo(No outro) {
        return Integer.compare(this.getFCost(), outro.getFCost());
    }
}