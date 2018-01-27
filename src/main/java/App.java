import controller.Controller;
import model.Model;
import view.View;

/**
 * @author Anton Rogoza
 */

public class App {
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new View());
        controller.processUser();
    }
}
