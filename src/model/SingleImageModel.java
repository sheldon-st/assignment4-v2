package model;

/**
 * This class represents a single image model.
 */
public class SingleImageModel implements IModel {
  private final String imagePath;
  private int[][][] image;

  /**
   * Constructor for SingleImageModel with no parameters.
   */
  public SingleImageModel() {
    imagePath = "";
    image = null;
  }

  /**
   * Constructor for SingleImageModel with given image path.
   *
   * @param imagePath given image path
   */
  public SingleImageModel(String imagePath) {
    this.imagePath = imagePath;
    this.setImage();
  }

  /**
   * Constructor for SingleImageModel with given image.
   *
   * @param image given image
   */
  public SingleImageModel(int[][][] image) {
    this.imagePath = "";
    this.image = image;
  }

  /**
   * Sets the image from the given image path.
   */
  @Override
  public void setImage() {
    this.image = ImageUtil.readPPM(this.imagePath);
  }

  /**
   * Gets the image array.
   */
  @Override
  public int[][][] getImage() {
    return this.image;
  }

  /**
   * Overrides the equals method.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SingleImageModel) {
      SingleImageModel other = (SingleImageModel) o;
      return this.image.equals(other.image);
    }
    return false;
  }

  /**
   * Overrides the hashCode method.
   */
  @Override
  public int hashCode() {
    return image.hashCode();
  }
}