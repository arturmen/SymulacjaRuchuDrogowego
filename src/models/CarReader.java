package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static models.Direction.*;
import static models.Direction.RIGHT;
import static models.FieldType.GRASS;

public class CarReader {
    public Map map;
    public List<Car> carList;
    Random generator = new Random();

    public CarReader(Map map){
        this.map = map;
        carList = new ArrayList<>();
    }

    public List<Car> readCars(){
        for(int i=0;i<map.getMapArray().length;i++){
            for(int j=0; j<map.getMapArray()[i].length;j++){
                if(map.getMapArray()[i][j] == FieldType.CAR ){
                    Position position = new Position(i,j);
                    carList.add(new ComputerCar(position,generator.nextInt(3)+1,getCarDirection(position)));
                }
                if(map.getMapArray()[i][j] == FieldType.USERCAR){
                    Position position = new Position(i,j);
                    carList.add(new UserCar(position,3,getCarDirection(position)));
                }
            }
        }
        return carList;
    }

    public Direction getCarDirection(Position position) {

        if(map.getMapArray()[position.getX()+1][position.getY()] == GRASS) return RIGHT;
        if(map.getMapArray()[position.getX()][position.getY()+1] == GRASS) return UP;
        if(map.getMapArray()[position.getX()][position.getY()-1] == GRASS) return DOWN;
        if(map.getMapArray()[position.getX()-1][position.getY()] == GRASS) return LEFT;
        return RIGHT;
    }

}
