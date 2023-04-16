package view.guiscreens;

import java.time.LocalDate;

import controller.Features;

/**
 * A class to buy the stock.
 */
public class BuyStock extends AbstractTransactionScreen {

  public BuyStock() {
    super("Trading Application - Purchase Window");
  }

  @Override
  public void performTrade(Features features, String portfolioName, String stock, Double shares,
                           LocalDate date, Double commission) {
    features.buyStock(portfolioName, stock, shares, date, commission);
  }


}
