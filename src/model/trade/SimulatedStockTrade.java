package model.trade;

import java.time.LocalDate;

/**
 * Represents a Simulated Trade of stocks with stock as properties along with
 * the number of stocks contributing in the trade.
 */
public class SimulatedStockTrade extends AbstractStockTrade {

  /**
   * Creates a Simulated Stock Trade using stock symbol and number of shares.
   *
   * @param stock    the stock symbol.
   * @param quantity the number of stocks.
   */
  public SimulatedStockTrade(String stock, double quantity) {
    super(stock, quantity);
  }

  @Override
  public LocalDate tradeDate() throws IllegalArgumentException {
    throw new UnsupportedOperationException("Does not support purchases, only for analysis\n");
  }

  @Override
  public double commission() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This type of trade does not support commissions\n");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof SimulatedStockTrade)) {
      return false;
    }
    Trade trade = (SimulatedStockTrade) obj;
    return this.get().equals(trade.get());
  }

  @Override
  public int hashCode() {
    return this.stock.hashCode();
  }

  @Override
  public String toString() {
    return "Stock Symbol : " + this.stock.toString() + ",Quantity : " + this.quantity + "\n";
  }
}
