package controller.commands;

import controller.IMECommand;
import model.Filter;
import model.FilterImage;
import model.IMEModel;
import model.SingleImageModel;

/**
 * This class is used to flip the image vertically.
 */
public class VerticalFlip implements IMECommand {
  private final String source;
  private final String dest;

  /**
   * Constructor for VerticalFlip.
   *
   * @param source the source file
   * @param dest   the destination file
   */
  public VerticalFlip(String source, String dest) {
    this.source = source;
    this.dest = dest;
  }

  /**
   * Executes the command.
   */
  @Override
  public void startProgram(IMEModel m) {
    Filter tempImage =
            new FilterImage(m.images.get(source).getImage());
    tempImage.flipVertically();
    m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
  }
}
