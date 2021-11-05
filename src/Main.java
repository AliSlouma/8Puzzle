import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Testing equals and hashCode
        Set<Board> set = new HashSet<>();
        int[][] arr1 = {{1,2,3},{4,5,0},{7,8,9}};
        int[][] arr2 = {{1,2,3},{4,5,0},{7,8,9}};
        Board board1 = new Board(arr1);
        Board board2 = new Board(arr2);
        System.out.println(board1.equals(board2));
        set.add(board1);
        System.out.println(set.contains(board1));
        System.out.println(set.contains(board2));
        //board1.drawBoard();
        int[][] arr = {{1,2,5},{3,4,0},{6,7,8}};
        Board board = new Board(arr);

        Search search = new Search();
        //System.out.println(search.BFS(board));
        search.AStarSearch(board,new EuclideanDistanceHeuristic(),false);
    }
}
