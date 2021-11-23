package model;

/**
 * This class implements the Filter interface. It represents a class with the possible filters on
 * an image such as grayscale, value, luma, and intensity component.
 */
public class FilterImage implements Filter {
  static final double[][] blurImageKernal = new double[][]{
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
          {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
  };
  static final double[][] sharpenImageKernal = new double[][]{
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
  };
  private final int width;
  private final int height;
  protected int[][][] image;


  /**
   * Constructor for FilterImage takes in image to apply the filter on and copies into a new blank
   * image and applies the filter.
   *
   * @param image image to be filtered
   */
  public FilterImage(int[][][] image) {
    this.image = copyImage(image);
    clamp();
    this.height = image.length;
    this.width = image[0].length;
  }

  /**
   * Blur Image uses the blurImageKernal to blur the image.
   */
  @Override
  public void blurImage() {
    processImageWithKernel(blurImageKernal);
    clamp();
  }

  /**
   * Sharpen Image uses the sharpenImageKernal to sharpen the image.
   */
  @Override
  public void sharpenImage() {
    processImageWithKernel(sharpenImageKernal);
    clamp();
  }

  /**
   * Processes the image with a kernal.
   */
  private void processImageWithKernel(double[][] kernal) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = processPixelWithKernal(i, j, kernal, k);
        }
      }
    }
  }

  /**
   * Process a pixel with a kernal.
   */
  private int processPixelWithKernal(int x, int y, double[][] kernal, int channel) {
    int sum = 0;
    int kernalXOffset = kernal.length / 2;
    int kernalYOffset = kernal[0].length / 2;
    for (int i = 0; i < kernal.length; i++) {
      for (int j = 0; j < kernal[0].length; j++) {
        if (x + i - kernalXOffset >= 0 && x + i - kernalXOffset < this.height &&
                y + j - kernalYOffset >= 0 && y + j - kernalYOffset < this.width) {
          sum += (int) (image[x + i - kernalXOffset][y + j - kernalYOffset][channel] * kernal[i][j]);
        }
      }
    }
    return sum;
  }

  /**
   * Clamps the RGB values to be between 0 and 255.
   */
  private void clamp() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          if (this.image[i][j][k] > 255) {
            this.image[i][j][k] = 255;
          } else if (image[i][j][k] < 0) {
            this.image[i][j][k] = 0;
          }
        }
      }
    }
  }


  /**
   * Returns the image array.
   *
   * @return image array
   */
  @Override
  public int[][][] getNewImage() {
    clamp();
    return this.image;
  }

  /**
   * Greyscale from red component.
   */
  @Override
  public void redScale() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        image[i][j][1] = image[i][j][0];
        image[i][j][2] = image[i][j][0];
      }
    }
    clamp();
  }

  /**
   * Greyscale from blue component.
   */
  @Override
  public void blueScale() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        image[i][j][0] = image[i][j][2];
        image[i][j][1] = image[i][j][2];
      }
    }
    clamp();
  }

  /**
   * Greyscale from green component.
   */
  @Override
  public void greenScale() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        image[i][j][0] = image[i][j][1];
        image[i][j][2] = image[i][j][1];
      }
    }
    clamp();
  }

  /**
   * Greyscale from value component.
   */
  @Override
  public void valueScale() {
    int maxVal;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        maxVal = Math.max(Math.max(image[i][j][0], image[i][j][1]), image[i][j][2]);
        image[i][j][0] = maxVal;
        image[i][j][1] = maxVal;
        image[i][j][2] = maxVal;
      }
    }
    clamp();
  }

  /**
   * Greyscale from intensity component.
   */
  @Override
  public void intensityScale() {
    int avgVal;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        avgVal = (image[i][j][0] + image[i][j][1] + image[i][j][2]) / 3;
        image[i][j][0] = avgVal;
        image[i][j][1] = avgVal;
        image[i][j][2] = avgVal;
      }
    }
    clamp();
  }

  /**
   * Greyscale from luma component.
   */
  @Override
  public void lumaScale() {
    int luma;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        luma = (int) Math.round((0.2126 * image[i][j][0]) + (0.7152 * image[i][j][1]) +
                (0.0722 * image[i][j][2]));
        image[i][j][0] = luma;
        image[i][j][1] = luma;
        image[i][j][2] = luma;
      }
    }
    clamp();
  }

  /**
   * Copies the given image.
   *
   * @param imgToCopy given image to make a copy of
   * @return new image array
   */
  @Override
  public int[][][] copyImage(int[][][] imgToCopy) {
    int[][][] newImage = new int[imgToCopy.length][imgToCopy[0].length][3];
    for (int i = 0; i < imgToCopy.length; i++) {
      for (int j = 0; j < imgToCopy[0].length; j++) {
        newImage[i][j][0] = imgToCopy[i][j][0];
        newImage[i][j][1] = imgToCopy[i][j][1];
        newImage[i][j][2] = imgToCopy[i][j][2];
      }
    }
    return newImage;
  }

  /**
   * Flips the image horizontally.
   */
  @Override
  public void flipHorizontally() {
    System.out.println("flipping image: ");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        int[] tmp = image[i][width - j - 1].clone();
        image[i][width - j - 1] = image[i][j].clone();
        image[i][j] = tmp;
      }
    }
    clamp();
  }

  /**
   * Flips the image vertically.
   */
  @Override
  public void flipVertically() {
    System.out.println("flipping image: ");
    System.out.println(this.height);
    System.out.println(this.width);
    System.out.println(this.height);

    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        int[] tmp = image[height - i - 1][j].clone();
        image[height - i - 1][j] = image[i][j].clone();
        image[i][j] = tmp;
      }
    }
    clamp();
  }

  /**
   * Brightens the image by the given increment.
   *
   * @param increment the amount to brighten the image by
   */
  @Override
  public void brighten(int increment) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = Math.min(image[i][j][k] + increment, 255);
        }
      }
    }
    clamp();
  }

}