package models;

import views.AskForTurnView;
import views.ShowMapView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static models.FieldType.CROSS;
import static models.FieldType.ROAD;
import static models.FieldType.USERCAR;

public class Sternik {
    private Map map;
    private List<Car> carList;
    private boolean crossFlag = false;

    public Sternik(Map map){

        this.map = map;
        this.carList = map.getCarList();

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

            if(moveCar(car) && car instanceof UserCar){
                crossFlag = true;
            }
            if(moveCar(car)){
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

        carList = map.getCarList();
        for(Car car :carList){
            car.setMovesDone(0);
        }
        crossFlag = false;
    }
        private boolean moveCar(Car car){
            Position position = car.getPosition();
            switch(car.getDirection()) {
                case UP:
                    if(map.getMapArray()[position.getX()-1][position.getY()]== CROSS ) {
                        //map = makeTurnAfterUP(askForTurnView.act(),userCar,map);
                        return true;
                    }
                    map.getMapArray()[position.getX()-1][position.getY()] = USERCAR;
                    map.getMapArray()[position.getX()][position.getY()] = ROAD;
                case DOWN:
                    if(map.getMapArray()[position.getX()+1][position.getY()]== CROSS ) {
                        //map = makeTurnAfterDOWN(askForTurnView.act(),Car,map);
                        return true;
                    }
                    map.getMapArray()[position.getX()+1][position.getY()] = USERCAR;
                    map.getMapArray()[position.getX()][position.getY()] = ROAD;
                case RIGHT:
                    if(map.getMapArray()[position.getX()][position.getY()+1]== CROSS ) {
                        //map = makeTurnAfterRIGHT(askForTurnView.act(),userCar,map);
                        return true;
                    }
                    map.getMapArray()[position.getX()][position.getY()+1] = USERCAR;
                    map.getMapArray()[position.getX()][position.getY()] = ROAD;
                case LEFT:
                    if(map.getMapArray()[position.getX()][position.getY()-1]== CROSS ) {
                       // map = makeTurnAfterLeft(askForTurnView.act(),userCar,map);
                        return true;
                    }
                    map.getMapArray()[position.getX()][position.getY()-1] = USERCAR;
                    map.getMapArray()[position.getX()][position.getY()] = ROAD;
            }
            return false;
        }

    public void crossTurn(Direction direction,Map map){
        UserCar userCar = map.getUserCar();
        Direction directionBefore = userCar.getDirection();
            switch(directionBefore){
                case UP:
                    makeTurnAfterUP(direction,userCar);
                case DOWN:
                    makeTurnAfterDOWN(direction,userCar);
                case RIGHT:
                    makeTurnAfterRIGHT(direction,userCar);
                case LEFT:
                    makeTurnAfterLeft(direction, userCar);

            }

    }

    public void makeTurnAfterUP(Direction act, Car car) {
        Position position = car.getPosition();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()-2][car.position.getY()] = USERCAR;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()][car.position.getY()-1] = USERCAR;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()-1][car.position.getY()+1] = USERCAR;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()-2][car.position.getY()-2] = USERCAR;
        }
        return;
    }

    public Map makeTurnAfterDOWN(Direction act, Car car) {
        Position position = car.getPosition();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()][car.position.getY()+1] = USERCAR;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()+2][car.position.getY()] = USERCAR;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()+2][car.position.getY()+2] = USERCAR;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()+1][car.position.getY()-1] = USERCAR;
        }
        return map;
    }

    public Map makeTurnAfterRIGHT(Direction act, Car car) {
        Position position = car.getPosition();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()-2][car.position.getY()+2] = USERCAR;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()+1][car.position.getY()+1] = USERCAR;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()][car.position.getY()+2] = USERCAR;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()-1][car.position.getY()] = USERCAR;
        }
        return map;
    }

    public Map makeTurnAfterLeft(Direction act, Car car) {
        Position position = car.getPosition();
        switch(act){
            case UP:
                car.setDirection(Direction.UP);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()-1][car.position.getY()-1] = USERCAR;
            case DOWN:
                car.setDirection(Direction.DOWN);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()+1][car.position.getY()-2] = USERCAR;
            case RIGHT:
                car.setDirection(Direction.RIGHT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()+1][car.position.getY()] = USERCAR;
            case LEFT:
                car.setDirection(Direction.LEFT);
                map.getMapArray()[car.position.getX()][car.position.getY()] = ROAD;
                map.getMapArray()[car.position.getX()][car.position.getY()-2] = USERCAR;
        }
        return map;
    }




}



