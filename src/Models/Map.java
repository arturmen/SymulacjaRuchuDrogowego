package models;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private FieldType[][] mapArray;
    private List <Car> carList;

    public Map(List<List<FieldType>> lines) {
        mapArray = new FieldType[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            mapArray[i] = new FieldType[lines.get(i).size()];
            lines.get(i).toArray(mapArray[i]);
        }
    }
    public FieldType[][] getMapArray() {
        return mapArray;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }
}