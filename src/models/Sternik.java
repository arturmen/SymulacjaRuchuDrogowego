package models;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Sternik {
    private Map map;
    private List<Car> carList;

    public Sternik(Map map){

        this.map = map;
        this.carList = map.getCarList();

    }


    public MoveResult makeMove(Direction direction){
        if(checkIfListIsEmpty(carList)){
            prepareTurn();
        }

        return moveCars(direction);

    }

    private boolean checkIfListIsEmpty(List<Car> carList){
        if(carList == null) return true;
        return false;
    }
    private MoveResult moveCars(Direction direction){
        List<Car> doneCars = new ArrayList<>();
        for(Car car : carList){

            moveCar(car);
            car.addMovesDone();
            if(car.getMovesDone() == car.getSpeed()){
                doneCars.add(car);
            }

        }

        carList.removeAll(doneCars);

        return MoveResult.END;
    }

    private void prepareTurn(){

        carList = map.getCarList();
        for(Car car :carList){
            car.setMovesDone(0);
        }
    }

}


    private void moveCar(Car car){

                return;
        }
