import java.awt.*;
import java.util.Map;
// provided Board as its parameter,
// Calculates Euclidean Distance for all items,
// with respect to its ideal positions stored as a static map in Board Class,
// and returns sum of all distances to be used as a heuristic in A* search
public class ManhattanDistanceHeuristic implements Heuristics{
    public double calculateHeuristic (int [][] obj1){
        if(obj1==null)return -1 ;
        Map<Integer, Point> idealStates = Board.getIdealStates(obj1.length);
        //for(int i=0 ;i<obj1.length*obj1.length;i++)System.out.println(i + " : " + idealStates.get(i).x +"," +idealStates.get(i).y);
        double heuristic  = 0.0;
        for(int i=0 ;i<obj1.length;i++)
            for (int j=0 ;j<obj1.length;j++){
                Point idealPos = idealStates.get(obj1[i][j]);
                heuristic += (Math.abs(idealPos.x - i) + Math.abs(idealPos.y - j));
            }
        return heuristic;
    }
}
