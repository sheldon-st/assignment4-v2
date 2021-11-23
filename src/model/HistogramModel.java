package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import javax.swing.*;

/**
 * This class represents a histogram model to visualize the red, green, blue and intensity values
 * of an image.
 */
public class HistogramModel {
  /**
   * Histogram array to store the histogram values for r, g, b and intensity.
   */
  private int[][] histogramArray;

  /**
   * Vars to ...
   */
  IntSummaryStatistics redStats;
  IntSummaryStatistics greenStats;
  IntSummaryStatistics blueStats;
  IntSummaryStatistics intensityStats;

  /**
   *
   */
  private double scaleFactor;

  /**
   * Constructor for the histogram model.
   */
  public HistogramModel(SingleImageModel imageModel) {
    System.out.println("attempting to get h model construct: ");

    histogramArray = new int[4][256];
    updateHistogram(imageModel);

   redStats = Arrays.stream(histogramArray[0]).summaryStatistics();
   greenStats = Arrays.stream(histogramArray[1]).summaryStatistics();
   blueStats = Arrays.stream(histogramArray[2]).summaryStatistics();
   intensityStats = Arrays.stream(histogramArray[3]).summaryStatistics();

    int overallMax = Math.max(redStats.getMax(), Math.max(greenStats.getMax(),
            Math.max(blueStats.getMax(), intensityStats.getMax())));

    scaleFactor = overallMax / 256;

    System.out.println(redStats.getMax());
    System.out.println(greenStats.getMax());
    System.out.println(blueStats.getMax());

    System.out.println(overallMax);
  }

  /**
   * This method updates the histogram.
   */
  public void updateHistogram(SingleImageModel imageModel) {
    System.out.println("attempting to update h: ");

    for (int i = 0; i < imageModel.getImage().length ; i++) {
      for (int j = 0; j < imageModel.getImage()[0].length ; j++) {
        for (int c = 0; c < 3; c++) {
          histogramArray[c][imageModel.getImage()[i][j][c]]++;
        }
        histogramArray[3][(imageModel.getImage()[i][j][0] +
                imageModel.getImage()[i][j][1] + imageModel.getImage()[i][j][2]) / 3]++;
      }
    }
  }

  /**
   * Creates an image of the histogram for the given values.
   *
   * @param index the array of values to be used to create the histogram
   * @return the image of the histogram
   */
   public ImageIcon createHistogramImage(int index) {
     Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK};
     ImageIcon histogramImage = new ImageIcon();
     BufferedImage histogramBufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
     Graphics2D histogramGraphics = histogramBufferedImage.createGraphics();
     histogramGraphics.setColor(Color.WHITE);
     histogramGraphics.fillRect(0, 0, 256, 256);
     histogramGraphics.setColor(colors[index]);
     System.out.println(scaleFactor);

     for (int i = 0; i < 256; i++) {
       histogramGraphics.drawLine(i, 255, i, 255 - (int) (histogramArray[index][i] / scaleFactor));

     }
     histogramImage.setImage(histogramBufferedImage);
     return histogramImage;
   }






  /**
   * Returns a JPanel component to visualize the histogram for the red, green, blue and intensity
   * values.
   */
  public ImageIcon[] getHistogramImages() {
    ImageIcon[] histogramImages = new ImageIcon[4];
    histogramImages[0] = createHistogramImage(0);
    histogramImages[1] = createHistogramImage(1);
    histogramImages[2] = createHistogramImage(2);
    histogramImages[3] = createHistogramImage(3);
    return histogramImages;
  }
  // load res/koala.ppm k
}