public class Testing {
    public static void main(String[] args) {
        int[][] arr1 = {{1,2,3},{4,0,5},{6,7,8}};
//        int[][] arr3 = {{1,0,2},{3,4,5},{6,7,8}};
        int[][] arr3 = {{1,2,5},{3,4,0},{7,6,8}};
        Board board1 = new Board(arr1);
//        Board board2 = new Board(arr2);
        Board board3 = new Board(arr3);
        //Testing
        long start = System.currentTimeMillis();
//        System.out.println(new Search().depthFirstSearch(board1));
        System.out.println(new Search().BFS(board1));
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Elapsed Time: "+ elapsedTime + " ms");
    }

}
