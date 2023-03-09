package SecondTask;

public class Main {
    public static void main(String[] args){
        int graph[][] = new int[][] {
                { 0, 4, 3, 0, 2, 0 },
                { 0, 0, 1, 2, 0, 0 },
                { 0, 1, 0, 0, 2, 2 },
                { 0, 2, 0, 0, 0, 5 },
                { 0, 0, 2, 0, 0, 2 },
                { 0, 0, 0, 0, 0, 0 }
        };
        FordFulkerson fordFulkerson = new FordFulkerson();
        System.out.println("Maximum flow: " + fordFulkerson.fordFulkerson(graph, 0, 5));


        graph[2][3] = 3;
        System.out.println("Maximum flow with adding: " + fordFulkerson.fordFulkerson(graph, 0, 5));
    }
}
