package com.project.model;

import java.util.*;

public class Simulator {
    private final Router[][] network;
    private final int size = 8;
    private Map<Integer, Flit> flit_initials;

    public Simulator() {
        network = new Router[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                network[i][j] = new Router(i, j);
            }
        }
    }

    public void setBlockedRouters(List<int[]> blockedRouters) {
        for (int[] pos : blockedRouters) {
            network[pos[0]][pos[1]].setBlocked(true);
        }
    }

    public void printNetwork(List<Deque<Router>> paths) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(network[i][j].getStatusChar() + " ");
            }
            System.out.println();
        }
        System.out.println();
        int index_flit = 0;
        for(Deque<Router> path : paths) {
            if (!path.isEmpty()) {
                Flit flit = flit_initials.get(index_flit);
                Router current = path.peek();
                Router dest = path.peekLast();
                System.out.println("Flit lançado pelo Roteador: (" + flit.getSourceX() + ", " + flit.getSourceY() + ")" );
                assert current != null;
                if((flit.getDestX() == current.getX() && flit.getDestY() == current.getY())){
                    System.out.println("Flit enviado por (" + flit.getSourceX() + ", " + flit.getSourceY() + ") foi entregue em: ("+ dest.getX() + ", " + dest.getY() +") com data: " + flit.getData()+" com sucesso!!" );
                }else{
                    System.out.println("Roteador do proximo salto: (" + current.getX() + ", " + current.getY() + ")");
                    if (dest != null) {
                        System.out.println("Destino: (" + dest.getX() + ", " + dest.getY() + ")");
                    }
                    System.out.println();
                }

            }
            index_flit++;
        }
        System.out.println("------------------------------");

    }

    public void run(List<Flit> flits) {
        AStar AStar = new AStar(this.network);
        flit_initials = new HashMap<>();
        int index_flit = 0;
        List<Deque<Router>> paths = new ArrayList<>();
        for (Flit flit : flits) {
            List<Router> path = AStar.findPath(flit.getSourceX(), flit.getSourceY(), flit.getDestX(), flit.getDestY());
            if (path == null) {
                System.out.println("O pacote de origem (" + flit.getSourceX() + ", " + flit.getSourceY() + ") não pode chegar ao destino (" + flit.getDestX() + ", " + flit.getDestY() + ") por não ter caminho");
                continue;
            }
            LinkedList<Router> tmp = new LinkedList<>(path);
            paths.add(tmp);
            flit_initials.put(index_flit, flit);
            index_flit++;
        }

        int clock = 0;
        boolean packetsRemaining = true;
        while (packetsRemaining) {
            packetsRemaining = false;

            // Processar pacotes em cada caminho
            for (Deque<Router> path : paths) {
                if (!path.isEmpty()) {
                    Router router = path.peek();
                    Router nextRouter = path.size() > 1 ? path.toArray(new Router[0])[1] : null;
                    int direction = getDirection(router, nextRouter);

                    if (nextRouter != null && !router.isPortOccupied(direction) && router.isAccessible() && nextRouter.isAccessible()) {
                        path.poll();
                        router.setProcessing(true);
                        router.occupyPort(direction);
                        packetsRemaining = true;
                    } else if (nextRouter == null) {
                        path.poll();
                        router.setReceivedFlit(true);
                    }



                }
            }

            // Imprimir o estado atual da rede
            System.out.println("Clock: " + clock);
            printNetwork(paths);

            for (Router[] row : this.network) {
                for (Router router : row) {
                    router.setProcessing(false);
                    router.releasePorts();
                }
            }

            clock++;
        }
    }

    private int getDirection(Router current, Router next) {
        if (next == null) return -1;
        if (next.getX() > current.getX()) return Router.RIGHT;
        if (next.getX() < current.getX()) return Router.LEFT;
        if (next.getY() > current.getY()) return Router.UP;
        if (next.getY() < current.getY()) return Router.DOWN;
        return -1;
    }
}