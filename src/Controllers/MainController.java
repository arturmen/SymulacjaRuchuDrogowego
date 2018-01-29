package controllers;


import dataAccess.MapReader;
import models.*;
import views.AskForTurnView;
import views.ErrorView;
import views.ShowInstructionsView;
import views.ShowMapView;

import java.io.IOException;

public class MainController {

    public Map map;

    public void home(){

        MapReader reader = new MapReader();
        try {
            map = reader.readMap();
        } catch (IOException e) {
            ErrorView errorView = new ErrorView();
            errorView.showError(e);
        }
        CarFounder carFounder = new CarFounder(map);
        map.setCarList(carFounder.readCars());


        ShowMapView showMapView = new ShowMapView(map);
        showMapView.showMap();


        ShowInstructionsView showInstructionsView = new ShowInstructionsView();


        Sternik sternik = new Sternik(map);

        while(showInstructionsView.act()) {
            Direction direction = null;
            while (true) {
                MoveResult moveResult = sternik.makeMove(direction);
                showMapView.showMap();
                if (moveResult == MoveResult.END) {
                    break;
                }
                if (moveResult == MoveResult.CONTINUE){
                    continue;
                }
                if (moveResult == MoveResult.CROSS){
                    AskForTurnView askForTurnView = new AskForTurnView();
                    askForTurnView.showInstruction();
                    direction = askForTurnView.act();
                    sternik.crossTurn(direction,map);


                    break;
                }
            }
        }





    }

}
