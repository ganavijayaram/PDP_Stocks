import java.util.Set;

import model.stocktradings.TradeOperation;
import model.strategy.Strategy;

/**
 * Mock Strategy model to test Controller.
 */
public class StrategyLoggingModel implements TradeOperation<Strategy> {

  private StringBuilder log;


  public StrategyLoggingModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void create(Strategy trade) throws IllegalArgumentException {
    log.append("createStrategy : " + trade + "\n");
  }

  @Override
  public Strategy get(String trade) throws IllegalArgumentException {
    log.append("getStrategy : " + trade + "\n");
    return null;
  }

  @Override
  public Set<String> all() {
    log.append("getAllStrategy\n");
    return null;
  }

  @Override
  public void save(String trade) throws Exception {
    // nothing
  }

  @Override
  public void load() throws Exception {
    // nothing
  }
}
