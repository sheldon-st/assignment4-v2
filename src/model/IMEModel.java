package model;

import java.util.HashMap;


/**
 * Class to represent IME model, which holds a gallery of loaded images and edits made to them.
 */
public class IMEModel {
  public HashMap<String, IModel> images;
  public IModel currentImage;
  public HistogramModel currentHistogram;

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
  public SingleImageModel getImage(String source) {
    return (SingleImageModel) images.get(source);
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

  }

  /**
   * Saves an image from the gallery to a file.
   *
   * @param imageName Name of the image to save
   */
  public void saveImage(String imagePath, String imageName) {
    SingleImageModel image = (SingleImageModel) images.get(imageName);

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
