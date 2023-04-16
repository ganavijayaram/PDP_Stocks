package view.guiscreens;

import java.time.LocalDate;

import controller.Features;

/**
 * A class for the value of the portfolio in the GUI.
 */
public class PortfolioValue extends AbstractEvaluationScreen {

  /**
   * Constructor to initialise the caption.
   */
  public PortfolioValue() {
    super("Trading Application - Portfolio Value Window");
  }

  @Override
  public void evaluateTrade(Features features, String portfolioName, LocalDate date) {
    features.value(portfolioName, date);
  }
}
