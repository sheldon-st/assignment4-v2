import javax.swing.*;

import controller.IController;
import controller.IMECommandController;
import model.IMEModel;
import view.IView;
import view.SwingView;
import view.TextView;

/**
 * This class is the main class of the program. It creates the view, model,
 * and controller.
 */
public class IMEProgramUI {
  /**
   * The main method.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    IMEModel model = new IMEModel();
   // IView view = new TextView(System.out);

    SwingView.setDefaultLookAndFeelDecorated(false);
    SwingView frame = new SwingView();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

    IController controller = new IMECommandController(model, System.in, frame);
    controller.startProgram();
  }
}
