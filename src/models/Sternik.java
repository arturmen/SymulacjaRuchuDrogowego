package models;

public class Sternik {
    private Map map;
    private UserCar userCar;

    public Sternik(Map map){

        this.map = map;
        UserCarDefinitions userCarDefinitions = new UserCarDefinitions(map);
        UserCar userCar = new UserCar(userCarDefinitions.getUserCarPosition(),userCarDefinitions.speed,userCarDefinitions.getUserCarDirection());

    }


    public MoveResult makeMove(Direction direction){
        if(checkIfListIsEmpty()){
            prepareTurn();
        }

        return moveCars(direction);

    }

    private boolean checkIfListIsEmpty(){

        return false;
    }
    private MoveResult moveCars(Direction direction){
        return MoveResult.END;
    }
    private void prepareTurn(){

    }

}
