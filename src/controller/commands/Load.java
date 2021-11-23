package controller.commands;

import controller.IMECommand;
import model.IMEModel;
import model.IModel;
import model.SingleImageModel;

/**
 * This class represents the command to load a file.
 */
public class Load implements IMECommand {
  private final String path;
  private final String name;

  /**
   * Constructor.
   *
   * @param path The path to the file.
   * @param name The name of the file.
   */
  public Load(String path, String name) {
    this.path = path;
    this.name = name;
  }

  /**
   * Executes the command.
   *
   * @param m The model to execute the command on.
   */
  @Override
  public void startProgram(IMEModel m) {
    IModel tempImage = new SingleImageModel(path);
    m.loadImage(name, tempImage);
  }
}
