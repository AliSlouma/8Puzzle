import java.awt.*;
import java.util.Map;

public class ManhattanDistanceHeuristic implements Heuristics{
    public Double[][] calculateHeuristic (int [][] obj1){
        if(obj1==null)return null ;
        Map<Integer, Point> idealStates = Board.getIdealStates(obj1.length);
        for(int i=0 ;i<obj1.length*obj1.length;i++)System.out.println(i + " : " + idealStates.get(i).x +"," +idealStates.get(i).y);
        Double [][] heuristic  = new Double [obj1.length][obj1[0].length];
        for(int i=0 ;i<obj1.length;i++)
            for (int j=0 ;j<obj1.length;j++){
                Point idealPos = idealStates.get(obj1[i][j]);
                heuristic[i][j] = (double) (Math.abs(idealPos.x - i) + Math.abs(idealPos.y - j));
            }
        return heuristic;
    }
}
