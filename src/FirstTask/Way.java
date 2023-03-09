package FirstTask;

import java.util.ArrayList;

public class Way {
    private int x1;
    private int x2;
    private int power;
    private int cost;
    private int flow;

    public Way(int x1, int x2, int power, int cost) {
        this.x1 = x1;
        this.x2 = x2;
        this.power = power;
        this.cost = cost;
        this.flow = 0;
    }

    public Way() {
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public Way getWay(int x1, int x2, ArrayList<Way> list) {
        for (Way w : list) {
            if (w.x1 == x1 && w.x2 == x2)
                return w;
        }
        return null;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}