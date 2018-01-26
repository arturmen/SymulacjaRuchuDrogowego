package controllers;


import dataAccess.MapReader;
import models.Map;
import views.ErrorView;
import views.ShowMapView;

import java.io.IOException;

public class MainController {

    private Map map;

    public void home(){

        MapReader reader = new MapReader();
        try {
            map = reader.readMap();
        } catch (IOException e) {
            ErrorView errorView = new ErrorView();
            errorView.showError(e);
        }
        ShowMapView showMapView = new ShowMapView(map);
        showMapView.showMap();
    }


}
