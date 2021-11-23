import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import controller.IController;
import controller.IMECommandController;
import model.IMEModel;
import view.IView;
import view.TextView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for IMECommandController class.
 */
public class IMECommandControllerTests {
  IMEModel model = new IMEModel();
  IView view = new TextView(System.out);
  IController controller = new IMECommandController(model, System.in, view);
  String koalaPPMpath = "res/koala.ppm";
  String venicePPMPath = "res/veniceSave.ppm";
  String veniceRedPath = "res/veniceR.ppm";
  String veniceBluePath = "res/veniceB.ppm";
  String veniceGreenPath = "res/veniceG.ppm";
  String veniceValuePath = "res/veniceV.ppm";
  String veniceIntensePath = "res/veniceI.ppm";
  String veniceLumaPath = "res/veniceL.ppm";
  String veniceHorizonPath = "res/veniceH.ppm";
  String veniceVertPath = "res/veniceVF.ppm";
  String veniceBrightPath = "res/veniceBr.ppm";

  /**
   * Tests for saving and loading a PPM file command.
   */
  @Test
  public void testLoadSaveCommands() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    IController controller = new IMECommandController(model, System.in, view);
    String testLoadString = "load " + venicePPMPath + " v" +
            "\nsave res/veniceTEST.ppm v" + "\n" + "q";
    Scanner s = new Scanner(testLoadString);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/veniceTEST.ppm")));
    assertTrue(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceTEST.ppm")) == -1L);
  }

