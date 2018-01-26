package views;

import models.FieldType;
import models.Map;

public class ShowMapView {
    private Map mapArray;

    public ShowMapView(Map mapArray) {
        this.mapArray = mapArray;
    }

    public void showMap(){
        for(FieldType[] fieldTypes : mapArray.getMapArray()){
            System.out.println("dupa");
        }
    }



}
