import java.awt.*;
import java.util.*;
import java.util.List;

public class Board {
    private final int[][] currentState;
    private int[] zeroIndecis = new int[]{0, 0};
    private int depth;
    private static Map<Integer, Point> idealStates;
    private Board parent = null;

    public static Map<Integer, Point> getIdealStates(int dim) {
        if (idealStates == null) {
            int i = 0;
            idealStates = new HashMap<>();
            //change this to parameter
            for (; i < dim * dim; i++) {
                idealStates.put(i, new Point(i / dim, i % dim));
            }
        }
        return idealStates;
    }

    public Board(int[][] currentState) {
        this.currentState = currentState;
        depth = 1 ;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[0].length; j++) {
                if (currentState[i][j] == 0)
                    this.zeroIndecis = new int[]{i, j};
            }
        }
    }

    public int[][] getCurrentState() {
        return currentState;
    }

    public int[] getZeroPoint() {
        return zeroIndecis;
    }

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public List<Board> getNeighbours() {
        List<Board> neighbours = new ArrayList<>();
        // swap zero with each of its available neighbours to get a new state
        int zeroX = zeroIndecis[0], zeroY = zeroIndecis[1];
        if (zeroY < 2) {
            int tempValue = currentState[zeroX][zeroY + 1];
            neighbours.add(generateNewState(tempValue, zeroX, zeroY + 1));
        }
        if (zeroY > 0) {
            int tempValue = currentState[zeroX][zeroY - 1];
            neighbours.add(generateNewState(tempValue, zeroX, zeroY - 1));
        }
        if (zeroX < 2) {
            int tempValue = currentState[zeroX + 1][zeroY];
            neighbours.add(generateNewState(tempValue, zeroX + 1, zeroY));
        }
        if (zeroX > 0) {
            int tempValue = currentState[zeroX - 1][zeroY];
            neighbours.add(generateNewState(tempValue, zeroX - 1, zeroY));
        }
        for (Board neighbour : neighbours) neighbour.setDepth(depth + 1);
        return neighbours;
    }

    private Board generateNewState(int tempValue, int xIdx, int yIdx) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[0].length; j++) {
                if (currentState[i][j] == 0)
                    newState[i][j] = tempValue;
                else if (i == xIdx && j == yIdx)
                    newState[i][j] = 0;
                else
                    newState[i][j] = currentState[i][j];
            }
        }
        Board newBoard = new Board(newState);
        newBoard.setParent(this);
        return newBoard;
    }

    @Override
    public boolean equals(Object obj) {
        // null check
        if (obj == null) {
            return false;
        }

        // this instance check
        if (this == obj) {
            return true;
        }

        // Actual value check
        int[][] objState = ((Board) obj).getCurrentState();
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[0].length; j++) {
                if (currentState[i][j] != objState[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] ints : currentState) {
            for (int j = 0; j < currentState[0].length; j++) {
                stringBuilder.append(ints[j]);
            }
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    public void drawBoard() {
        System.out.println("_____________");
        for (int[] ints : currentState)
            System.out.println("| " + ints[0] + " | " + ints[1] + " | " + ints[2] + " |");
        System.out.println("_____________");
    }

}
