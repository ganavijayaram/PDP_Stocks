import java.util.HashSet;
import java.util.Set;

import model.portfolio.Portfolio;
import model.portfolio.SimulatedPortfolio;
import model.stocktradings.TradeOperation;

/**
 * Mock model which will be used to test the controller delegation.
 */
public class LoggingModel implements TradeOperation<Portfolio> {
  private StringBuilder log;

  public LoggingModel(StringBuilder log) {
    this.log = log;
  }


  @Override
  public void create(Portfolio trade) throws IllegalArgumentException {
    log.append("Received : addPortfolio\n" + trade.toString());

  }

  @Override
  public Portfolio get(String trade) throws IllegalArgumentException {
    log.append("Get composition method called for " + trade + "\n");
    return new SimulatedPortfolio(trade, new HashSet<>());
  }

  @Override
  public Set<String> all() {
    log.append("getAllTrades called\n");
    return new HashSet<>();
  }

  @Override
  public void save(String trade) throws Exception {
    log.append("Save portfolio : " + trade + "\n");
  }

  @Override
  public void load() {
    log.append("Load all the portfolio in the data source\n");
  }

}
