package models;

import views.AskForTurnView;
import views.ShowMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static models.Direction.*;
import static models.FieldType.*;

public class Pilot {
    private Map map;
    private List<Car> carList;
    private boolean crossFlag = false;
    private static final Random RANDOM = new Random();

    public Pilot(Map map){

        this.map = map;
        this.carList = new ArrayList<>();

    }


    public MoveResult makeMove(Direction direction){
        if(carList.isEmpty()){
            prepareTurn();
        }
        return moveCars(direction);

    }


    private MoveResult moveCars(Direction direction){
        List<Car> doneCars = new ArrayList<>();
        for(Car car : carList){
            boolean resultCar = moveCar(car);
            if(car instanceof UserCar && resultCar) {
                crossFlag = true;
                car.setMovesDone(car.getSpeed()-1);
            }
            else if(resultCar){
                car.setMovesDone(car.getSpeed()-1);
                if(!crossOtherTurn(randomDirection(),map,car)) {
                    do{ }
                        while(!crossOtherTurn(randomDirection(),map,car));
                }
            }
            car.addMovesDone();
            if(car.getMovesDone() == car.getSpeed()){
                doneCars.add(car);
            }
        }
        carList.removeAll(doneCars);

        if(carList.isEmpty() && crossFlag == true){
            return MoveResult.CROSS;
        }
        if(carList.isEmpty()){
            return MoveResult.END;
        }
        return MoveResult.CONTINUE;

    }

    private void prepareTurn(){

        carList = new ArrayList<>(map.getCarList());
        for(Car car :carList){
            car.setMovesDone(0);
        }
        crossFlag = false;
    }

