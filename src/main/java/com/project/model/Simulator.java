package com.project.model;

import java.util.*;

public class Simulator {
    private final Network network;
    private final int size_network;
    private final LinkedList<Flit> flits;
    private int clock;

    public Simulator(int size_network) {
        this.flits = new LinkedList<>();
        this.size_network = size_network;
        this.network = new Network(size_network);
        this.clock = 0;
    }

    public void addBlock(int x, int y) {
        network.blockRouter(x, y);
    }

    public void addBlocks(int[][] blocks) {
        for (int[] block : blocks) {
            network.blockRouter(block[0], block[1]);
        }
    }

    public void addFlit(int originX, int originY, int destinyX, int destinyY, int data) {
        Flit flit = new Flit(new int[]{originX, originY}, new int[]{destinyX, destinyY}, data);
        flit.setId(flits.size());
        flits.add(flit);
    }

    public void run() {
        while (!flits.isEmpty()) {
            clockTick();
            clock++;
        }
        System.out.println("Simulação concluída.");
    }

    private void clockTick() {
        System.out.println("Clock tick: " + clock);
        LinkedList<Flit> deliveredFlits = new LinkedList<>();

        for (int i = 0; i < flits.size(); i++) {
            Flit flit = flits.get(i);
            if (!flit.isDelivered()) {
                LinkedList<int[]> route = flit.getRoute();
                if (route.isEmpty()) {
                    route = roteamentoAStar(flit);
                    flit.setRoute(route);
                    if(route == null || route.isEmpty()){
                        System.out.println("Flit "+ flit.getId()+" não tem rota possivel e por isso o pacote não será enviado");
                        flits.remove(flit);
                        i = i-1;
                        continue;
                    }
                }

                if (route != null && !route.isEmpty()) {
                    int[] nextStep = route.poll();
                    if (network.isRouterAvailable(nextStep[0], nextStep[1], clock)) {
                        flit.advanceTo(nextStep);
                        network.occupyRouter(nextStep[0], nextStep[1], clock);
                        System.out.println("Flit " + flit.getId() + " avançou para (" + nextStep[0] + ", " + nextStep[1] + ")");
                    }
                }

            }

            if (flit.isDelivered()) {
                deliveredFlits.add(flit);
                System.out.println("Flit " + flit.getId() + " entregue em (" + flit.getDestinX() + ", " + flit.getDestinY() + ")");
            }
        }
        flits.removeAll(deliveredFlits);
        System.out.println("Fim do clock \nStatus atual: "+flits.size()+ " flits a serem entregues" );
    }

    private LinkedList<int[]> roteamentoAStar(Flit flit) {
        PriorityQueue<No> open = new PriorityQueue<>();
        Set<No> lock = new HashSet<>();

        No start = new No(flit.getOriginX(), flit.getOriginY(), null, 0, calcularHeuristica(flit.getOriginX(), flit.getOriginY(), flit.getDestinX(), flit.getDestinY()));
        open.add(start);

        while (!open.isEmpty()) {
            No atual = open.poll();
            if (atual.getX() == flit.getDestinX() && atual.getY() == flit.getDestinY() && !network.isBlocked(atual.getX(), atual.getY())) {
                return reconstruirCaminho(atual);
            }

            lock.add(atual);
            for (No vizinho : getVizinhos(atual, flit.getDestinX(), flit.getDestinY())) {
                if (lock.contains(vizinho) || network.isBlocked(vizinho.getX(), vizinho.getY())) {
                    continue;
                }
                if (!open.contains(vizinho) || vizinho.getFCost() < atual.getFCost()) {
                    open.add(vizinho);
                }
            }
        }
        return null;
    }

    private int calcularHeuristica(int x, int y, int destinoX, int destinoY) {
        return Math.abs(x - destinoX) + Math.abs(y - destinoY);
    }

    private Set<No> getVizinhos(No atual, int destinoX, int destinoY) {
        Set<No> vizinhos = new HashSet<>();
        int[][] direcoes = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : direcoes) {
            int novoX = atual.getX() + dir[0];
            int novoY = atual.getY() + dir[1];
            if (novoX >= 0 && novoX < size_network && novoY >= 0 && novoY < size_network) {
                int gCost = atual.getGCost() + 1;
                int hCost = calcularHeuristica(novoX, novoY, destinoX, destinoY);
                vizinhos.add(new No(novoX, novoY, atual, gCost, hCost));
            }
        }
        return vizinhos;
    }

    private LinkedList<int[]> reconstruirCaminho(No no) {
        LinkedList<int[]> rota = new LinkedList<>();
        No atual = no;
        while (atual != null) {
            rota.addFirst(new int[]{atual.getX(), atual.getY()});
            atual = atual.getPai();
        }
        return rota;
    }
}