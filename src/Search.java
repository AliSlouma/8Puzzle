import java.util.*;

public class Search {

    private static final int [][] goalMat = new int[][]{{0,1,2},{3,4,5},{6,7,8}};
    private static final Board goal= new Board(goalMat);
    public boolean BFS(Board initialState){
        Queue<Board> frontier = new LinkedList<>();
        Set<Board> explored = new HashSet<>();
        frontier.add(initialState);

        while (!frontier.isEmpty()){
            Board state = frontier.poll();
            state.drawBoard();
            explored.add(state);
            if(goalTest(state)){
                getPathToGoal(state);
                System.out.println("nodes expanded = " + explored.size());
                return true;
            }


            for (Board neighbour : state.getNeighbours()){
                if(!frontier.contains(neighbour) && !explored.contains(neighbour))
                    frontier.add(neighbour);
            }

        }
        System.out.println("nodes expanded = " + explored.size());
        return false;
    }

    private void getPathToGoal(Board state) {
        Stack<Board> boards = new Stack<>();
        while (state!=null){
            boards.push(state);
            state = state.getParent();
        }
        System.out.println("__________________________________");
        System.out.println("Search Depth = " + boards.size());
        while (!boards.isEmpty()){
            boards.pop().drawBoard();
        }
    }

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
                getPathToGoal(state);
                System.out.println("nodes expanded = " + explored.size());
                return true;
            }

            for(Board neighbour: state.getNeighbours()) {
                if(!frontier.contains(neighbour) && !explored.contains(neighbour))
                    frontier.push(neighbour);
            }
        }
        System.out.println("nodes expanded = " + explored.size());
        return false;
    }
    public static Boolean goalTest(Board state){
        return state.equals(goal);
    }
}
