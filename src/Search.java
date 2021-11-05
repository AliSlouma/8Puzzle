import java.util.*;

public class Search {

    int [][] goalMat = new int[][]{{0,1,2},{3,4,5},{6,7,8}};
    Board goal = new Board(goalMat);
    public boolean BFS(Board initialState){
        int nodesExpanded =0;
        Queue<Board> frontier = new LinkedList<>();
        Set<Board> explored = new HashSet<>();
        frontier.add(initialState);

        while (!frontier.isEmpty()){
            Board state = frontier.poll();
            state.drawBoard();
            explored.add(state);
            nodesExpanded++;
            if(goalTest(state)){
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

    public boolean depthFirstSearch(Board initialState){
        Stack<Board> frontier = new Stack<>();
        Set<Board> explored = new HashSet<>();
        frontier.push(initialState);
        int nodesExpanded =0;
        while (!frontier.isEmpty()){
            Board state = frontier.pop();
            explored.add(state);
//            System.out.println(explored.size());
            nodesExpanded++;
            state.drawBoard();

            if(goalTest(state)){
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
    public Boolean goalTest(Board state){
        return state.equals(goal);
    }
}
