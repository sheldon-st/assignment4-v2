package model;

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
}
