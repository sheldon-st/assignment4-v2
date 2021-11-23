package controller.commands;

import controller.IMECommand;
import model.Filter;
import model.FilterImage;
import model.IMEModel;
import model.SingleImageModel;

/**
 * This class is used to apply a filter to the image.
 */
public class KernalFilter {

  /**
   * This class is used to apply a greyscale filter from the red component of an image.
   */
  public static class BlurFilter implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new RedComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public BlurFilter(String source, String dest) {
      this.source = source;
      this.dest = dest;
    }


    /**
     * This method is used to execute the command.
     *
     * @param m The model of the program.
     */
    @Override
    public void startProgram(IMEModel m) {
      Filter tempImage =
              new FilterImage(m.images.get(source).getImage());
      tempImage.blurImage();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }

  public static class SharpenImage implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new RedComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public SharpenImage(String source, String dest) {
      this.source = source;
      this.dest = dest;
    }


    /**
     * This method is used to execute the command.
     *
     * @param m The model of the program.
     */
    @Override
    public void startProgram(IMEModel m) {
      Filter tempImage =
              new FilterImage(m.images.get(source).getImage());
      tempImage.sharpenImage();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }
}