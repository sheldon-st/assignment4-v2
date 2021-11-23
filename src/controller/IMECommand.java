package controller;

import model.IMEModel;

/**
 * Created by blerner on 10/10/16.
 */
public interface IMECommand {

  /**
   * Execute the command.
   *
   * @param m The model to use.
   */
  void startProgram(IMEModel m);
}
