import org.junit.Before;
import org.junit.Test;

import model.Filter;
import model.FilterImage;
import model.IMEModel;
import model.IModel;
import model.SingleImageModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Filtering Images such as by red, green, blue grayscale etc.
 */
public class FilterImageTest {
  private final String venPath = "res/venice.ppm";
  private final String koPath = "res/koala.ppm";
  private IMEModel galleryModel = new IMEModel();
  private IModel koala;
  private IModel venice;
  private Filter filter;

  @Before
  public void setUp() {
    koala = new SingleImageModel(koPath);
    venice = new SingleImageModel(venPath);
    galleryModel.loadImage("Venice", venice);
    galleryModel.loadImage("koala", koala);
    filter = new FilterImage(venice.getImage());
  }

  @Test
  public void testConstructorIModel() {
    assertEquals(filter.getNewImage(), (venice.getImage()));
  }

  @Test
  public void getNewImage() {
    assertEquals(venice.getImage(), filter.getNewImage());
    assertNotEquals(koala.getImage(), filter.getNewImage());
  }

  @Test
  public void redScale() {
    filter.redScale();
    assertTrue(filter.getNewImage()[0][0][0] == venice.getImage()[0][0][0] &&
            filter.getNewImage()[0][0][1] == venice.getImage()[0][0][0] &&
            filter.getNewImage()[0][0][2] == venice.getImage()[0][0][0]);

    assertTrue(filter.getNewImage()[5][6][0] == venice.getImage()[5][6][0] &&
            filter.getNewImage()[5][6][1] == venice.getImage()[5][6][0] &&
            filter.getNewImage()[5][6][2] == venice.getImage()[5][6][0]);
  }

  @Test
  public void blueScale() {
    filter.blueScale();
    assertTrue(filter.getNewImage()[0][0][0] == venice.getImage()[0][0][2] &&
            filter.getNewImage()[0][0][1] == venice.getImage()[0][0][2] &&
            filter.getNewImage()[0][0][2] == venice.getImage()[0][0][2]);

    assertTrue(filter.getNewImage()[5][6][0] == venice.getImage()[5][6][2] &&
            filter.getNewImage()[5][6][1] == venice.getImage()[5][6][2] &&
            filter.getNewImage()[5][6][2] == venice.getImage()[5][6][2]);
  }

  @Test
  public void greenScale() {
    filter.greenScale();
    assertTrue(filter.getNewImage()[0][0][0] == venice.getImage()[0][0][1] &&
            filter.getNewImage()[0][0][1] == venice.getImage()[0][0][1] &&
            filter.getNewImage()[0][0][2] == venice.getImage()[0][0][1]);

    assertTrue(filter.getNewImage()[5][6][0] == venice.getImage()[5][6][1] &&
            filter.getNewImage()[5][6][1] == venice.getImage()[5][6][1] &&
            filter.getNewImage()[5][6][2] == venice.getImage()[5][6][1]);
  }

  @Test
  public void valueScale() {
    int maxValAtZero = Math.max(Math.max(venice.getImage()[0][0][0],
            venice.getImage()[0][0][1]), venice.getImage()[0][0][2]);

    int maxValAtFiveSix = Math.max(Math.max(venice.getImage()[5][6][0],
            venice.getImage()[5][6][1]), venice.getImage()[5][6][2]);

    filter.valueScale();
    assertTrue(filter.getNewImage()[0][0][0] == maxValAtZero
            && filter.getNewImage()[0][0][1] == maxValAtZero
            && filter.getNewImage()[0][0][2] == maxValAtZero
            && filter.getNewImage()[5][6][0] == maxValAtFiveSix
            && filter.getNewImage()[5][6][1] == maxValAtFiveSix
            && filter.getNewImage()[5][6][2] == maxValAtFiveSix);
  }

  @Test
  public void intensityScale() {
    int avgValAtZero = (venice.getImage()[0][0][0] + venice.getImage()[0][0][1] +
            venice.getImage()[0][0][2]) / 3;

    int avgValAtFiveSix = (venice.getImage()[5][6][0] + venice.getImage()[5][6][1]
            + venice.getImage()[5][6][2]) / 3;

    filter.intensityScale();
    assertTrue(filter.getNewImage()[0][0][0] == avgValAtZero
            && filter.getNewImage()[0][0][1] == avgValAtZero
            && filter.getNewImage()[0][0][2] == avgValAtZero
            && filter.getNewImage()[5][6][0] == avgValAtFiveSix
            && filter.getNewImage()[5][6][1] == avgValAtFiveSix
            && filter.getNewImage()[5][6][2] == avgValAtFiveSix);
  }

  @Test
  public void lumaScale() {
    int lumaAtZero = (int) Math.round((0.2126 * venice.getImage()[0][0][0]) +
            (0.7152 * venice.getImage()[0][0][1]) +
            (0.0722 * venice.getImage()[0][0][2]));

    int lumaAtFiveSix = (int) Math.round((0.2126 * venice.getImage()[5][6][0]) +
            (0.7152 * venice.getImage()[5][6][1]) +
            (0.0722 * venice.getImage()[5][6][2]));

    filter.lumaScale();
    assertTrue(filter.getNewImage()[0][0][0] == lumaAtZero
            && filter.getNewImage()[0][0][1] == lumaAtZero
            && filter.getNewImage()[0][0][2] == lumaAtZero
            && filter.getNewImage()[5][6][0] == lumaAtFiveSix
            && filter.getNewImage()[5][6][1] == lumaAtFiveSix
            && filter.getNewImage()[5][6][2] == lumaAtFiveSix);
  }

  @Test
  public void copyImage() {
    filter.copyImage(venice.getImage());
    assertTrue(filter.getNewImage()[0][0][0] == venice.getImage()[0][0][0] &&
            filter.getNewImage()[0][0][1] == venice.getImage()[0][0][1] &&
            filter.getNewImage()[0][0][2] == venice.getImage()[0][0][2]);

    assertTrue(filter.getNewImage()[5][6][0] == venice.getImage()[5][6][0] &&
            filter.getNewImage()[5][6][1] == venice.getImage()[5][6][1] &&
            filter.getNewImage()[5][6][2] == venice.getImage()[5][6][2]);
  }

  @Test
  public void flipHorizontally() {
    filter.flipHorizontally();
    assertEquals(filter.getNewImage()[0][614][0], venice.getImage()[0][0][0]);
    assertEquals(filter.getNewImage()[0][614][1], venice.getImage()[0][0][1]);
    assertEquals(filter.getNewImage()[0][614][2], venice.getImage()[0][0][2]);

  }

  @Test
  public void flipVertically() {
    filter.flipVertically();
    assertTrue(filter.getNewImage()[409][0][0] == venice.getImage()[0][0][0] &&
            filter.getNewImage()[409][0][1] == venice.getImage()[0][0][1] &&
            filter.getNewImage()[409][0][2] == venice.getImage()[0][0][2]);

  }

  @Test
  public void brighten() {
    filter.brighten(10);
    assertTrue(filter.getNewImage()[0][0][0] == venice.getImage()[0][0][0] + 10 &&
            filter.getNewImage()[0][0][1] == venice.getImage()[0][0][1] + 10 &&
            filter.getNewImage()[0][0][2] == venice.getImage()[0][0][2] + 10);

    assertTrue(filter.getNewImage()[5][6][0] == venice.getImage()[5][6][0] + 10 &&
            filter.getNewImage()[5][6][1] == venice.getImage()[5][6][1] + 10 &&
            filter.getNewImage()[5][6][2] == venice.getImage()[5][6][2] + 10);
  }
}