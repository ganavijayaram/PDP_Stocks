package model.stocktradings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.strategy.Strategy;

/**
 * Represents a class used to track Strategies. Supports methods to create new
 * Strategy, get an existing Strategy from the application, listing of all the
 * Strategy trades created by the user.
 */
public class InvestmentStrategyOperation implements TradeOperation<Strategy> {

  private Map<String, Strategy> strategies;

  /**
   * Initializes a Strategy Operator responsible for tracking the strategies in the
   * application.
   */
  public InvestmentStrategyOperation() {
    this.strategies = new HashMap<>();
  }

  @Override
  public void create(Strategy trade) throws IllegalArgumentException {
    if (this.strategies.containsKey(trade.name())) {
      throw new IllegalArgumentException("The strategy already exists in the application.\n");
    }
    this.strategies.put(trade.name(), trade);
  }

  @Override
  public Strategy get(String trade) throws IllegalArgumentException {
    return this.strategies.get(trade);
  }

  @Override
  public Set<String> all() {
    return this.strategies.keySet();
  }

  @Override
  public void save(String trade) throws Exception {
    // NOT SUPPORTED AS OF NOW
  }

  @Override
  public void load() throws Exception {
    // NOT SUPPORTED AS OF NOW
  }
}
