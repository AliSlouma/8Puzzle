import java.awt.*;
import java.util.Map;
public class EuclideanDistanceHeuristic implements Heuristics{
    public Double[][] calculateHeuristic (int [][] obj1){
        if(obj1==null)return null ;
        Map<Integer, Point> idealStates = Board.getIdealStates(obj1.length);
        Double [][] heuristic  = new Double [obj1.length][obj1[0].length];
        for(int i=0 ;i<obj1.length;i++)
            for (int j=0 ;j<obj1.length;j++){
                Point idealPos = idealStates.get(obj1[i][j]);
                heuristic[i][j] = Math.sqrt((idealPos.x-i)*(idealPos.x-i) +(idealPos.y-j)*(idealPos.y-j));
            }
        return heuristic;
    }
}
