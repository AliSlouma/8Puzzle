
public class Main {
    public static void main(String[] args) {
        // Testing equals and hashCode
        int[][] arr1 = {{1,2,3},{4,5,0},{7,8,9}};
        Board board1 = new Board(arr1);
        System.out.println(new Search().depthFirstSearch(board1));
    }
}
