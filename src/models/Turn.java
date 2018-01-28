package models;

import views.ShowMapView;

public class Turn {
    public Map map;
    public UserCar userCar;
    public int speed;


    public Turn(Map map, UserCar userCar){
        speed = userCar.speed;
        this.map = map;
        this.userCar = userCar;

    }
    public Map makeTurn(){
        for(int i=0;i<speed;i++){
            MakeQueue makeQueue = new MakeQueue();
            makeQueue.MakeQueue(map,userCar);
            ShowMapView showMapView = new ShowMapView(map);
            showMapView.showMap();
        }
        return map;
    }
}
