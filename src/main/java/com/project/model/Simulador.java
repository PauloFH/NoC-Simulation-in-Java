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
public class Simulador {
    private Rede rede;
    private int tamanho;

    public Simulador(int tamanho) {
        this.tamanho = tamanho;
        this.rede = new Rede(tamanho);
    }

    public void adicionarBloqueio(int x, int y) {
        rede.bloquearRoteador(x, y);
    }

    public void enviarPacote(int origemX, int origemY, int destinoX, int destinoY, int[] dados) {
        Flit flit = new Flit(origemX, destinoX, 1, dados);
        LinkedList<int[]> rota = roteamentoAStar(flit, origemX, origemY, destinoX, destinoY);
        if (rota != null) {
            exibirCaminho(rota);
            criarGrafico(rota);
        } else {
            System.out.println("Nenhum caminho encontrado.");
        }
    }

    private LinkedList<int[]> roteamentoAStar(Flit flit, int origemX, int origemY, int destinoX, int destinoY) {
        PriorityQueue<No> aberta = new PriorityQueue<>();
        Set<No> fechada = new HashSet<>();

        No inicio = new No(origemX, origemY, null, 0, calcularHeuristica(origemX, origemY, destinoX, destinoY));
        aberta.add(inicio);

        while (!aberta.isEmpty()) {
            No atual = aberta.poll();
            if (atual.getX() == destinoX && atual.getY() == destinoY) {
                return reconstruirCaminho(atual);
            }

            fechada.add(atual);
            for (No vizinho : getVizinhos(atual, destinoX, destinoY)) {
                if (fechada.contains(vizinho) || rede.isBloqueado(vizinho.getX(), vizinho.getY())) {
                    continue;
                }
                if (!aberta.contains(vizinho) || vizinho.getFCost() < atual.getFCost()) {
                    aberta.add(vizinho);
                }
            }
        }
        return null;
    }

    private int calcularHeuristica(int x, int y, int destinoX, int destinoY) {
        // Usando a distância de Manhattan como heurística
        return Math.abs(x - destinoX) + Math.abs(y - destinoY);
    }

    private Set<No> getVizinhos(No atual, int destinoX, int destinoY) {
        Set<No> vizinhos = new HashSet<>();
        int[][] direcoes = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : direcoes) {
            int novoX = atual.getX() + dir[0];
            int novoY = atual.getY() + dir[1];
            if (novoX >= 0 && novoX < tamanho && novoY >= 0 && novoY < tamanho) {
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