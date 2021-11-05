import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Board> set = new HashSet<>();
        int[][] arr1 = {{1,2,3},{4,5,0},{7,8,9}};
        int[][] arr2 = {{1,2,3},{4,5,0},{7,8,9}};
        Board board1 = new Board(arr1);
        Board board2 = new Board(arr2);
        System.out.println(board1.equals(board2));
        set.add(board1);
        System.out.println(set.contains(board1));
        System.out.println(set.contains(board2));
        board1.drawBoard();
    }
}
