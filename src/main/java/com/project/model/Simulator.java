package com.project.model;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
public class Simulator {
    private final Network network;
    private final int size_network;

    public Simulator(int size_network) {
        this.size_network = size_network;
        this.network = new Network(size_network);
    }

    public void addBlock(int x, int y) {
        network.block_Router(x, y);
    }

    public void SendPackage(int origin_X, int origin_Y, int destiny_X, int destiny_Y, int[] data) {
        Flit flit = new Flit(origin_X, destiny_X, 1, data);
        LinkedList<int[]> route_chosed = roteamentoAStar(flit, origin_X, origin_Y, destiny_X, destiny_Y);
        if (route_chosed != null) {
            exibirCaminho(route_chosed);
            criarGrafico(route_chosed);
        } else {
            System.out.println("Nenhum caminho encontrado.");
        }
    }

    private LinkedList<int[]> roteamentoAStar(Flit flit, int origin_X, int origin_Y, int destiny_X, int destiny_Y) {
        PriorityQueue<No> open = new PriorityQueue<>();
        Set<No> lock = new HashSet<>();

        No start = new No(origin_X, origin_Y, null, 0, calcularHeuristica(origin_X, origin_Y, destiny_X, destiny_Y));
        open.add(start);

        while (!open.isEmpty()) {
            No atualy = open.poll();
            if (atualy.getX() == destiny_X && atualy.getY() == destiny_Y) {
                return reconstruirCaminho(atualy);
            }

            lock.add(atualy);
            for (No vizinho : getVizinhos(atualy, destiny_X, destiny_Y)) {
                if (lock.contains(vizinho) || network.isBlocked(vizinho.getX(), vizinho.getY())) {
                    continue;
                }
                if (!open.contains(vizinho) || vizinho.getFCost() < atualy.getFCost()) {
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

    private void exibirCaminho(LinkedList<int[]> rota) {
        System.out.println("Rota:");
        for (int[] pos : rota) {
            System.out.println("(" + pos[0] + ", " + pos[1] + ")");
        }
        System.out.println("Total de saltos: " + (rota.size() - 1));
    }

    private void criarGrafico(LinkedList<int[]> rota) {
        XYSeries series = new XYSeries("Rota");
        for (int[] pos : rota) {
            series.add(pos[0], pos[1]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Roteamento",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setInverted(true); // Inverte o eixo Y

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 560));
        ApplicationFrame frame = new ApplicationFrame("Roteamento");
        frame.setContentPane(chartPanel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

}