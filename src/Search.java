import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Search {

    int [][] goalMat = new int[][]{{0,1,2},{3,4,5},{6,7,8}};
    Board goal = new Board(goalMat);
    public boolean depthFirstSearch(Board initialState){
        Stack<Board> frontier = new Stack<>();
        Set<Board> explored = new HashSet<>();
        frontier.push(initialState);
        while (!frontier.isEmpty()){
            Board state = frontier.pop();
            explored.add(state);

            if(goalTest(state)){
                return true;
            }

            for(Board neighbour: state.getNeighbours()) {
                // I know I have to make it compare between the values inside the objects,
                // not the objects references.
                if(!frontier.contains(neighbour)||!explored.contains(neighbour))
                    frontier.push(neighbour);
            }
        }
        return false;
    }
    public Boolean goalTest(Board state){
        return state.equals(goal);
    }
}
