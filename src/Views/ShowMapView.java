package Views;

import Models.Map;

public class ShowMapView {
    private Map mapArray;

    public ShowMapView(Map mapArray) {
        this.mapArray = mapArray;
    }

    public void showMap(){
        for(int[] ints : mapArray.getMapArray()){
            System.out.print(ints[0]);
            System.out.print(ints[1]);
            System.out.println();
        }
    }



}
