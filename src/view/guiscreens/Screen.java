package view.guiscreens;

import java.awt.event.ActionListener;

import controller.Features;

/**
 * Interface for all the Screen related operations.
 */
public interface Screen {

  /**
   * To display the text.
   *
   * @param text the text to be dispplayed.
   */
  void display(String text);

  /**
   * To display the error.
   *
   * @param text the error text to be displayed
   */
  void error(String text);

  /**
   * To bind the listener.
   *
   * @param listener the listener to bind
   */
  void bindListener(ActionListener listener);

  /**
   * To add features.
   *
   * @param features features to add
   */
  void addFeatures(Features features);

  /**
   * To set the visibility.
   *
   * @param visible check if the visibility has to be set
   */
  void setVisibility(boolean visible);

  /**
   * To dispose the Screen.
   */
  void disposeScreen();

}
