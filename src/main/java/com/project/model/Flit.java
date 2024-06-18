package com.project.model;

import java.util.LinkedList;

public class Flit {
    private int id;
    private final int[] origin;
    private final int[] destination;
    private final int data;
    private boolean delivered;
    private LinkedList<int[]> route;


    public Flit(int[] origin, int[] destination, int data) {
        this.origin = origin;
        this.destination = destination;
        this.data = data;
        this.delivered = false;
        this.route = new LinkedList<>();
    }

    public int getOriginX() {
        return origin[0];
    }

    public int getOriginY() {
        return origin[1];
    }

    public int getDestinX() {
        return destination[0];
    }

    public int getDestinY() {
        return destination[1];
    }

    public int getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void advanceTo(int[] nextStep) {
        route.add(nextStep);
        if (nextStep[0] == destination[0] && nextStep[1] == destination[1]) {
            delivered = true;
        }
    }

    public LinkedList<int[]> getRoute() {
        return route;
    }

    public void setRoute(LinkedList<int[]> route) {
        this.route = route;
    }
}