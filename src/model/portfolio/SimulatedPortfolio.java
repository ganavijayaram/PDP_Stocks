package model.portfolio;

import java.time.LocalDate;
import java.util.Set;

import model.stock.Stock;
import model.strategy.Strategy;
import model.trade.Trade;

/**
 * Represents a Simulated Portfolio with the portfolio name and collection of
 * shares. This type of portfolio only support analyzing/understanding the stock
 * collection that could be used for trading. This portfolio does not support
 * trading (i.e. purchase/sale) of the stock trades within the portfolio once
 * created.
 */
public class SimulatedPortfolio extends AbstractPortfolio {

  // collection of shares
  protected Set<Trade<Stock>> shares;

  /**
   * Creates a Simulated Portfolio with the portfolio name and collection of
   * shares. This type of portfolio only support analyzing/understanding the stock
   * collection that could be used for trading. This portfolio does not support
   * trading (i.e. purchase/sale) of the stock trades within the portfolio once
   * created.
   *
   * @param name   portfolio name
   * @param shares collection of shares of stock.
   */
  public SimulatedPortfolio(String name, Set<Trade<Stock>> shares) {
    super(name);
    this.shares = shares;
  }

  @Override
  public double value(LocalDate date) throws IllegalArgumentException {
    if (date.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The entered date is in future.\n");
    }
    return this.evaluateValue(this.shares, date);
  }

  @Override
  public void buy(String stock, Double shares, LocalDate date, Double commission)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Purchasing a new Stock is not allowed "
            + "in this portfolio\n");
  }

  @Override
  public void sell(String stock, Double shares, LocalDate date, Double commission)
          throws IllegalArgumentException, UnsupportedOperationException {
    throw new UnsupportedOperationException("Selling an existing Stock is not allowed "
            + "in Simulated Portfolio\n");
  }

  @Override
  public double costBasis(LocalDate date) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("The cost basis cannot be evaluated for Simulated"
            + "Portfolio.\n");
  }

  @Override
  public String composition() {
    return this.getComposition(PortfolioType.SIMULATED, this.shares);
  }

  @Override
  public String composition(LocalDate date) {
    return this.getComposition(PortfolioType.SIMULATED, this.shares);
  }

  @Override
  public void applyStrategy(Strategy strategy) {
    throw new UnsupportedOperationException("This portfolio does not support applying investment"
            + " strategies\n");
  }


  @Override
  public String toString() {
    return this.getComposition(PortfolioType.SIMULATED, this.shares);
  }


}
