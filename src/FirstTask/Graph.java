package FirstTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph {
    public void printGraph(ArrayList<Way> graph) {
        for (Way w : graph) {
            System.out.println("From " + w.getX1() + " to " + w.getX2() + " with power " + w.getPower() + " with cost " + w.getCost());
        }
    }

    public ArrayList<Integer> getMinWay(ArrayList<Way> graph, int num_points) {
        ArrayList<ArrayList<Line>> lines = new ArrayList<>();
        ArrayList<Integer> pool = new ArrayList<>();
        Line myLine = new Line();
        pool.add(1);
        for (int i = 0; i < num_points; i++) {
            lines.add(new ArrayList<>());
        }
        lines.get(0).add(new Line(new ArrayList<>(Arrays.asList(1)), 0));
        int counter = 0;
        int nowPoint = pool.get(counter);
        while (nowPoint != 9) {
            for (Way w : graph) {
                if (w.getX1() == nowPoint) {
                    if (!pool.contains(w.getX2())) {
                        pool.add(w.getX2());
                    }
                    ArrayList<Integer> newLine = new ArrayList<>(myLine.getLineByMinCost(lines.get(nowPoint - 1), w.getX2()).getLineNums());
                    newLine.add(w.getX2());
                    lines.get(w.getX2() - 1).add(new Line(newLine, lines.get(nowPoint - 1).get(0).getCost() + w.getCost()));
                }
            }
            counter++;
            nowPoint = pool.get(counter);
        }

        return myLine.getLineByMinCost(lines.get(nowPoint - 1)).getLineNums();
    }

    //method not working
    public ArrayList<Integer> getMinimalWay(ArrayList<Way> graph, int num_points) {
        Way myWay = new Way();
        ArrayList<ArrayList<Integer>> minWaysToPoints = new ArrayList<ArrayList<Integer>>();
        int[] earlyArr = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        minWaysToPoints.add(new ArrayList<>(Arrays.asList(1)));

        for (int k = 0; k < num_points; k++) {
            for (int i = 1; i < num_points; i++) {
                minWaysToPoints.add(new ArrayList<>());
                int min = Integer.MAX_VALUE;
                if (myWay.getWay(1, i + 1, graph) != null) {
                    minWaysToPoints.get(i).clear();
                    minWaysToPoints.get(i).add(1);
                    min = myWay.getWay(1, i + 1, graph).getCost();
                }
                for (int j = 0; j < num_points; j++) {
                    if (myWay.getWay(j + 1, i + 1, graph) != null && min > earlyArr[j] + myWay.getWay(j + 1, i + 1, graph).getCost()) {
                        minWaysToPoints.get(i).clear();
                        minWaysToPoints.get(i).add(j + 1);
                        min = earlyArr[j] + myWay.getWay(j + 1, i + 1, graph).getCost();
                    }
                }
                minWaysToPoints.get(i).add(i + 1);
                earlyArr[i] = min;
            }
        }

        int index = num_points - 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(num_points);
        for (int i = 0; i < num_points && index != 0; i++) {
            result.add(minWaysToPoints.get(index).get(0));
            index = minWaysToPoints.get(index).get(0) - 1;
        }
        Collections.reverse(result);
        return result;
    }

    public boolean hasWay(ArrayList<Way> graph, int enter, int exit) {
        ArrayList<Integer> pointsToVisit = new ArrayList<>();
        pointsToVisit.add(enter);
        int counter = 0;
        while (pointsToVisit.size() > counter) {
            int num = pointsToVisit.get(counter);
            if (num == exit) {
                return true;
            }
            for (Way w : graph) {
                if (w.getX1() == num && !pointsToVisit.contains(w.getX2())) {
                    pointsToVisit.add(w.getX2());
                }
            }
            counter++;
        }
        return false;
    }

    public int getEnter(ArrayList<Way> graph) {
        ArrayList<Integer> points = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (Way g : graph) {
            if (points.contains(g.getX2())) {
                points.remove(points.indexOf(g.getX2()));
            }
        }
        return points.get(0);
    }

    public int getExit(ArrayList<Way> graph) {
        ArrayList<Integer> points = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (Way g : graph) {
            if (points.contains(g.getX1())) {
                points.remove(points.indexOf(g.getX1()));
            }
        }
        return points.get(0);
    }
}
