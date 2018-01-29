package views;


import models.FieldType;
import models.Map;

public class ShowMapView {
    private Map mapArray;


    public ShowMapView(Map mapArray) {
        this.mapArray = mapArray;
    }

    public void showMap() {
        for (int i=0; i<mapArray.getMapArray().length;i++) {
            for (int j=0; j<mapArray.getMapArray()[i].length;j++){
                System.out.print(getFieldChar(mapArray.getMapArray()[i][j]));
            }
            System.out.println();

        }
        System.out.println();

    }

    private char getFieldChar(FieldType type) {
        switch (type) {
            case ROAD:
                return 'x';

            case GRASS:
                return '0';

            case CROSS:
                return '@';

            case CAR:
                return 'A';

            case USERCAR:
                return 'U';
            case END:
                return '=';


            default:
                throw new IllegalArgumentException("Argument should be ROAD, GRASS, CAR, END, CROSS or USERCAR");
        }


    }
}
