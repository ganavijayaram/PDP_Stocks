package view.guiscreens;

import java.time.LocalDate;

import controller.Features;

/**
 * A class for displaying the composition of the portfolio in the GUI.
 */
public class PortfolioComposition extends AbstractEvaluationScreen {

  /**
   * Constructor to initialise the caption.
   */
  public PortfolioComposition() {
    super("Trading Application - Portfolio Composition Window");
  }

  @Override
  public void evaluateTrade(Features features, String portfolioName, LocalDate date) {
    String composition = features.composition(portfolioName, date);
    String finalComposition = "<html>" + composition.replace("\n", "<br>");
    this.display(finalComposition);
  }

}
