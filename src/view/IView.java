package view;

import javax.swing.*;

/**
 * Interface for the view.
 */
public interface IView {
  /**
   * Prints the given string to the view.
   */
  void showString(String s);

  /**
   * menu to the view.
   */
  void showOptions();

  /**
   * Prints the error message to the console .
   */
  void showOptionError();

  /**
   * Prints the input prefix to the console.
   */
  void prefix();

  /**
   * Adds the given gui component to the view.
   */
  void addPanel(JPanel panel);

  /**
   * Updates the historgram
   */
  void updateSourceHistogram(ImageIcon[] images);

  /**
   * Updates the current image.
   */
  void updateSourceImage(ImageIcon image);

  /**
   * Update preview image.
   */
  void updatePreviewImage(ImageIcon image);

  /**
   * Update the histogram of the preview image.
   */
  void updatePreviewHistogram(ImageIcon[] images);


}