    private boolean moveCar(Car car){
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        if(map.getMapArray()[i][j] == USERCAR) {
            switch (car.getDirection()) {
                case UP:
                    if (map.getMapArray()[position.getX() - 1][position.getY()] == END) {
                        map.setMapArrayField(i,j,ROAD);
                        map.setMapArrayField(i, j-1, USERCAR);
                        car.setPosition(new Position(i , j-1));
                        car.setDirection(DOWN);
                        break;
                    }
                    if (map.getMapArray()[position.getX() - 1][position.getY()] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i - 1, j, USERCAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i - 1, j));
                    break;
                case DOWN:
                    if (map.getMapArray()[position.getX() + 1][position.getY()] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i, j+1, USERCAR);
                        car.setPosition(new Position(i , j+1));
                        car.setDirection(UP);
                        break;
                    }
                    if (map.getMapArray()[position.getX() + 1][position.getY()] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i + 1, j, USERCAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i + 1, j));
                    break;
                case RIGHT:
                    if (map.getMapArray()[position.getX()][position.getY() + 1] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i-1, j, USERCAR);
                        car.setPosition(new Position(i-1 , j));
                        car.setDirection(LEFT);
                        break;
                    }
                    if (map.getMapArray()[position.getX()][position.getY() + 1] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i, j + 1, USERCAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i, j + 1));
                    break;
                case LEFT:
                    if (map.getMapArray()[position.getX()][position.getY() - 1] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i+1, j, USERCAR);
                        car.setPosition(new Position(i+1 , j));
                        car.setDirection(RIGHT);
                        break;
                    }
                    if (map.getMapArray()[position.getX()][position.getY() - 1] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i, j - 1, USERCAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i, j - 1));
                    break;
            }
            return false;
        }
        else{
            switch (car.getDirection()) {
                case UP:
                    if (map.getMapArray()[position.getX() - 1][position.getY()] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i, j-1, CAR);
                        car.setPosition(new Position(i , j-1));
                        car.setDirection(DOWN);
                        break;
                    }
                    if (map.getMapArray()[position.getX() - 1][position.getY()] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i - 1, j, CAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i - 1, j));
                    break;
                case DOWN:
                    if (map.getMapArray()[position.getX() + 1][position.getY()] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i, j+1, CAR);
                        car.setPosition(new Position(i , j+1));
                        car.setDirection(UP);
                        break;
                    }
                    if (map.getMapArray()[position.getX() + 1][position.getY()] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i + 1, j, CAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i + 1, j));
                    break;
                case RIGHT:
                    if (map.getMapArray()[position.getX()][position.getY() + 1] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i-1, j, CAR);
                        car.setPosition(new Position(i-1 , j));
                        car.setDirection(LEFT);
                        break;
                    }
                    if (map.getMapArray()[position.getX()][position.getY() + 1] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i, j + 1, CAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i, j + 1));
                    break;
                case LEFT:
                    if (map.getMapArray()[position.getX()][position.getY() - 1] == END) {
                        map.setMapArrayField(i, j, ROAD);
                        map.setMapArrayField(i+1, j, CAR);
                        car.setPosition(new Position(i+1 , j));
                        car.setDirection(RIGHT);
                        break;
                    }
                    if (map.getMapArray()[position.getX()][position.getY() - 1] == CROSS) {
                        return true;
                    }
                    map.setMapArrayField(i, j - 1, CAR);
                    map.setMapArrayField(i, j, ROAD);
                    car.setPosition(new Position(i, j - 1));
                    break;
            }
            return false;
        }
    }

    public boolean crossTurn(Direction direction,Map map){
        UserCar userCar = map.getUserCar();
        Direction directionBefore = userCar.getDirection();
            switch(directionBefore){
                case UP:
                    if(!makeTurnAfterUP(direction,userCar)) return false;
                    return true;
                case DOWN:
                    if(!makeTurnAfterDOWN(direction,userCar)) return false;
                    return true;
                case RIGHT:
                    if(!makeTurnAfterRIGHT(direction,userCar)) return false;
                    return true;
                case LEFT:
                    if(!makeTurnAfterLeft(direction, userCar)) return false;
                    return true;

            }
        return true;
    }

    public boolean makeTurnAfterUP(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i-2][j] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-2,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j));
                return true;
            case DOWN:
                if(map.getMapArray()[i][j-1] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-1));
                return true;
            case RIGHT:
                if(map.getMapArray()[i-1][j+1] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i-1,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j+1));
                return true;
            case LEFT:
                if(map.getMapArray()[i-2][j-2] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i-2,j-2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j-2));
                return true;
        }
        return true;
    }

    public boolean makeTurnAfterDOWN(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i][j+1] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+1));
                return true;
            case DOWN:
                if(map.getMapArray()[i+2][j] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+2,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+2,j));
                return true;
            case RIGHT:
                if(map.getMapArray()[i+2][j+2] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i+2,j+2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+2,j+2));
                return true;
            case LEFT:
                if(map.getMapArray()[i+1][j-1] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i+1,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j-1));
                return true;
        }
        return true;
    }

    public boolean makeTurnAfterRIGHT(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i-2][j+2] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-2,j+2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j+2));
                return true;
            case DOWN:
                if(map.getMapArray()[i+1][j+1] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+1,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j+1));
                return true;
            case RIGHT:
                if(map.getMapArray()[i][j+2] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i,j+2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+2));
                return true;
            case LEFT:
                if(map.getMapArray()[i-1][j] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i-1,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j));
                return true;
        }
        return true;
    }

    public boolean makeTurnAfterLeft(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i-1][j-1] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-1,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j-1));
                return true;
            case DOWN:
                if(map.getMapArray()[i+1][j-2] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+1,j-2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j-2));
                return true;
            case RIGHT:
                if(map.getMapArray()[i+1][j] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i+1,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j));
                return true;
            case LEFT:
                if(map.getMapArray()[i][j-2] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i,j-2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-2));
                return true;
        }
        return true;
    }

    public boolean crossOtherTurn(Direction direction,Map map,Car car){
        Direction directionBefore = car.getDirection();
        switch(directionBefore){
            case UP:
                if(!makeOtherTurnAfterUP(direction,car)) return false;
                return true;
            case DOWN:
                if(!makeOtherTurnAfterDOWN(direction,car)) return false;
                return true;
            case RIGHT:
                if(!makeOtherTurnAfterRIGHT(direction,car)) return false;
                return true;
            case LEFT:
                if(!makeOtherTurnAfterLeft(direction, car)) return false;
                return true;

        }
        return true;
    }
    public boolean makeOtherTurnAfterUP(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i-2][j] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-2,j,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j));
                return true;
            case DOWN:
                if(map.getMapArray()[i][j-1] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i,j-1,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-1));
                return true;
            case RIGHT:
                if(map.getMapArray()[i-1][j+1] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i-1,j+1,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j+1));
                return true;
            case LEFT:
                if(map.getMapArray()[i-2][j-2] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i-2,j-2,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j-2));
                return true;
        }
        return true;
    }

    public boolean makeOtherTurnAfterDOWN(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i][j+1] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i,j+1,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+1));
                return true;
            case DOWN:
                if(map.getMapArray()[i+2][j] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+2,j,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+2,j));
                return true;
            case RIGHT:
                if(map.getMapArray()[i+2][j+2] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i+2,j+2,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+2,j+2));
                return true;
            case LEFT:
                if(map.getMapArray()[i+1][j-1] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i+1,j-1,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j-1));
                return true;
        }
        return true;
    }

    public boolean makeOtherTurnAfterRIGHT(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i-2][j+2] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-2,j+2,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j+2));
                return true;
            case DOWN:
                if(map.getMapArray()[i+1][j+1] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+1,j+1,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j+1));
                return true;
            case RIGHT:
                if(map.getMapArray()[i][j+2] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i,j+2,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+2));
                return true;
            case LEFT:
                if(map.getMapArray()[i-1][j] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i-1,j,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j));
                return true;
        }
        return true;
    }

    public boolean makeOtherTurnAfterLeft(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                if(map.getMapArray()[i-1][j-1] == GRASS) return false;
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-1,j-1,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j-1));
                return true;
            case DOWN:
                if(map.getMapArray()[i+1][j-2] == GRASS) return false;
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+1,j-2,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j-2));
                return true;
            case RIGHT:
                if(map.getMapArray()[i+1][j] == GRASS) return false;
                car.setDirection(RIGHT);
                map.setMapArrayField(i+1,j,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j));
                return true;
            case LEFT:
                if(map.getMapArray()[i][j-2] == GRASS) return false;
                car.setDirection(LEFT);
                map.setMapArrayField(i,j-2,CAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-2));
                return true;
        }
        return true;
    }

    private Direction randomDirection() {
        int pick = new Random().nextInt(Direction.values().length);
        return Direction.values()[pick];
    }


}



