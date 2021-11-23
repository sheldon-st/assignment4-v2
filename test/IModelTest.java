import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.IMEModel;
import model.IModel;
import model.SingleImageModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the model image.
 */
public class IModelTest {

  private final String venPath = "res/venice.ppm";
  private final String koPath = "res/koala.ppm";
  private final int veniceWidth = 615;
  private final int veniceHeight = 410;
  private final int koalaWidth = 260;
  private final int koalaHeight = 280;
  private final int rbg = 3;
  private IMEModel galleryModel = new IMEModel();
  private int[][][] koalaImage;
  private int[][][] veniceImage;


  @Test
  public void testConstructorIModel() {
    IModel empty = new SingleImageModel();
    assertEquals(null, empty.getImage());
    IModel koala = new SingleImageModel(koPath);
    assertEquals(koalaHeight, koala.getImage().length);
    IModel koalaImage = new SingleImageModel(koala.getImage());
    assertEquals(koalaHeight, koalaImage.getImage().length);
    IModel venice = new SingleImageModel(venPath);
    assertEquals(veniceHeight, venice.getImage().length);
  }

  /**
   * Tests the getImage method of the IMEModel class and the getImage method of the
   * ImageModel class.
   */
  @Test
  public void getImage() {
    IModel koala = new SingleImageModel(koPath);
    galleryModel.loadImage("k1", koala);
    galleryModel.loadImage("k2", koala);
    assertEquals(galleryModel.images.get("k1").getImage(),
            galleryModel.images.get("k2").getImage());
    assertEquals(koalaHeight, galleryModel.images.get("k1").getImage().length);
    assertEquals(koalaWidth, galleryModel.images.get("k1").getImage()[0].length);
    assertEquals(rbg, galleryModel.images.get("k1").getImage()[0][0].length);
  }

  /**
   * Tests the getImage method of the IMEModel class and the getImage method of the ImageModel
   * class.
   */
  @Test
  public void loadImage() {
    IModel venice = new SingleImageModel(venPath);
    galleryModel.loadImage("k1", venice);
    galleryModel.loadImage("k2", venice);
    assertEquals(galleryModel.images.get("k1").getImage(),
            galleryModel.images.get("k2").getImage());
    assertEquals(veniceHeight, galleryModel.images.get("k1").getImage().length);
    assertEquals(veniceWidth, galleryModel.images.get("k1").getImage()[0].length);
    assertEquals(rbg, galleryModel.images.get("k1").getImage()[0][0].length);
  }

  /**
   * Tests the getImage method of the IMEModel class and the getImage method of the ImageModel
   * class.
   */
  @Test
  public void saveImage() throws IOException {
    IModel venice = new SingleImageModel(venPath);
    IModel koala = new SingleImageModel(koPath);
    galleryModel.loadImage("k1", venice);
    galleryModel.loadImage("k2", venice);
    galleryModel.loadImage("k3", koala);
    galleryModel.saveImage("res/venice2.ppm", "k1");
    galleryModel.saveImage("res/venice3.ppm", "k2");
    galleryModel.saveImage("res/koala1.ppm", "k3");
    assertTrue(Files.mismatch(Paths.get("res/venice2.ppm"),
            Paths.get("res/venice3.ppm")) == -1L);
    assertFalse(Files.mismatch(Paths.get("res/venice2.ppm"),
            Paths.get("res/koala1.ppm")) == -1L);
  }

  /**
   * Tests the getImage method of the IMEModel class and the getImage method of the ImageModel
   * class.
   */
  @Test
  public void testEquals() {
    IModel venice = new SingleImageModel(venPath);
    IModel koala = new SingleImageModel(koPath);
    galleryModel.loadImage("k1", venice);
    galleryModel.loadImage("k2", venice);
    galleryModel.loadImage("k3", koala);
    assertTrue(galleryModel.images.get("k1").equals(galleryModel.images.get("k2")) &&
            galleryModel.images.get("k2").equals(galleryModel.images.get("k1")));
    assertFalse(galleryModel.images.get("k1").equals(galleryModel.images.get("k3")));
  }

  @Test
  public void testHashCode() {
    IModel venice = new SingleImageModel(venPath);
    IModel koala = new SingleImageModel(koPath);
    galleryModel.loadImage("k1", venice);
    galleryModel.loadImage("k2", venice);
    galleryModel.loadImage("k3", koala);
    assertEquals(galleryModel.images.get("k1").hashCode(),
            galleryModel.images.get("k2").hashCode());
    assertNotEquals(galleryModel.images.get("k1").hashCode(),
            galleryModel.images.get("k3").hashCode());

  }
}