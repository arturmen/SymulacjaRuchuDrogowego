package views;

import models.Direction;
import models.FieldType;

import java.util.Scanner;

import static models.Direction.*;


public class AskForTurnView {

    public Direction act(){

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        if(line=="r"){
            return RIGHT;
        }
        else if(line=="l"){
            return LEFT;
        }
        else if(line=="u"){
            return UP;
        }
        else if(line=="d"){
            return DOWN;
        }
        return UP;

    }


    public void showInstruction(){
        System.out.println("Nastepny ruch - wciśnij klawisz r,l,u,d");
        return;
    }
}
