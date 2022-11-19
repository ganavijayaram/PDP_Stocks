import controller.MainControllerImpl;
import java.io.IOException;
import java.io.InputStreamReader;

import view.CommandView;
import view.CommandViewImpl;
/**
 * The main class for the program.
 */

public class Main {

  /**
   * Main method to begin the program.
   * @param args args
   * @throws IOException throws exception
   */
  public static void main(String[] args) throws IOException {
    CommandView view = new CommandViewImpl(System.out);
    new MainControllerImpl(new InputStreamReader(System.in), view).begin();
  }
}
