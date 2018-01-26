package Models;

public class Map {
    private int[][] mapArray;

    public Map(){
        for (int[] ints : mapArray = new int[2][2]) {
            ints[0]=1;
            ints[1]=2;
        }

    }

    public int[][] getMapArray() {
        return mapArray;
    }
}
