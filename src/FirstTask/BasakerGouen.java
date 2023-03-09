package FirstTask;

import java.util.ArrayList;
import java.util.Collections;

public class BasakerGouen {
    static final int NUM_POINTS = 9;

    public static void main(String[] args) {
        Graph myGraph = new Graph();
        Way myWay = new Way();

        ArrayList<Way> graph = new ArrayList<>();
        graph.add(new Way(1, 2, 5, 6));
        graph.add(new Way(1, 3, 3, 20));
        graph.add(new Way(1, 4, 1, 36));
        graph.add(new Way(1, 6, 3, 12));
        graph.add(new Way(2, 5, 2, 13));
        graph.add(new Way(2, 6, 5, 14));
        graph.add(new Way(3, 4, 3, 14));
        graph.add(new Way(3, 6, 4, 8));
        graph.add(new Way(4, 7, 4, 5));
        graph.add(new Way(5, 8, 3, 16));
        graph.add(new Way(6, 8, 2, 12));
        graph.add(new Way(6, 9, 2, 11));
        graph.add(new Way(7, 9, 1, 5));
        graph.add(new Way(8, 9, 5, 9));
        System.out.println("GRAPH:");
        myGraph.printGraph(graph);

        int enter = myGraph.getEnter(graph);
        int exit = myGraph.getExit(graph);
        ArrayList<Integer> minWay = new ArrayList<>();
        Integer flowPower = 0;

        while (myGraph.hasWay(graph, enter, exit)) {
            minWay = myGraph.getMinWay(graph, NUM_POINTS);
            System.out.println("\nMinimal way: " + minWay);
            ArrayList<Integer> flowsPower = new ArrayList<>();
            for (int i = 0; i < minWay.size() - 1; i++) {
                Way way = myWay.getWay(minWay.get(i), minWay.get(i + 1), graph);
                flowsPower.add(way.getPower());
            }
            int nowPower = Collections.min(flowsPower);
            flowPower += nowPower;
            for (int i = 0; i < minWay.size() - 1; i++) {
                Way way = myWay.getWay(minWay.get(i), minWay.get(i + 1), graph);
                way.setFlow(nowPower);
                if (way.getFlow() == way.getPower()) {
                    if (way.getX1() != 1 && way.getX2() != NUM_POINTS) {
                        if (myWay.getWay(minWay.get(i + 1), minWay.get(i), graph) == null) {
                            graph.add(new Way(minWay.get(i + 1), minWay.get(i), way.getPower(), -1 * way.getCost()));
                        } else {
                            myWay.getWay(minWay.get(i + 1), minWay.get(i), graph).setPower(way.getPower());
                        }
                    }
                    System.out.println("\nWay to remove " + way.getX1() + " " + way.getX2());
                    graph.remove(way);
                } else {
                    way.setPower(way.getPower() - way.getFlow());
                    if (way.getX1() != 1 && way.getX2() != NUM_POINTS) {
                        if (myWay.getWay(minWay.get(i + 1), minWay.get(i), graph) == null) {
                            graph.add(new Way(minWay.get(i + 1), minWay.get(i), way.getPower(), -1 * way.getCost()));
                        } else {
                            myWay.getWay(minWay.get(i + 1), minWay.get(i), graph).setPower(way.getPower());
                        }
                    }
                }
            }
            System.out.println("\n==========================================");
            System.out.println("GRAPH:");
            myGraph.printGraph(graph);
            System.out.println("\nCurrent flow power: " + flowPower);
        }
        System.out.println("\n==========================================");
        System.out.println("\nRESULT FLOW POWER: " + flowPower);
    }
}
