package model;

import java.util.HashMap;



/**
 * Class to represent IME model, which holds a gallery of loaded images and edits made to them.
 */
public class IMEModel {
  public HashMap<String, IModel> images;

  public IModel currentImage;
  public HistogramModel currentHistogram;

  public IModel editedImage;
  public HistogramModel editedHistogram;

  /**
   * Constructor creates a blank hashmap to load images in.
   */
  public IMEModel() {
    images = new HashMap<>();
  }

  /**
   * Gets the image with the given name from the gallery.
   *
   * @param source Name the name of the image to get
   * @return The image with the given name.
   */
  public IModel getImage(String source) {
    return images.get(source);
  }

  /**
   * Loads an image to the gallery.
   *
   * @param imageName Name of the image to add
   * @param image     Image to add
   */
  public void loadImage(String imageName, IModel image) {
    images.put(imageName, image);
    currentImage = image;
    currentHistogram = new HistogramModel((SingleImageModel) image);

    this.editedImage = image;
    this.editedHistogram = new HistogramModel((SingleImageModel) image);

    // this.editedImage = image;
   //  this.editedHistogram = new HistogramModel((IModel) image);
  }

  /**
   * Saves an image from the gallery to a file.
   *
   * @param imageName Name of the image to save
   */
  public void saveImage(String imagePath, String imageName) {
    IModel image = images.get(imageName);

    ImageUtil.writeImage(image.getImage(), image.getImage()[0].length, image.getImage().length,
            imagePath);
    System.out.println("Height: " + image.getImage().length);
    System.out.println("Width: " + image.getImage()[0].length);
  }


  /**
   * Returns the gallery list.
   *
   * @return the gallery.
   */
  public IModel[] getGallery() {
    return images.values().toArray(new IModel[0]);
  }
}
