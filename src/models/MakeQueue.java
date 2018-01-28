package models;

import models.Direction;
import models.Map;
import models.Position;
import models.UserCar;
import views.AskForTurnView;
import views.ShowMapView;

import static models.Direction.UP;
import static models.FieldType.CROSS;
import static models.FieldType.ROAD;
import static models.FieldType.USERCAR;

public class MakeQueue {

    public Map MakeQueue(Map map, UserCar userCar){
        Position position = userCar.getPosition();
        switch(userCar.direction) {
            case UP:
                if(map.getMapArray()[position.getX()-1][position.getY()]== CROSS ) {
                    ShowMapView showMapView = new ShowMapView(map);
                    showMapView.showMap();
                    AskForTurnView askForTurnView = new AskForTurnView();
                    askForTurnView.showInstruction();
                    map = makeTurnAfterUP(askForTurnView.act(),userCar,map);
                    return map;
                }
                map.getMapArray()[position.getX()-1][position.getY()] = USERCAR;
                map.getMapArray()[position.getX()][position.getY()] = ROAD;
            case DOWN:
                if(map.getMapArray()[position.getX()+1][position.getY()]== CROSS ) {
                    ShowMapView showMapView = new ShowMapView(map);
                    showMapView.showMap();
                    AskForTurnView askForTurnView = new AskForTurnView();
                    askForTurnView.showInstruction();
                    map = makeTurnAfterDOWN(askForTurnView.act(),userCar,map);
                    return map;
                }
                map.getMapArray()[position.getX()+1][position.getY()] = USERCAR;
                map.getMapArray()[position.getX()][position.getY()] = ROAD;
            case RIGHT:
                if(map.getMapArray()[position.getX()][position.getY()+1]== CROSS ) {
                    ShowMapView showMapView = new ShowMapView(map);
                    showMapView.showMap();
                    AskForTurnView askForTurnView = new AskForTurnView();
                    askForTurnView.showInstruction();
                    map = makeTurnAfterRIGHT(askForTurnView.act(),userCar,map);
                    return map;
                }
                map.getMapArray()[position.getX()][position.getY()+1] = USERCAR;
                map.getMapArray()[position.getX()][position.getY()] = ROAD;
            case LEFT:
                if(map.getMapArray()[position.getX()][position.getY()-1]== CROSS ) {
                    ShowMapView showMapView = new ShowMapView(map);
                    showMapView.showMap();
                    AskForTurnView askForTurnView = new AskForTurnView();
                    askForTurnView.showInstruction();
                    map = makeTurnAfterLeft(askForTurnView.act(),userCar,map);
                    return map;
                }
                map.getMapArray()[position.getX()][position.getY()-1] = USERCAR;
                map.getMapArray()[position.getX()][position.getY()] = ROAD;
        }
        return map;
    }

    public Map makeTurnAfterUP(Direction act, UserCar userCar, Map map) {
        Position position = userCar.getPosition();
        switch(act){
            case UP:
                userCar.setDirection(Direction.UP);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()-2][userCar.position.getY()] = USERCAR;
            case DOWN:
                userCar.setDirection(Direction.DOWN);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()-1] = USERCAR;
            case RIGHT:
                userCar.setDirection(Direction.RIGHT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()-1][userCar.position.getY()+1] = USERCAR;
            case LEFT:
                userCar.setDirection(Direction.LEFT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()-2][userCar.position.getY()-2] = USERCAR;
        }
        return map;
    }

    public Map makeTurnAfterDOWN(Direction act, UserCar userCar, Map map) {
        Position position = userCar.getPosition();
        switch(act){
            case UP:
                userCar.setDirection(Direction.UP);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()+1] = USERCAR;
            case DOWN:
                userCar.setDirection(Direction.DOWN);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()+2][userCar.position.getY()] = USERCAR;
            case RIGHT:
                userCar.setDirection(Direction.RIGHT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()+2][userCar.position.getY()+2] = USERCAR;
            case LEFT:
                userCar.setDirection(Direction.LEFT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()+1][userCar.position.getY()-1] = USERCAR;
        }
        return map;
    }

    public Map makeTurnAfterRIGHT(Direction act, UserCar userCar, Map map) {
        Position position = userCar.getPosition();
        switch(act){
            case UP:
                userCar.setDirection(Direction.UP);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()-2][userCar.position.getY()+2] = USERCAR;
            case DOWN:
                userCar.setDirection(Direction.DOWN);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()+1][userCar.position.getY()+1] = USERCAR;
            case RIGHT:
                userCar.setDirection(Direction.RIGHT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()+2] = USERCAR;
            case LEFT:
                userCar.setDirection(Direction.LEFT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()-1][userCar.position.getY()] = USERCAR;
        }
        return map;
    }

    public Map makeTurnAfterLeft(Direction act, UserCar userCar, Map map) {
        Position position = userCar.getPosition();
        switch(act){
            case UP:
                userCar.setDirection(Direction.UP);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()-1][userCar.position.getY()-1] = USERCAR;
            case DOWN:
                userCar.setDirection(Direction.DOWN);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()+1][userCar.position.getY()-2] = USERCAR;
            case RIGHT:
                userCar.setDirection(Direction.RIGHT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()+1][userCar.position.getY()] = USERCAR;
            case LEFT:
                userCar.setDirection(Direction.LEFT);
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()] = ROAD;
                map.getMapArray()[userCar.position.getX()][userCar.position.getY()-2] = USERCAR;
        }
        return map;
    }
}
