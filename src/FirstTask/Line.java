package FirstTask;

import java.util.ArrayList;

public class Line {
    private ArrayList<Integer> lineNums;
    private int cost;

    public Line(ArrayList<Integer> lineNums, int cost) {
        this.lineNums = lineNums;
        this.cost = cost;
    }

    public Line() {
    }

    public Line getLineByMinCost(ArrayList<Line> lines, int index) {
        Line resLine = new Line();
        int minCost = Integer.MAX_VALUE;
        for (Line line : lines) {
            if (!line.lineNums.contains(index) && line.cost < minCost) {
                resLine = line;
                minCost = line.cost;
            }
        }
        return resLine;
    }

    public Line getLineByMinCost(ArrayList<Line> lines) {
        Line resLine = new Line();
        int minCost = Integer.MAX_VALUE;
        for (Line line : lines) {
            if (line.cost < minCost) {
                resLine = line;
                minCost = line.cost;
            }
        }
        return resLine;
    }

    public ArrayList<Integer> getLineNums() {
        return lineNums;
    }

    public void setLineNums(ArrayList<Integer> lineNums) {
        this.lineNums = lineNums;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
