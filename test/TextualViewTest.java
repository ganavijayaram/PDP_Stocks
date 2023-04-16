import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


import view.TextualView;
import view.View;

/**
 * Testing the TextualView class.
 */
public class TextualViewTest {

  private OutputStream out;
  private View view;

  @Test
  public void testDisplay() {
    String input = "Test Check";
    this.setup(input);
    view.display(input);
    Assert.assertEquals(input, this.out.toString());
  }

  @Test
  public void testInput() {
    String input = "Test Check";
    this.setup(input);
    String actual = view.input();
    Assert.assertEquals(input, actual);
  }


  private void setup(String input) {
    InputStream in;
    in = new ByteArrayInputStream(input.getBytes());
    this.out = new ByteArrayOutputStream();
    view = new TextualView(in, this.out);
  }

}
