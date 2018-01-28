package models;


import models.*;

import java.lang.reflect.Field;
import java.util.Random;

import static models.Direction.*;
import static models.FieldType.GRASS;
import static models.FieldType.ROAD;
import static models.FieldType.USERCAR;


public class CarDefinitions {
    private Map mapArray;
    Random generator = new Random();
    public int speed = generator.nextInt(3)+1;
    private Car car;

    public CarDefinitions(Map mapArray) {
        this.mapArray = mapArray;
        computerCar = new ComputerCar(getCarPosition(),speed,getCarDirection());
    }

    public Position getCarPosition() {
        for (int i = 0; i < mapArray.getMapArray().length; i++) {
            for (int j = 0; j < mapArray.getMapArray()[i].length; j++) {
                if (mapArray.getMapArray()[i][j] == USERCAR) {
                    Position position = new Position(i, j);
                    return position;

                }
            }
        }
        Position position1 = new Position(0,0);
        return position1;
    }

    public Direction getCarDirection() {
        Position position1 = getCarPosition();
        if(mapArray.getMapArray()[position1.getX()+1][position1.getY()] == GRASS) return RIGHT;
        if(mapArray.getMapArray()[position1.getX()][position1.getY()+1] == GRASS) return UP;
        if(mapArray.getMapArray()[position1.getX()][position1.getY()-1] == GRASS) return DOWN;
        if(mapArray.getMapArray()[position1.getX()-1][position1.getY()] == GRASS) return LEFT;
        return RIGHT;
    }
}

