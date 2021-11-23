package controller.commands;

import controller.IMECommand;
import model.Filter;
import model.FilterImage;
import model.IMEModel;
import model.SingleImageModel;

/**
 * This class is used to flip the image horizontally.
 */
public class HorizontalFlip implements IMECommand {
  private final String source;
  private final String dest;

  /**
   * Constructor.
   *
   * @param source the source file
   * @param dest   the destination file
   */
  public HorizontalFlip(String source, String dest) {
    this.source = source;
    this.dest = dest;
  }

  /**
   * Execute the command.
   *
   * @param m the model
   */
  @Override
  public void startProgram(IMEModel m) {
    Filter tempImage =
            new FilterImage(m.images.get(source).getImage());
    tempImage.flipHorizontally();
    m.images.put(dest, new SingleImageModel(tempImage.getNewImage()));
  }
}
