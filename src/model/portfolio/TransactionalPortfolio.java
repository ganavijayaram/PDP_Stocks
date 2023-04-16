package model.portfolio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import model.stock.Stock;
import model.stock.StockImpl;
import model.stockpriceprovider.StockDataProvider;
import model.stockpriceprovider.StockDataProviderFactory;
import model.strategy.Strategy;
import model.trade.SimulatedStockTrade;
import model.trade.TransactionalStockTrade;
import model.trade.Trade;

/**
 * Represents a Transactional Portfolio used to make stock trades by the user.
 * This portfolio support trading (i.e. purchase/sale) of the stock trades within the
 * portfolio and stores the purchase and sale data to provide features such as
 * analysing the total money invested till a specified date.
 */
public class TransactionalPortfolio extends AbstractPortfolio {

  // TODO : change all the set to map
  private final Set<Trade<Stock>> purchased;
  private final Set<Trade<Stock>> sold;

  /**
   * Creates a Transactional Portfolio used to make stock trades by the user.
   * This portfolio support trading (i.e. purchase/sale) of the stock trades within the
   * portfolio and stores the purchase and sale data to provide features such as
   * analysing the total money invested till a specified date.
   *
   * @param name      the name of the portfolio.
   * @param purchased the set of purchases made by the user.
   * @param sold      the set of sale made by the user.
   */
  public TransactionalPortfolio(String name, Set<Trade<Stock>> purchased, Set<Trade<Stock>> sold) {
    // Assuming that an empty portfolio will be created and then the stock
    // purchases will be made against that portfolio.
    super(name);
    this.purchased = purchased;
    this.sold = sold;
  }

  @Override
  public void buy(String stock, Double shares, LocalDate date, Double commission)
          throws IllegalArgumentException {
    // new purchase of the stock
    Trade share = new TransactionalStockTrade(stock, shares, date, commission);
    // adding the trade in the trade
    // TODO : don't have this because commission will be counted only once.
    for (var purchase : purchased) {
      if (purchase.equals(share)) {
        purchase.buy(shares);
      }
    }
    if (!this.purchased.contains(share)) {
      this.purchased.add(share);
    }
  }

  @Override
  public void sell(String stock, Double shares, LocalDate date, Double commission)
          throws IllegalArgumentException, UnsupportedOperationException {
    Stock stockImpl = new StockImpl(stock);
    // predicate to get all available shares with trade date before the specified date
    Predicate<Trade<Stock>> predicate = x -> x.get().equals(new StockImpl(stock))
            && !x.tradeDate().isAfter(date);
    // count the number of stocks purchases
    double availableShares = this.countIf(this.purchased, predicate);
    // count the number of stock sold
    double sold = this.countIf(this.sold, predicate);
    // get the remaining stocks available for sale
    double availableToSell = availableShares - sold;
    // if the remaining stocks are less than shares that need to be sold, throw exception
    if (availableToSell < shares) {
      throw new IllegalArgumentException("You do not have enough shares of the stock to sell\n");
    }
    // make the sale trade
    Trade share = new TransactionalStockTrade(stock, shares, date, commission);
    this.sold.add(share);
  }


  @Override
  public double costBasis(LocalDate date) throws UnsupportedOperationException {
    // predicate for all the purchased trade before the specified date
    Predicate<Trade<Stock>> predicate = x -> !x.tradeDate().isAfter(date);
    // get the total money invested using the predicate.
    return addPriceIf(predicate);
  }

  @Override
  public double value(LocalDate date) throws IllegalArgumentException {
    // if the date specified is in the future, throw exception
    if (date.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The entered date is in future.\n");
    }
    // predicate to consolidate trade for a date before the specified date.
    Predicate<Trade<Stock>> predicate = x -> !x.tradeDate().isAfter(date);
    // consolidate all purchases and sale using the predicate.
    Set<Trade<Stock>> cumulative = this.consolidateIf(predicate);
    // evaluated the total values of the stock composition.
    double value = this.evaluateValue(cumulative, date);
    return value;
  }

  @Override
  public String composition() {
    // predicate to include all the purchases and sale trades
    Predicate<Trade<Stock>> predicate = x -> true;
    // get composition using predicate
    return this.getComposition(predicate);
  }

  @Override
  public String composition(LocalDate date) {
    // predicate to include all the purchases and sale trades made before the specified date.
    Predicate<Trade<Stock>> predicate = x -> !x.tradeDate().isAfter(date);
    // get composition using predicate
    return this.getComposition(predicate);
  }

