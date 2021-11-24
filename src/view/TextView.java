package view;

import java.io.PrintStream;

import javax.swing.*;

/**
 * This class represents a text view and implements the View interface.
 */
public class TextView implements view.IView {
  private final PrintStream out;

  public TextView(PrintStream out) {
    this.out = out;
  }

  /**
   * Prints the given string to the view as a line.
   */
  public void showString(String s) {
    out.println(s);
  }

  /**
   * Prints the given string to the view.
   */
  public void prefix() {

    out.print(">");

  }

  /**
   * Adds the given gui component to the view.
   *
   * @param panel
   */
  @Override
  public void addPanel(JPanel panel) {

  }

  /**
   * Updates the historgram
   *
   * @param images
   */
  @Override
  public void updateSourceHistogram(ImageIcon[] images) {

  }

  /**
   * Updates the current image.
   *
   * @param image
   */
  @Override
  public void updateSourceImage(ImageIcon image) {

  }

  /**
   * Update preview image.
   *
   * @param image
   */
  @Override
  public void updatePreviewImage(ImageIcon image) {

  }

  /**
   * Update the histogram of the preview image.
   *
   * @param images
   */
  @Override
  public void updatePreviewHistogram(ImageIcon[] images) {

  }


  /**
   * Prints the menu to the view.
   */
  public void showOptions() {
    //print the UI
    out.println("Menu: ");
    out.println(" load [image-path] [image-name]: loads an image from [image-path] " +
            "to be referred to as [image-name]" + "\n   Supported file types are .ppm, .png, .jpg and .bmp");
    out.println(" -save [image-path] [image-name]: saves image [image-name] " +
            "to path [image-path].\n  File type is determined by extension of provided [image-path]. "
            + "Supported file types are .ppm, .png, .jpg and .bmp");
    // flips
    out.println(" -horizontal-flip [image-name] [dest-image-name]: " +
            "flips [image-name] horizontally and saves to gallery as [dest-image-name]");
    out.println(" -vertical-flip [image-name] [dest-image-name]: " +
            "flips [image-name] vertically and saves to gallery as [dest-image-name]");
    out.println(" -brighten [increment] [image-name] [dest-image-name]" +
            " brighten the [image-name] by the given increment (+/-) and saves to gallery "
            + "as [dest-image-name]");
    out.println(" Component visualization: creates a greyscale image of specified component " +
            "from [image-name] and saves to gallery as as [dest-image-name]");
    out.println("  -red-component [image-name] [dest-image-name]");
    out.println("  -green-component [image-name] [dest-image-name]");
    out.println("  -blue-component [image-name] [dest-image-name]");
    out.println("  -value-component [image-name] [dest-image-name]");
    out.println("  -luma-component [image-name] [dest-image-name]");
    out.println("  -intensity-component [image-name] [dest-image-name]");
    // brighten
    out.println(" -run [script-path]: processes the given .txt file as a list of commands");
    out.println(" -M: See the full menu again");
    out.println(" -Q: Quit the program");
    out.println("********************************************************************************");

  }

  /**
   * Prints the error message to the view.
   */
  public void showOptionError() {
    out.print("\nInvalid option. Please try again.");
  }
}
