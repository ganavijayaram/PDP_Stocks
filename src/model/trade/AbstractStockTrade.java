package model.trade;

import java.time.LocalDate;

import model.stock.Stock;
import model.stock.StockImpl;

/**
 * Abstract class to abstract common features of different types of implementations
 * of Trades comprised of stock.
 */
public abstract class AbstractStockTrade implements Trade<Stock> {

  // stock transaction of the trade
  protected final Stock stock;
  // number of shares of the stock.
  protected double quantity;

  /**
   * Creates a trade object comprised of stock.
   *
   * @param stock    the stock comprising the trade.
   * @param quantity the number of shares of the stock.
   */
  protected AbstractStockTrade(String stock, double quantity) {
    this.stock = new StockImpl(stock);
    this.quantity = quantity;
  }

  protected  AbstractStockTrade(String stock) {
    this.stock = new StockImpl(stock);
  }



  @Override
  public Stock get() {
    return this.stock;
  }

  @Override
  public double value(LocalDate date) {
    return this.stock.price(date) * this.quantity;
  }

  @Override
  public double quantity() {
    return this.quantity;
  }

  @Override
  public void buy(double quantity) {
    this.quantity = this.quantity + quantity;
  }

  @Override
  public void sell(double quantity) {
    if (this.quantity < quantity) {
      throw new IllegalArgumentException("Not enough shares to sell");
    }
    this.quantity = this.quantity - quantity;
  }

}
