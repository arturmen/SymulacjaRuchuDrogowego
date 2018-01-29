package models;

import views.AskForTurnView;
import views.ShowMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static models.FieldType.*;

public class Sternik {
    private Map map;
    private List<Car> carList;
    private boolean crossFlag = false;

    public Sternik(Map map){

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
                car.setMovesDone(car.getSpeed());
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
        switch(car.getDirection()) {
            case UP:
                if(map.getMapArray()[position.getX()-1][position.getY()]== CROSS ) {
                    return true;
                }
                map.setMapArrayField(i-1,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j));
                break;
            case DOWN:
                if(map.getMapArray()[position.getX()+1][position.getY()]== CROSS ) {
                    return true;
                }
                map.setMapArrayField(i+1,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j));
                break;
            case RIGHT:
                if(map.getMapArray()[position.getX()][position.getY()+1]== CROSS ) {
                    return true;
                }
                map.setMapArrayField(i,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+1));
                break;
            case LEFT:
                if(map.getMapArray()[position.getX()][position.getY()-1]== CROSS ) {
                    return true;
                }
                map.setMapArrayField(i,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-1));
                break;
        }
        return false;
    }

    public void crossTurn(Direction direction,Map map){
        UserCar userCar = map.getUserCar();
        Direction directionBefore = userCar.getDirection();
            switch(directionBefore){
                case UP:
                    makeTurnAfterUP(direction,userCar);
                    break;
                case DOWN:
                    makeTurnAfterDOWN(direction,userCar);
                    break;
                case RIGHT:
                    makeTurnAfterRIGHT(direction,userCar);
                    break;
                case LEFT:
                    makeTurnAfterLeft(direction, userCar);
                    break;

            }

    }

    public void makeTurnAfterUP(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-2,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j));
                break;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-1));
                break;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.setMapArrayField(i-1,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j+1));
                break;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.setMapArrayField(i-2,j-2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j-2));
                break;
        }
        return;
    }

    public Map makeTurnAfterDOWN(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.setMapArrayField(i,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+1));
                break;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+2,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+2,j));
                break;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.setMapArrayField(i+2,j+2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+2,j+2));
                break;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.setMapArrayField(i+1,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j-1));
                break;
        }
        return map;
    }

    public Map makeTurnAfterRIGHT(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-2,j+2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-2,j+2));
                break;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+1,j+1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j+1));
                break;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.setMapArrayField(i,j+2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j+2));
                break;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.setMapArrayField(i-1,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j));
                break;
        }
        return map;
    }

    public Map makeTurnAfterLeft(Direction act, Car car) {
        Position position = car.getPosition();
        int i = position.getX();
        int j = position.getY();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.setMapArrayField(i-1,j-1,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i-1,j-1));
                break;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.setMapArrayField(i+1,j-2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j-2));
                break;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.setMapArrayField(i+1,j,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i+1,j));
                break;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.setMapArrayField(i,j-2,USERCAR);
                map.setMapArrayField(i,j,ROAD);
                car.setPosition(new Position(i,j-2));
                break;
        }
        return map;
    }




}



