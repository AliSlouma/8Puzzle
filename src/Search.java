import java.util.*;

public class Search {

    private static final int[][] goalMat = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    private static final Board goal = new Board(goalMat);
    private int maxDepth;

    public boolean BFS(Board initialState) {
        maxDepth = 0;
        Queue<Board> frontier = new LinkedList<>();
        Set<Board> explored = new HashSet<>();
        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            Board state = frontier.poll();
            explored.add(state);
            maxDepth = Math.max(state.getDepth(), maxDepth);
            //state.drawBoard();
            if (goalTest(state)) {
                getPathToGoal(state);
                System.out.println("nodes expanded = " + explored.size());
                return true;
            }


            for (Board neighbour : state.getNeighbours()) {
                if (!frontier.contains(neighbour) && !explored.contains(neighbour))
                    frontier.add(neighbour);
            }

        }
        System.out.println("nodes expanded = " + explored.size());
        return false;
    }

    private void getPathToGoal(Board state) {
        Stack<Board> boards = new Stack<>();
        while (state != null) {
            boards.push(state);
            state = state.getParent();
        }
        System.out.println("__________________________________");
        int boardsSize = boards.size();
        while (!boards.isEmpty()) {
            boards.pop().drawBoard();
        }
        System.out.println("Search Depth = " + boardsSize);
        System.out.println("Maximum Search Depth = " + maxDepth);
        System.out.println("cost of path = " + (boardsSize - 1));
    }

    public boolean DFS(Board initialState) {
        maxDepth = 0;
        Stack<Board> frontier = new Stack<>();
        Set<Board> explored = new HashSet<>();
        frontier.push(initialState);
        while (!frontier.isEmpty()) {
            Board state = frontier.pop();
            explored.add(state);
            maxDepth = Math.max(state.getDepth(), maxDepth);
//            System.out.println(explored.size());
            //state.drawBoard();

            if (goalTest(state)) {
                getPathToGoal(state);
                System.out.println("nodes expanded = " + explored.size());
                return true;
            }

            for (Board neighbour : state.getNeighbours()) {
                if (!frontier.contains(neighbour) && !explored.contains(neighbour))
                    frontier.push(neighbour);
            }
        }
        System.out.println("nodes expanded = " + explored.size());
        return false;
    }

    public boolean AStar(Board initialState, Heuristics heuristic, boolean printFlag) {
        maxDepth = 0;
        int step = 0;
        //  priority queue to store expanded nodes with its actual cost and total heuristic cost
        // <<F(X),G(X)>, Board>
        PriorityQueue<Map.Entry<Map.Entry<Double, Integer>, Board>> frontier = new PriorityQueue<>(new BoardComparator());
        Set<Board> explored = new HashSet<>(); // set carries explored nodes
        Set<Board> found = new HashSet<>(); // set carries expanded nodes
        double initialHeuristic = heuristic.calculateHeuristic(initialState.getCurrentState());
        frontier.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(initialHeuristic, 0), initialState)); // add initial state to the priority queue
        found.add(initialState);
        while (!frontier.isEmpty()) {
            Map.Entry<Map.Entry<Double, Integer>, Board> curr = frontier.poll();
            Map.Entry<Double, Integer> currCost = curr.getKey();
            Board currBoard = curr.getValue();
            double currHeuristic = heuristic.calculateHeuristic(currBoard.getCurrentState());
            maxDepth = Math.max(currBoard.getDepth(), maxDepth);

            if (explored.contains(currBoard)) continue; //if this board was explored with lower cost then skip it
            explored.add(currBoard);  // set this node as visited

            if (Search.goalTest(currBoard)) { // if the current state is the goal state then print the results and end the search
                getPathToGoal(currBoard);
                System.out.println("nodes expanded = " + found.size());
                if (printFlag) {
                    System.out.println("\nfound solution in " + step + " steps");
                    System.out.println("total cost = " + currCost.getValue());
                }
                return true;
            }

            List<Board> neighbours = currBoard.getNeighbours(); // get neighbours of current state
            found.addAll(neighbours); // set this nodes as expanded but not visited yet
            for (Board nextBoard : neighbours) { // add neighbours of current state to the priority queue
                double nextHeuristic = heuristic.calculateHeuristic(nextBoard.getCurrentState()); //H(X)
                int nextCost = currCost.getValue() + 1; // G(X)
                frontier.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(nextHeuristic + nextCost, nextCost), nextBoard));
            }
            step++;

            if (printFlag) {
                System.out.println("\nstep : " + step);
                currBoard.drawBoard();
                System.out.println("h(state) = " + currHeuristic + ", g(state) = " + currCost.getValue() + ", f(state) = " + currCost.getKey());
                if (!neighbours.isEmpty()) {
                    System.out.println("\npossible neighbours : ");
                    for (Board nextBoard : neighbours) nextBoard.drawBoard();
                }
            }
        }
        // if priority queue is empty and no solution found
        System.out.println("didn't find solution in " + step + " steps");
        System.out.println("nodes expanded = " + found.size());
        return false;
    }

    public static class BoardComparator implements Comparator<Map.Entry<Map.Entry<Double, Integer>, Board>> {

        @Override
        public int compare(Map.Entry<Map.Entry<Double, Integer>, Board> o1, Map.Entry<Map.Entry<Double, Integer>, Board> o2) {
            if (o1.getKey().getKey().equals(o2.getKey().getKey()))
                return o1.getKey().getValue().compareTo(o2.getKey().getValue());
            else return o1.getKey().getKey().compareTo(o2.getKey().getKey());

        }
    }

    public static Boolean goalTest(Board state) {
        return state.equals(goal);
    }
}