  @Override
  public void applyStrategy(Strategy strategy) throws UnsupportedOperationException {
    buyPeriodic(strategy.principal(), strategy.stockProportion(), strategy.startDate(),
            strategy.endDate(), strategy.frequency(), strategy.commission());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append("TYPE : TRANSACTIONAL\n");
    sb.append("Portfolio Name : " + this.name + "\n");
    sb.append("PURCHASES : \n");
    for (Trade<Stock> trade : purchased) {
      sb.append(trade.toString());
    }
    sb.append("SALE : \n");
    for (Trade<Stock> trade : sold) {
      sb.append(trade.toString());
    }
    sb.append("------ END ------\n");
    return sb.toString();
  }

  /**
   * Consolidates the purchases and sale made by the portfolio using the
   * predicate on the trade and return the composition.
   *
   * @param predicate the predicate used to consolidate purchases and sale trades.
   * @return the composition of the portfolio using predicate.
   */
  private String getComposition(Predicate<Trade<Stock>> predicate) {
    // consolidate using the predicate
    Set<Trade<Stock>> shares = this.consolidateIf(predicate);
    // return the composition
    return getComposition(PortfolioType.TRANSACTIONAL, shares);
  }

  /**
   * Consolidate the purchases and sale of the portfolio depending on the predicate.
   *
   * @param predicate the filter used to consolidate all transactions.
   * @return the consolidated set of trades.
   */
  private Set<Trade<Stock>> consolidateIf(Predicate<Trade<Stock>> predicate) {
    HashMap<Stock, Trade<Stock>> consolidated = new HashMap<>();
    for (Trade<Stock> trade : this.purchased) {
      if (predicate.test(trade)) {
        if (consolidated.containsKey(trade.get())) {
          consolidated.get(trade.get()).buy(trade.quantity());
        } else {
          consolidated.put(trade.get(),
                  new SimulatedStockTrade(trade.get().name(), trade.quantity()));
        }
      }
    }
    for (Trade<Stock> trade : this.sold) {
      if (predicate.test(trade)) {
        Stock stock = trade.get();
        consolidated.get(stock).sell(trade.quantity());
      }
    }
    return new HashSet<>(consolidated.values());
  }


  /**
   * Count the total number of shares of the stock using the filter.
   *
   * @param shares    the trade to be counted.
   * @param predicate the filter used to count the number of shares in each trade.
   * @return the total number of shares.
   */
  private double countIf(Set<Trade<Stock>> shares, Predicate<Trade<Stock>> predicate) {
    double available = 0;
    for (Trade<Stock> trade : shares) {
      if (predicate.test(trade)) {
        available = available + trade.quantity();
      }
    }
    return available;
  }

  /**
   * Determines the total money invested in the portfolio so far using a predicate.
   *
   * @param predicate contains the filter on date that is to be considered.
   * @return the total money invested in the portfolio.
   */
  private double addPriceIf(Predicate<Trade<Stock>> predicate) {
    double cost = 0;
    for (Trade<Stock> trade : purchased) {
      if (predicate.test(trade)) {
        cost = cost + trade.value(trade.tradeDate()) + trade.commission();
      }
    }
    return cost;
  }

  private void buyOnce(Double principal, Map<String, Double> stockData, LocalDate date,
                       Double commission) {
    for (Map.Entry<String, Double> entry : stockData.entrySet()) {
      var share = new TransactionalStockTrade(entry.getKey(), principal, entry.getValue(),
              date, commission);
      if (!this.purchased.contains(share)) {
        this.purchased.add(share);
      } else {
        for (var purchase : purchased) {
          if (purchase.equals(share)) {
            purchase.buy(share.quantity());
          }
        }
      }
    }
  }

  private void buyPeriodic(Double principal, Map<String, Double> weight, LocalDate beginDate,
                           LocalDate endDate, int frequency, Double commission) {
    LocalDate currDate = beginDate;
    if (endDate == null) {
      endDate = LocalDate.now();
    }
    while (true) {
      // TODO : handle purchases on holiday
      currDate = nextAvailableDate(currDate);
      if (currDate.isAfter(endDate) || currDate.isAfter(LocalDate.now())) {
        break;
      }
      buyOnce(principal, weight, currDate, commission);
      currDate = currDate.plusDays(frequency);
    }
  }

  private LocalDate nextAvailableDate(LocalDate tradeDate) {
    StockDataProvider stockDataProvider = StockDataProviderFactory.getDataProvider();
    while (!stockDataProvider.isAvailable(tradeDate) && !tradeDate.isAfter(LocalDate.now())) {
      tradeDate = tradeDate.plusDays(1);
    }
    return tradeDate;
  }


}
