import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Search {



    public boolean depthFirstSearch(Board initialState){
        Stack<Board> frontier = new Stack<>();
        Set<Board> explored = new HashSet<>();
        while (!frontier.isEmpty()){
            Board state = frontier.pop();
            explored.add(state);

            if(goalTest(state)){
                return true;
            }
            for(Board neighbour: state.getNeighbours()) {

            }
        }
        return false;
    }
    public Boolean goalTest(Board state){
        return false;

    }
}
