package models;


import models.*;

import java.lang.reflect.Field;

import static models.Direction.*;
import static models.FieldType.GRASS;
import static models.FieldType.ROAD;
import static models.FieldType.USERCAR;


public class UserCarDefinitions {
    private Map mapArray;
    public int speed = 3;
    private UserCar userCar;

    public UserCarDefinitions(Map mapArray) {
        this.mapArray = mapArray;
        userCar = new UserCar(getUserCarPosition(),speed,getUserCarDirection());
    }

    public Position getUserCarPosition() {
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

    public Direction getUserCarDirection() {
        Position position1 = getUserCarPosition();
        if(mapArray.getMapArray()[position1.getX()+1][position1.getY()] == GRASS) return RIGHT;
        if(mapArray.getMapArray()[position1.getX()][position1.getY()+1] == GRASS) return UP;
        if(mapArray.getMapArray()[position1.getX()][position1.getY()-1] == GRASS) return DOWN;
        if(mapArray.getMapArray()[position1.getX()-1][position1.getY()] == GRASS) return LEFT;
        return RIGHT;
    }
}

