package view.guiscreens;

import java.time.LocalDate;

import controller.Features;

/**
 * A class to show the cost basis of the portfolio in the GUI.
 */
public class PortfolioCostBasis extends AbstractEvaluationScreen {

  /**
   * Constructor to initialise the caption.
   */
  public PortfolioCostBasis() {
    super("Trading Application - Portfolio Cost Basis Window");
  }

  @Override
  public void evaluateTrade(Features features, String portfolioName, LocalDate date) {
    features.costBasis(portfolioName, date);
  }
}
