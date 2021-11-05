import java.util.*;

public class AStarSearch {
    Heuristics heuristic  ;
    public AStarSearch(Heuristics heuristics){
        heuristic = heuristics ;
    }
    public AStarSearch(){
        heuristic = new ManhattanDistanceHeuristic();
    }
    public void search(Board initialState){
        PriorityQueue<Map.Entry<Map.Entry<Double,Integer>,Board>> pq  = new PriorityQueue<>(new BoardComparator());
        Set<Board> visited = new HashSet<>();
        pq.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(0.0, 0), initialState));
        while(!pq.isEmpty()){
            Map.Entry<Map.Entry<Double,Integer>,Board> curr = pq.peek();
            Board currBoard = curr.getValue();
            Double[][] currHeuristic = heuristic.calculateHeuristic(currBoard.getCurrentState());
            currBoard.getNeighbours();
        }
    }

    public static class BoardComparator implements Comparator<Map.Entry<Map.Entry<Double,Integer>,Board>>{

        @Override
        public int compare(Map.Entry<Map.Entry<Double, Integer>, Board> o1, Map.Entry<Map.Entry<Double, Integer>, Board> o2) {
            if(o1.getKey().getKey().equals(o2.getKey().getKey()))
                return -o1.getKey().getValue().compareTo(o2.getKey().getValue());
            else return -o1.getKey().getKey().compareTo(o2.getKey().getKey());

        }
    }
}
