package com.project.model;

import java.util.*;

public class AStar {
    private final Router[][] network;

    public AStar(Router[][] network) {
        this.network = network;
    }

    private static class Node implements Comparable<Node> {
        int x, y;
        int cost, heuristic;
        Node parent;

        Node(int x, int y, int cost, int heuristic, Node parent) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.heuristic = heuristic;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost + this.heuristic, other.cost + other.heuristic);
        }
    }

    private int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public List<Router> findPath(int startX, int startY, int endX, int endY) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        openSet.add(new Node(startX, startY, 0, heuristic(startX, startY, endX, endY), null));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            String currentKey = current.x + "," + current.y;

            if (current.x == endX && current.y == endY) {
                return constructPath(current);
            }

            closedSet.add(currentKey);

            for (int[] direction : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValid(newX, newY) && !network[newX][newY].isBlocked() && !closedSet.contains(newX + "," + newY)) {
                    openSet.add(new Node(newX, newY, current.cost + 1, heuristic(newX, newY, endX, endY), current));
                }
            }
        }
        return null;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < network.length && y < network[0].length;
    }

    private List<Router> constructPath(Node node) {
        List<Router> path = new ArrayList<>();
        while (node != null) {
            path.add(network[node.x][node.y]);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}