package views;

public class ErrorView {

    public void showError(Exception e){
        System.out.println("cos poszlo nie tak: ");
        System.out.println(e.getMessage());

    }
}
