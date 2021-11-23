import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.IMECommandController;
import model.IMEModel;
import view.TextView;

/**
 * This class tests the TextView class.
 */
public class IViewTest {
  /**
   * Tests if a string is printed to the console.
   */
  @Test
  public void testShowString() {
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStreamCaptor));

    PrintStream printStream = new PrintStream(System.out);
    TextView view = new TextView(printStream);
    IMEModel model = new IMEModel();
    IMECommandController controller = new IMECommandController(model, System.in, view);
    view.showString("test");
    Assert.assertEquals("test", outputStreamCaptor.toString()
            .trim());
  }

  /**
   * Tests if a string is printed to the console.
   */
  @Test
  public void testShowOptions() {
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStreamCaptor));

    PrintStream printStream = new PrintStream(System.out);
    TextView view = new TextView(printStream);
    IMEModel model = new IMEModel();
    IMECommandController controller = new IMECommandController(model, System.in, view);
    view.showOptions();
    Assert.assertEquals("Menu: \n" +
                    " load [image-path] [image-name]: loads an image from [image-path] to be referred to as [image-name]\n" +
                    "   Supported file types are .ppm, .png, .jpg and .bmp\n" +
                    " -save [image-path] [image-name]: saves image [image-name] to path [image-path].\n" +
                    "  File type is determined by extension of provided [image-path]. Supported file types are .ppm, .png, .jpg and .bmp\n" +
                    " -horizontal-flip [image-name] [dest-image-name]: flips [image-name] horizontally and saves to gallery as [dest-image-name]\n" +
                    " -vertical-flip [image-name] [dest-image-name]: flips [image-name] vertically and saves to gallery as [dest-image-name]\n" +
                    " -brighten [increment] [image-name] [dest-image-name] brighten the [image-name] by the given increment (+/-) and saves to gallery as [dest-image-name]\n" +
                    " Component visualization: creates a greyscale image of specified component from [image-name] and saves to gallery as as [dest-image-name]\n" +
                    "  -red-component [image-name] [dest-image-name]\n" +
                    "  -green-component [image-name] [dest-image-name]\n" +
                    "  -blue-component [image-name] [dest-image-name]\n" +
                    "  -value-component [image-name] [dest-image-name]\n" +
                    "  -luma-component [image-name] [dest-image-name]\n" +
                    "  -intensity-component [image-name] [dest-image-name]\n" +
                    " -run [script-path]: processes the given .txt file as a list of commands\n" +
                    " -M: See the full menu again\n" +
                    " -Q: Quit the program\n" +
                    "********************************************************************************",
            outputStreamCaptor.toString().trim());
  }
}