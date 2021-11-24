package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import controller.commands.Brighten;
import controller.commands.Components;
import controller.commands.HorizontalFlip;
import controller.commands.KernalFilter;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.VerticalFlip;
import model.IMEModel;
import model.ImageUtil;
import view.IView;

/**
 * This is a controller that is very similar to the
 * CommandController class. The main difference
 * is that the main is replaced with the controller
 * method processCommand
 */
public class IMECommandController implements IController {
  private final IMEModel model;
  private final IView view;
  private Scanner s;

  /**
   * Constructor for the IMECommandController.
   *
   * @param in input stream
   */
  public IMECommandController(IMEModel model, InputStream in, IView view) {
    this.model = new IMEModel();
    this.view = view;

    this.s = new Scanner(in);
  }

  /**
   * This method is the main method of the controller.
   * It will take in a stream of commands and process it.
   */
  public void startProgram() {
    boolean quit = false;
    String[] line = null;
    view.showOptions();
    view.showString("Enter Command:");
    view.prefix();
    while (s.hasNextLine()) {
      while (!quit) {
        IMECommand cmd = null;
        line = s.nextLine().split(" ");
        try {
          switch (line[0].toLowerCase()) {
            case "load":
              cmd = new Load(line[1], line[2]);
              view.showString("Attempting to load image " + line[2] + " from " + line[1]
                      + "...");
              break;
            case "save":
              cmd = new Save(line[1], line[2]);
              view.showString("Attempting to save image " + line[2] + " to " + line[1] + "...");
              break;
            case "horizontal-flip":
              cmd = new HorizontalFlip(line[1], line[2]);
              view.showString("Attempting to create a copy of " + line[1] +
                      " flipped horizontally" +
                      " referred to henceforth by " + line[2] + "...");
              break;
            case "vertical-flip":
              cmd = new VerticalFlip(line[1], line[2]);
              view.showString("Attempting to create a copy of " + line[1] +
                      " flipped vertically" +
                      " referred to henceforth by " + line[2] + "...");
              break;
            case "brighten":
              try {
                int increment = Integer.parseInt(line[1]);
                cmd = new Brighten(increment, line[2], line[3]);
                view.showString("Attempting to create a copy of " + line[2] +
                        " brightened by " +
                        line[1] + ", referred to henceforth by " + line[3] + "...");
                break;
              } catch (NumberFormatException e) {
                view.showString("Increment must be an integer:" + cmd);
                break;
              }
            case "red-component":
              cmd = new Components.RedComponent(line[1], line[2]);
              view.showString("Attempting to create a greyscale copy of " + line[1] +
                      "'s red component, referred to henceforth by " + line[2] + "...");
              break;
            case "blur":
              cmd = new KernalFilter.BlurFilter(line[1], line[2]);
              view.showString("Attempting to create a blur copy of " + line[1] +
                      "'s image, referred to henceforth by " + line[2] + "...");
              break;
            case "sharpen":
              cmd = new KernalFilter.SharpenImage(line[1], line[2]);
              view.showString("Attempting to create a blur copy of " + line[1] +
                      "'s image, referred to henceforth by " + line[2] + "...");
              break;
            case "blue-component":
              cmd = new Components.BlueComponent(line[1], line[2]);
              view.showString("Attempting to create a greyscale copy of " + line[1] +
                      "'s blue component, referred to henceforth by " + line[2] + "...");
              break;
            case "green-component":
              cmd = new Components.GreenComponent(line[1], line[2]);
              view.showString("Attempting to create a greyscale copy of " + line[1] +
                      "'s green component, referred to henceforth by " + line[2] + "...");
              break;
            case "intensity-component":
              cmd = new Components.IntensityComponent(line[1], line[2]);
              view.showString("Created a greyscale copy of " + line[1] +
                      "'s intensity component, referred to henceforth by " + line[2] + "...");
              break;
            case "value-component":
              cmd = new Components.ValueComponent(line[1], line[2]);
              view.showString("Attempting to create a greyscale copy of " + line[1] +
                      "'s value component, referred to henceforth by " + line[2] + "...");
              break;
            case "luma-component":
              cmd = new Components.LumaComponent(line[1], line[2]);
              view.showString("Attempting to create a greyscale copy of " + line[1] +
                      "'s luma component, referred to henceforth by " + line[2] + "...");
              break;
            case "run":
              String data = new String(Files.readAllBytes(Paths.get(line[1])));
              this.s = new Scanner(data);
              view.showString("Attempting to run script at " + line[1] + "...");
              break;
            case "m":
              view.showOptions();
              break;
            case "q":
              quit = true;
              view.showString("Quitting program... Thank You!");
              break;
            default:
              view.showString(String.format("Unknown command :%s", line[0]));
              cmd = null;
              break;
          }
          if (cmd != null) {
            cmd.startProgram(model);
            view.updateSourceImage(model.currentImage.getImageIcon());
            view.updateSourceHistogram(model.currentHistogram.getHistogramImages());
            view.updatePreviewImage(model.currentImage.getImageIcon());
            view.updatePreviewHistogram(model.currentHistogram.getHistogramImages());
            view.showString("Executed Command: " + Arrays.toString(line));
          }
        } catch (NullPointerException e) {
          view.showString("Cannot find image:" + Arrays.toString(line));
        } catch (IndexOutOfBoundsException e) {
          view.showString("Invalid Command Parameters:" + Arrays.toString(line));
          System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
          view.showString("Cannot find file:" + Arrays.toString(line));
        } catch (IOException e) {
          view.showString("IOException during transmission:" + Arrays.toString(line));
        }
        view.showString("**********************************" +
                "**********************************************");
        if (!quit) {
          view.showString("Enter Command:");
          view.prefix();
        }
      }
    }
  }

  /**
   * This method sets the input scanner to the given Scanner object.
   *
   * @param s Scanner object to be used as the input scanner.
   */
  public void setScanner(Scanner s) {
    this.s = s;
  }
}
