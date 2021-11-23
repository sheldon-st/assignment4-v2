package controller.commands;

import controller.IMECommand;
import model.IMEModel;

/**
 * This class represents the command to load a file.
 */
public class Save implements IMECommand {
  private final String path;
  private final String name;

  /**
   * Constructor.
   *
   * @param path the path of the file
   * @param name the name of the file
   */
  public Save(String path, String name) {
    this.path = path;
    this.name = name;
  }

  /**
   * Executes the command.
   *
   * @param m the model
   */
  @Override
  public void startProgram(IMEModel m) {
    m.saveImage(path, name);
  }
}
