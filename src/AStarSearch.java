import java.util.*;

public class AStarSearch {
    Heuristics heuristic;

    public AStarSearch(Heuristics heuristics) {
        heuristic = heuristics;
    }

    public AStarSearch() {
        heuristic = new ManhattanDistanceHeuristic();
    }

    public boolean search(Board initialState) {
        int step = 0;
        PriorityQueue<Map.Entry<Map.Entry<Double, Integer>, Board>> pq = new PriorityQueue<>(new BoardComparator());
        Set<Board> visited = new HashSet<>();
        double initialHeuristic = heuristic.calculateHeuristic(initialState.getCurrentState());
        pq.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(initialHeuristic, 0), initialState));
        while (!pq.isEmpty()) {
            Map.Entry<Map.Entry<Double, Integer>, Board> curr = pq.poll();
            Map.Entry<Double, Integer> currCost = curr.getKey();
            Board currBoard = curr.getValue();
            List<Board> neighbours = currBoard.getNeighbours();
            double currHeuristic = heuristic.calculateHeuristic(currBoard.getCurrentState());

            if (visited.contains(currBoard)) continue;

            System.out.println("\nstep : " + step);
            currBoard.drawBoard();
            System.out.println("h(state) = " + currHeuristic + ", g(state) = " + currCost.getValue() + ", f(state) = " + currCost.getKey());

            if (Search.goalTest(currBoard)) {
                System.out.println("found solution in " + step + " steps");
                System.out.println("total cost = " + currCost.getValue());
                return true;
            }
            visited.add(currBoard);

            if (!neighbours.isEmpty())
                System.out.println("\npossible neighbours : ");
            for (Board nextBoard : neighbours) {
                double nextHeuristic = heuristic.calculateHeuristic(nextBoard.getCurrentState());
                int nextCost = currCost.getValue() + 1;
                pq.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(nextHeuristic + nextCost, nextCost), nextBoard));

                nextBoard.drawBoard();
                System.out.println("h(state) = " + nextHeuristic + ", g(state) = " + nextCost + ", f(state) = " + (nextHeuristic + nextCost));

            }
            step++;
        }
        System.out.println("didn't find solution in " + step + " steps");
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
}