  /**
   * Tests for overriding a file by loading a new one into it.
   */
  @Test
  public void testLoadSaveOverride() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    IController controller = new IMECommandController(model, System.in, view);
    String testLoadString = "load " + koalaPPMpath + " v" +
            "\nload " + venicePPMPath + " v" + "\nsave res/veniceTEST.ppm v"
            + "\n" + "q";
    Scanner s = new Scanner(testLoadString);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/veniceTEST.ppm")));
    assertTrue(Files.mismatch(Paths.get("res/veniceTEST.ppm"),
            Paths.get(venicePPMPath)) == -1L);
  }

  /**
   * Tests for applying red-component filter command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testRedComponent() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "red-component v redK";
    String testSaveString3 = "save res/veniceRed.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceRed.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceRed.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceRed.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceRed.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceRed.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceLumaPath),
            Paths.get("res/veniceRed.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceRed.ppm")) == -1L);
  }

  /**
   * Tests for applying blue-component filter command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testBlueComponent() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "blue-component v redK";
    String testSaveString3 = "save res/veniceBlue.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceLumaPath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceBlue.ppm")) == -1L);
  }

  /**
   * Tests for applying green-component filter command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testGreenComponent() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "green-component v redK";
    String testSaveString3 = "save res/veniceGreen.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceLumaPath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceGreen.ppm")) == -1L);
  }

  /**
   * Tests for applying value-component filter command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testValueComponent() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "value-component v redK";
    String testSaveString3 = "save res/veniceValue.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceValue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceValue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceValue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceValue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceValue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceValue.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceLumaPath),
            Paths.get("res/veniceValue.ppm")) == -1L);

  }

  /**
   * Tests for applying intensity-component filter command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testIntenseComponent() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "intensity-component v redK";
    String testSaveString3 = "save res/veniceIntensity.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceLumaPath),
            Paths.get("res/veniceIntensity.ppm")) == -1L);
  }

  /**
   * Tests for applying luma-component filter command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testLumaComponent() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "luma-component v redK";
    String testSaveString3 = "save res/veniceLuma.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceLumaPath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceLuma.ppm")) == -1L);
  }

  /**
   * Tests for flipping image vertically command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testVerticalFlip() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "vertical-flip v redK";
    String testSaveString3 = "save res/veniceVert.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceVertPath),
            Paths.get("res/veniceVert.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceVert.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceHorizonPath),
            Paths.get("res/veniceVert.ppm")) == -1L);
  }

  /**
   * Tests for flipping image horizontally command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testHorizontalFlip() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "horizontal-flip v redK";
    String testSaveString3 = "save res/veniceHoriz.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceHorizonPath),
            Paths.get("res/veniceHoriz.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceHoriz.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceVertPath),
            Paths.get("res/veniceHoriz.ppm")) == -1L);
  }

  /**
   * Tests for brightening image by 10 command.
   *
   * @throws IOException if File not found
   */
  @Test
  public void testBrighten() throws IOException {
    String testLoadString = "load " + venicePPMPath + " v";
    String testRedString2 = "brighten 10 v redK";
    String testSaveString3 = "save res/veniceBright.ppm redK";
    Scanner s = new Scanner(testLoadString + "\n" + testRedString2 + "\n" +
            testSaveString3 + "\n" + "q");
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.mismatch(Paths.get(veniceBrightPath),
            Paths.get("res/veniceBright.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(venicePPMPath),
            Paths.get("res/veniceBright.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceRedPath),
            Paths.get("res/veniceBright.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceBluePath),
            Paths.get("res/veniceBright.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceGreenPath),
            Paths.get("res/veniceBright.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceValuePath),
            Paths.get("res/veniceBright.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get(veniceIntensePath),
            Paths.get("res/veniceBright.ppm")) == -1L);
  }

  /**
   * Tests for IMECommandController class for case that given image does not exist at source.
   */
  @Test
  public void testNoImageAtPath() {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/venice.ppm venice \nload res/paris.ppm paris " +
            "\nsave res/veniceCopy.ppm venice \nsave res/parisCopy.ppm paris \nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/veniceCopy.ppm")));
    assertFalse(Files.exists(Paths.get("res/parisCopy.ppm")));
  }

  /**
   * Tests for IMECommandController class for case that brighten input string is not an integer.
   */
  @Test
  public void testIncrementNotInt() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/venice.ppm v1 \nload res/venice.ppm v2" +
            "\nload res/venice.ppm vOriginal" +
            "\nbrighten 10 v1 v1brighter \nbrighten ten v2 v2brighter" +
            "\nsave res/vOriginal.ppm vOriginal \nsave res/vBrighter.ppm v1brighter \nsave " +
            "res/vBrighterFailed.ppm v2brighter " +
            "\nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertFalse(Files.exists(Paths.get("res/vBrighterFailed.ppm")));
    assertFalse(Files.mismatch(Paths.get("res/vOriginal.ppm"),
            Paths.get("res/vbrighter.ppm")) == -1L);
  }

  /**
   * Tests for IMECommandController class for case that given image does not exist within the model.
   */
  @Test
  public void testImageNotLoaded() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/venice.ppm v1 \nload res/venice.ppm v2" +
            "\nload res/venice.ppm v3" +
            "\nbrighten 10 v1 v1brighter \nbrighten 10 v4 v2brighter" +
            "\nsave res/vOriginal.ppm v3 \nsave res/v1.ppm v1brighter \nsave " +
            "res/vBrighterFailed.ppm v2brighter " +
            "\nsave res/randomPhoto.ppm randomSource " +
            "\nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertFalse(Files.exists(Paths.get("res/vBrighterFailed.ppm")));
    assertFalse(Files.exists(Paths.get("res/randomPhoto.ppm")));
  }

  /**
   * Tests for IMECommandController class for case that command is either not recognized or first
   * word is recognized but arguments are not.
   */
  @Test
  public void testInvalidCommand() {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/venice.ppm venice \nload venice2 " +
            "\nbrighten venice venice3" +
            "\nsave res/veniceCopy.ppm venice \nsave res/veniceCopy3.ppm venice2 " +
            "\nsave res/veniceCopy3.ppm venice3 \nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/veniceCopy.ppm")));
    assertFalse(Files.exists(Paths.get("res/veniceCopy3.ppm")));
    assertFalse(Files.exists(Paths.get("res/veniceCopy3.ppm")));
  }


  /**
   * Tests for IMECommandController reading and saving a jpg file.
   */
  @Test
  public void testLoadSaveJPG() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/koala.jpg k1 \nsave res/koalaFromJpg.ppm k1 " +
            "\nload res/venice.ppm v1 \nsave res/veniceFromPpm.jpg v1 \nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/koalaFromJpg.ppm")));
    assertTrue(Files.exists(Paths.get("res/veniceFromPpm.jpg")));
    assertTrue(Files.isReadable(Paths.get("res/koalaFromJpg.ppm")));
    assertTrue(Files.isReadable(Paths.get("res/veniceFromPpm.jpg")));
  }

  /**
   * Tests for IMECommandController reading and saving a png file.
   */
  @Test
  public void testLoadSavePng() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/koala.png k1 \nsave res/koalaFromPng.ppm k1 " +
            "\nload res/venice.ppm v1 \nsave res/veniceFromPpm.png v1 \nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/koalaFromPng.ppm")));
    assertTrue(Files.exists(Paths.get("res/veniceFromPpm.png")));
    assertTrue(Files.isReadable(Paths.get("res/koalaFromPng.ppm")));
    assertTrue(Files.isReadable(Paths.get("res/veniceFromPpm.png")));
  }

  /**
   * Tests for IMECommandController reading and saving a bmp file.
   */
  @Test
  public void testLoadSaveBmp() throws IOException {
    IMEModel model = new IMEModel();
    IView view = new TextView(System.out);
    String testLoadString = "load res/koala.bmp k1 \nsave res/koalaFromBmp.ppm k1 " +
            "\nload res/venice.ppm v1 \nsave res/veniceFromPpm.bmp v1 \nq";
    Scanner s = new Scanner(testLoadString);
    IController controller = new IMECommandController(model, System.in, view);
    controller.setScanner(s);
    controller.startProgram();
    assertTrue(Files.exists(Paths.get("res/koalaFromBmp.ppm")));
    assertTrue(Files.exists(Paths.get("res/veniceFromPpm.bmp")));
    assertTrue(Files.isReadable(Paths.get("res/koalaFromBmp.ppm")));
    assertTrue(Files.isReadable(Paths.get("res/veniceFromPpm.bmp")));
  }
}


