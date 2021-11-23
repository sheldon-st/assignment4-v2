package controller.commands;

import controller.IMECommand;
import model.Filter;
import model.FilterImage;
import model.IMEModel;
import model.SingleImageModel;

/**
 * This class is used to apply a filter to the image.
 */
public class Components {

  /**
   * This class is used to apply a greyscale filter from the red component of an image.
   */
  public static class RedComponent implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new RedComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public RedComponent(String source, String dest) {
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
      tempImage.redScale();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }

  /**
   * This class is used to apply a greyscale filter from the blue component of an image.
   */
  public static class BlueComponent implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new BlueComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public BlueComponent(String source, String dest) {
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
      tempImage.blueScale();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }

  /**
   * This class is used to apply a greyscale filter from the green component of an image.
   */
  public static class GreenComponent implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new GreenComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public GreenComponent(String source, String dest) {
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
      tempImage.greenScale();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }

  /**
   * This class is used to apply a greyscale filter from the Intensity component of an image.
   */
  public static class IntensityComponent implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new IntensityComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public IntensityComponent(String source, String dest) {
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
      tempImage.intensityScale();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }

  /**
   * This class is used to apply a greyscale filter from the value component of an image.
   */
  public static class ValueComponent implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new ValueComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public ValueComponent(String source, String dest) {
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
      tempImage.valueScale();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }

  /**
   * This class is used to apply a greyscale filter from the Luma component of an image.
   */
  public static class LumaComponent implements IMECommand {
    private final String source;
    private final String dest;

    /**
     * This constructor is used to create a new LumaComponent command.
     *
     * @param source The source image.
     * @param dest   The destination image.
     */
    public LumaComponent(String source, String dest) {
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
      tempImage.lumaScale();
      m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
    }
  }
}
