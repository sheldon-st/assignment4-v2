package model;

/**
 * This interface represents a filter effect for an image.
 */
public interface Filter {
  /**
   * Method to apply red-component filter on image. This means that the red-component value is
   * also applied to the blue and green components of RGB for each pixel.
   */
  void redScale();

  /**
   * Method to apply blue-component filter on image. This means that the blue-component value is
   * also applied to the red and green components of RGB for each pixel.
   */
  void blueScale();

  /**
   * Method to apply green-component filter on image. This means that the green-component value is
   * also applied to the red and blue components of RGB for each pixel.
   */
  void greenScale();

  /**
   * Method to apply value-component filter on image. This means that the max value of RGB
   * components is applied for each pixel.
   */
  void valueScale();

  /**
   * Method to apply intensity-component filter on image. This means that the average value of RGB
   * components is applied for each pixel.
   */
  void intensityScale();

  /**
   * Method to apply luma-component filter on image. This means that the weighted sum of RGB
   * components is applied for each pixel.
   */
  void lumaScale();


  /**
   * Sharpen Image.
   */
  void sharpenImage();

  /**
   * Blur Image
   */
  void blurImage();


  /**
   * Method to return new filtered image.
   *
   * @return new filtered image
   */
  int[][][] getNewImage();

  /**
   * Method to make a copy of given image to apply filters to.
   *
   * @param imgToCopy given image to make a copy of
   * @return new copied image
   */
  int[][][] copyImage(int[][][] imgToCopy);

  /**
   * Flips the image array horizontally.
   */
  void flipHorizontally();

  /**
   * Flips the image array vertically.
   */
  void flipVertically();

  /**
   * This method brightens the image array by the given increment.
   *
   * @param increment the increment to brighten the image by.
   */
  void brighten(int increment);
}


