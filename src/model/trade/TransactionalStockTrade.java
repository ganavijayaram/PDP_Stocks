package model.trade;

import java.time.LocalDate;


import model.stockpriceprovider.StockDataProvider;
import model.stockpriceprovider.StockDataProviderFactory;

/**
 * Represents a Regular Trade of stocks with stock as properties along with
 * the number of stocks contributing in the trade, date of trade, commission fee involved
 * in the trade.
 */
public class TransactionalStockTrade extends AbstractStockTrade {

  // the date of the trade
  private final LocalDate tradeDate;

  // the commission fee involved in the trade.
  private final double commission;

  /**
   * Creates a transactional stock trade object with stock, number of shares of a stock,
   * date of trade, commission fee involved in the trade.
   *
   * @param stock      the stock comprising the trade.
   * @param quantity   the number of shares in the trade.
   * @param tradeDate  the trade date.
   * @param commission the commission fee involved in the trade.
   */
  public TransactionalStockTrade(String stock, double quantity, LocalDate tradeDate,
                                 Double commission) {

    super(stock, quantity);
    StockDataProvider stockDataProvider = StockDataProviderFactory.getDataProvider();
    if (!stockDataProvider.isAvailable(tradeDate)) {
      throw new IllegalArgumentException("The Stock Market is closed on the specified date."
              + "Invalid trade Date.\n");
    }
    this.tradeDate = tradeDate;
    this.commission = commission;
  }

  /**
   * Constructor to initialise the variables.
   * @param stock name of the stock
   * @param principal investment
   * @param weight proportion of the stocks
   * @param tradeDate the date on which the trade as to be made
   * @param commission the commission for the transaction
   */
  public TransactionalStockTrade(String stock, double principal, double weight, LocalDate tradeDate,
                                 Double commission) {
    super(stock);
    StockDataProvider stockDataProvider = StockDataProviderFactory.getDataProvider();
    if (!stockDataProvider.isAvailable(tradeDate)) {
      throw new IllegalArgumentException("The Stock Market is closed on the specified date."
              + "Invalid trade Date.\n");
    }
    var amount = principal * weight / 100;
    var currentVal = this.stock.price(tradeDate);
    this.quantity = amount / currentVal;
    this.tradeDate = tradeDate;
    this.commission = commission;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof TransactionalStockTrade)) {
      return false;
    }
    Trade trade = (TransactionalStockTrade) obj;
    return this.get().equals(trade.get()) && this.tradeDate.equals(trade.tradeDate())
            && this.commission == this.commission;
  }

  @Override
  public int hashCode() {
    return this.stock.hashCode() + this.tradeDate.hashCode()
            + Double.valueOf(this.commission).hashCode();
  }

  @Override
  public String toString() {
    return "Stock Symbol : " + this.stock
            + ",Quantity : " + this.quantity
            + ",Date of Purchase : " + this.tradeDate
            + ",Commission Fee : " + this.commission + "\n";
  }

  @Override
  public LocalDate tradeDate() throws IllegalArgumentException {
    return this.tradeDate;
  }

  @Override
  public double commission() throws UnsupportedOperationException {
    return this.commission;
  }

}
