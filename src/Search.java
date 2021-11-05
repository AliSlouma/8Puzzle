import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Search {

    private static int [][] goalMat = new int[][]{{0,1,2},{3,4,5},{6,7,8}};
    private static Board goal = new Board(goalMat);
    public boolean depthFirstSearch(Board initialState){
        Stack<Board> frontier = new Stack<>();
        Set<Board> explored = new HashSet<>();
        frontier.push(initialState);
        while (!frontier.isEmpty()){
            Board state = frontier.pop();
            explored.add(state);
//            System.out.println(explored.size());
            state.drawBoard();

            if(goalTest(state)){
                return true;
            }

            for(Board neighbour: state.getNeighbours()) {
                if(!frontier.contains(neighbour) && !explored.contains(neighbour))
                    frontier.push(neighbour);
            }
        }
        return false;
    }
    public static Boolean goalTest(Board state){
        return state.equals(goal);
    }
}
