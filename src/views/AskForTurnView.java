package views;

import models.Direction;
import models.FieldType;

import java.util.Scanner;

import static models.Direction.*;


public class AskForTurnView {

    public Direction act(){

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while(true) {
            if (line.equals("r")) {
                return RIGHT;
            } else if (line.equals("l")) {
                return LEFT;
            } else if (line.equals("u")) {
                return UP;
            } else if (line.equals("d")) {
                return DOWN;
            }

            showBadInput();
            line = scanner.nextLine();
        }

    }

    private void showBadInput() {
        System.out.println("złe wejście");
    }


    public void showInstruction(){
        System.out.println("Nastepny ruch - wciśnij klawisz r,l,u,d");
        return;
    }
}
