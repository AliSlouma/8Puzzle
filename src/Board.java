import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] currentState = new int[3][3];
    private int[] zeroIndecis = new int[]{0,0};

    public Board(int[][] currentState) {
        this.currentState = currentState;
        for(int i = 0; i< currentState.length; i++){
            for(int j = 0; j< currentState[0].length; j++){
                if(currentState[i][j]==0)
                    this.zeroIndecis = new int[]{i, j};
            }
        }
    }

    public int[][] getCurrentState() {
        return currentState;
    }

    public List<Board> getNeighbours() {
        List<Board> neighbours = new ArrayList<>();
        // swap zero with each of its available neighbours to get a new state
        int zeroX = zeroIndecis[0], zeroY = zeroIndecis[1];
        if(zeroX>0){
            int tempValue = currentState[zeroX-1][zeroY];
            neighbours.add(generateNewState(tempValue, zeroX-1, zeroY));
        }
        if(zeroX<2){
            int tempValue = currentState[zeroX+1][zeroY];
            neighbours.add(generateNewState(tempValue, zeroX+1, zeroY));
        }
        if(zeroY>0){
            int tempValue = currentState[zeroX][zeroY-1];
            neighbours.add(generateNewState(tempValue, zeroX, zeroY-1));
        }
        if(zeroY<2){
            int tempValue = currentState[zeroX][zeroY+1];
            neighbours.add(generateNewState(tempValue, zeroX, zeroY+1));
        }
        return neighbours;
    }
    private Board generateNewState(int tempValue, int xIdx, int yIdx){
        int[][] newState = new int[3][3];
        for(int i = 0; i< currentState.length; i++){
            for(int j = 0; j< currentState[0].length; j++){
                if(currentState[i][j]==0)
                    newState[i][j] = 0;
                else if(i==xIdx && j==yIdx)
                    newState[i][j] = tempValue;
                else
                    newState[i][j] = currentState[i][j];
            }
        }
        return new Board(newState);
    }
}
