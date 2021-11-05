public class Testing {
    public static void main(String[] args) {
        // Creating a board
        int[][] arr = {{1,2,5},{3,4,0},{6,7,8}};
        Board board = new Board(arr);
        /*Testing*/
        //BFS
        long startBFS = System.currentTimeMillis();
        System.out.println(new Search().BFS(board));
        long endBFS = System.currentTimeMillis();
        long elapsedTimeBFS = endBFS - startBFS;
        System.out.println("Elapsed Time: "+ elapsedTimeBFS + " ms");
        //DFS
        long startDFS = System.currentTimeMillis();
        System.out.println(new Search().depthFirstSearch(board));
        long endDFS = System.currentTimeMillis();
        long elapsedTimeDFS = endDFS - startDFS;
        System.out.println("Elapsed Time: "+ elapsedTimeDFS + " ms");
        //A* Search
        long startAstar = System.currentTimeMillis();
        Search search = new Search();
        System.out.println(search.BFS(board));
        search.AStarSearch(board,new EuclideanDistanceHeuristic(),true);
        long endAstar = System.currentTimeMillis();
        long elapsedTimeAstar = endAstar - startAstar;
        System.out.println("Elapsed Time: "+ elapsedTimeAstar + " ms");
    }
}
