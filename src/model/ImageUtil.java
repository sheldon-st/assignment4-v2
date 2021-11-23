package model;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Optional;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file return a 3D int representation
 * of the file, and write a image into a PPM file.
 */
public class ImageUtil {
  /**
   * Creates a 3d array of ints from a to a ppm, png or jpg file. Uses the file extension to
   * determine what type of file to read to.
   **/
  public static int[][][] readImage(String filename) {
    System.out.println("Reading Image ...");
    System.out.println("Image name: " + filename);

    Optional<String> extension = getExtension(filename);
    int[][][] imagePixels;

    try {
      if (extension.get().equals("ppm")) {
        return readPPM(filename);
      } else {
        BufferedImage bImage = ImageIO.read(new File(filename));
        int width = bImage.getWidth();
        int height = bImage.getHeight();
        imagePixels = new int[height][width][3];

        for (int i = 0; i < height - 1; i++) {
          for (int j = 0; j < width - 1; j++) {
            int rgb = bImage.getRGB(j, i);
            int red = (rgb & 0x00ff0000) >> 16;
            int green = (rgb & 0x0000ff00) >> 8;
            int blue = rgb & 0x000000ff;
            imagePixels[i][j][0] = red;
            imagePixels[i][j][1] = green;
            imagePixels[i][j][2] = blue;
          }
        }
        return imagePixels;
      }
    } catch (IOException e) {
      System.out.println("Transmission error while reading file: " + e);
      return null;
    }
  }

  /**
   * Read an image file in the PPM format and return a 3D int representation of the image.
   *
   * @param filename name of file to be read
   * @return 3D int representation of the image of read PPM file
   */
  public static int[][][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("Failed to load.");
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int[][][] imagePixels = new int[height][width][3];
    int max = sc.nextInt();

    System.out.println("Loading Image ...");
    System.out.println("Image Size: " + width + " by " + height +
            " px.");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imagePixels[i][j][0] = r;
        imagePixels[i][j][1] = g;
        imagePixels[i][j][2] = b;
      }
    }
    return imagePixels;
  }

  /**
   * Writes a 3d array of ints to a ppm, png or jpg file. Uses the file extension to
   * determine what type of file to save to.
   *
   * @param filename    name of file to be saved.
   * @param imagePixels 3d array of ints representing an image.
   * @param width       width of image.
   * @param height      height of image.
   * throws IllegalArgumentException if the file extension is not one of the supported types.
   */
  public static void writeImage(int[][][] imagePixels, int width, int height, String filename) {
    Optional<String> extension = getExtension(filename);

    switch (extension.get()) {
      case "bmp":
      case "png":
      case "jpg":
        writeIo(imagePixels, width, height, filename, extension.get());
        break;
      case "ppm":
        writePPM(imagePixels, width, height, filename);
        break;
      default:
        System.out.println("Invalid file extension. Supported extensions are: .ppm, .png, .jpg");
    }
  }

  /**
   * Writes a 3d array of ints to a jpg file.
   *
   * @param filename    name of file to be saved.
   * @param imagePixels 3d array of ints representing an image.
   * @param width       width of image.
   * @param height      height of image.
   */
  private static void writeIo(int[][][] imagePixels, int width, int height,
                              String filename, String format) {
    try {
      BufferedImage bImage = toBuffer(imagePixels, width, height);
      File writer = new File(filename);
      ImageIO.write(bImage, format, writer);
      System.out.println("Saving ran");
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  /**
   * Writes given image with given width. height and name into a PPM file.
   *
   * @param imagePixels Represents an image
   * @param width       represents width of image
   * @param height      represents height of image
   * @param filename    represents file name of image
   */
  private static void writePPM(int[][][] imagePixels, int width, int height, String filename) {
    try {
      BufferedImage bImage = toBuffer(imagePixels, width, height);

      BufferedWriter writer =
              new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
      writer.write(("P3\n" + width + " " + height + "\n255\n"));

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          writer.write(imagePixels[i][j][0] + "\n" + imagePixels[i][j][1] + "\n"
                  + imagePixels[i][j][2] + "\n");
        }
      }
      writer.flush();
      writer.close();
      System.out.println("Saving ran");
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  /**
   * Converts a 3d array of ints to a BufferedImage.
   *
   * @param imagePixels 3d array of ints representing an image.
   * @param width       width of image.
   * @param height      height of image.
   * @return BufferedImage of imagePixels.
   */
  public static BufferedImage toBuffer(int[][][] imagePixels, int width, int height) {
    BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        bImage.setRGB(j, i, (imagePixels[i][j][0] << 16) | (imagePixels[i][j][1] << 8) |
                imagePixels[i][j][2]);
      }
    }
    return bImage;
  }

  /**
   * Returns the extension of a file from the name.
   *
   * @param filename name of file
   * @return extension of file
   */
  private static Optional<String> getExtension(String filename) {
    return Optional.ofNullable(filename)
            .filter(separator -> separator.contains("."))
            .map(ext -> ext.substring(filename.lastIndexOf(".") + 1));
  }
}

