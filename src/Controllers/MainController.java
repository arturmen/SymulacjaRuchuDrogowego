package Controllers;


import DataAccess.MapReader;
import Models.Map;
import Views.ShowMapView;

public class MainController {

    private Map map;

    public void home(){

        MapReader reader = new MapReader();
        map = reader.readMap();
        ShowMapView showMapView = new ShowMapView(map);
        showMapView.showMap();
    }


}
