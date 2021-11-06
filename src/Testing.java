public class Testing {
    public static void main(String[] args) {
        // Creating a board
        int[][] arr = {{1, 2, 5}, {3, 4, 0}, {6, 7, 8}};
        Board board = new Board(arr);
        /*Testing*/

        //DFS
        long startDFS = System.currentTimeMillis();
        System.out.println("Found Solution using DFS ? " + new Search().DFS(board));
        long endDFS = System.currentTimeMillis();
        long elapsedTimeDFS = endDFS - startDFS;
        System.out.println("Elapsed Time (DFS): " + elapsedTimeDFS + " ms");

        //BFS
        long startBFS = System.currentTimeMillis();
        System.out.println("Found Solution using BFS ? " + new Search().BFS(board));
        long endBFS = System.currentTimeMillis();
        long elapsedTimeBFS = endBFS - startBFS;
        System.out.println("Elapsed Time : " + elapsedTimeBFS + " ms");

        //A* Search with EuclideanDistance
        long startAStar = System.currentTimeMillis();
        System.out.println("Found Solution using (A* ED) ? " + new Search().AStar(board, new EuclideanDistanceHeuristic(), false));
        long endAStar = System.currentTimeMillis();
        long elapsedTimeAStar = endAStar - startAStar;
        System.out.println("Elapsed Time : " + elapsedTimeAStar + " ms");

        //A* Search with ManhattanDistance
        long startAStar2 = System.currentTimeMillis();
        System.out.println("Found Solution using (A* MD) ? " + new Search().AStar(board, new ManhattanDistanceHeuristic(), false));
        long endAStar2 = System.currentTimeMillis();
        long elapsedTimeAStar2 = endAStar2 - startAStar2;
        System.out.println("Elapsed Time : " + elapsedTimeAStar2 + " ms");

    }
}
