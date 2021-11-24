package model;

import javax.swing.*;

/**
 * This interface represents an image model.
 */
public interface IModel {

  /**
   * Set the image array.
   */
  void setImage();

  /**
   * Get the image array.
   *
   * @return image array
   */
  int[][][] getImage();

  /**
   * Gets an ImageIcon of the image.
   * /
   **/
  ImageIcon getImageIcon();

}
