package SecondTask;

import java.util.ArrayList;

public class FordFulkerson {
    static final int NUM_POINTS = 6;

    private boolean bfs(int graph[][], int enter, int exit, int parent[]) {
        boolean visited[] = new boolean[NUM_POINTS];
        for (int i = 0; i < NUM_POINTS; ++i)
            visited[i] = false;

        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(enter);
        visited[enter] = true;
        parent[enter] = -1;

        //bfs (обход в ширину)
        while (queue.size() != 0) {
            int nowIndex = queue.remove(0);

            for (int i = 0; i < NUM_POINTS; i++) {
                if (visited[i] == false && graph[nowIndex][i] > 0) {
                    if (i == exit) {
                        parent[i] = nowIndex;
                        return true;
                    }
                    queue.add(i);
                    parent[i] = nowIndex;
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    public int fordFulkerson(int graph[][], int enter, int exit) {
        int newGraph[][] = new int[NUM_POINTS][NUM_POINTS];

        for (int i = 0; i < NUM_POINTS; i++)
            for (int j = 0; j < NUM_POINTS; j++)
                newGraph[i][j] = graph[i][j];

        int parent[] = new int[NUM_POINTS];
        int maxFlow = 0;

        while (bfs(newGraph, enter, exit, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int i = exit; i != enter; i = parent[i]) {
                int j = parent[i];
                if (newGraph[j][i] < pathFlow){
                    pathFlow = newGraph[j][i];
                }
            }

            for (int i = exit; i != enter; i = parent[i]) {
                int j = parent[i];
                newGraph[j][i] -= pathFlow;
                newGraph[i][j] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
