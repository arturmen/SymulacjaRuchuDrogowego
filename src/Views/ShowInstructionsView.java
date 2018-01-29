package views;

import java.util.Scanner;

public class ShowInstructionsView {

    public boolean act(){
        showInstruction();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        if(line=="q"){
            return false;
        }
        return true;
    }

    private void showInstruction(){
        System.out.println("Nastepny ruch - wciśnij jakikolwiek klawisz");
        System.out.println("Zakończ program - wciśnij q");
    }


}
