package model.portfolio;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import model.stock.Stock;
import model.trade.Trade;
import model.utility.Utility;

/**
 * Abstract Class to abstract out the Portfolio details pertaining to all
 * the different types of Portfolio.
 */
abstract class AbstractPortfolio implements Portfolio {

  //name of the portfolio
  protected final String name;

  /**
   * Creates an instance of a portfolio and sets the name of the type.
   * Abstract class so this will only be called by concrete implementation.
   *
   * @param name name of the portfolio
   */
  protected AbstractPortfolio(String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public Map<LocalDate, Double> values(LocalDate from, LocalDate to)
          throws IllegalArgumentException {
    if (from.isAfter(to)) {
      throw new IllegalArgumentException("Start date cannot be after end date\n");
    }
    if (from.isAfter(LocalDate.now()) || to.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The start/end date entered is in future.\n");
    }
    SortedMap<LocalDate, Double> values = new TreeMap<>();
    Set<LocalDate> dates = Utility.getEqualPeriod(from, to);
    for (LocalDate date : dates) {
      values.put(date, this.value(date));
    }
    return values;
  }

  /**
   * Evaluates the value of the set of trades at a date.
   *
   * @param shares the set of aggregated shares comprising portfolio.
   * @param date   the date of evaluation.
   * @return the value of evaluation.
   */
  protected double evaluateValue(Set<Trade<Stock>> shares, LocalDate date) {
    // Adds the value of each trade to calculate the total portfolio value
    double value = 0;
    for (Trade trade : shares) {
      double total = trade.value(date);
      value = value + total;
    }
    return value;
  }

  /**
   * Returns the aggregated composition of shares in a portfolio.
   *
   * @param type type of portfolio
   * @param shares the set of aggregated shares comprising portfolio.
   * @return the portfolio composition.
   */
  protected String getComposition(PortfolioType type, Set<Trade<Stock>> shares) {
    StringBuilder sb = new StringBuilder();
    sb.append("TYPE : " + type + "\n");
    sb.append("Portfolio Name : " + this.name + "\n");
    sb.append("STOCKS : \n");
    for (Trade share : shares) {
      sb.append(share.toString());
    }
    sb.append("------ END ------\n");
    return sb.toString();
  }


}
