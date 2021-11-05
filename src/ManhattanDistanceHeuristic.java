import java.awt.*;
import java.util.Map;

public class ManhattanDistanceHeuristic {
    public int[][] calculateHeuristic (int [][] obj1){
        Map<Integer, Point> idealStates = Board.getIdealStates(obj1.length);
        for(int i=0 ;i<obj1.length*obj1.length;i++)System.out.println(i + " : " + idealStates.get(i).x +"," +idealStates.get(i).y);
        return null ;
    }
}
