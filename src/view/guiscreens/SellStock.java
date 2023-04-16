package view.guiscreens;

import java.time.LocalDate;

import controller.Features;

/**
 * GUI to sell stock.
 */
public class SellStock extends AbstractTransactionScreen {

  /**
   * Constructor to set the caption.
   */
  public SellStock() {
    super("Trading Application - Sale Window");
  }

  @Override
  public void performTrade(Features features, String portfolioName, String stock, Double shares,
                           LocalDate date, Double commission) {
    features.sellStock(portfolioName, stock, shares, date, commission);
  }
}
