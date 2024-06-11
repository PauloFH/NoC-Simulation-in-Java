package com.project.model;

public class Router {
    private int x;
    private int y;
    private boolean block;

    public Router(int x, int y) {
        this.x = x;
        this.y = y;
        this.block = false;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isBlock() {
        return block;
    }

}

