package model.stocktradings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.datarepo.DataRepository;
import model.portfolio.Portfolio;
import model.utility.PortfolioDataParser;

/**
 * Represents a class used to trade on Portfolio. Supports methods to create new
 * portfolio, get an existing portfolio from the application, listing of all the
 * portfolio trades created by the user in, saving and loading of portfolio to and
 * from a data source specified by the user.
 */
public class PortfolioTradeOperation implements TradeOperation<Portfolio> {

  // create a map for portfolios available in the application.
  private Map<String, Portfolio> portfolios;
  // repository to read/write from data source.
  private final DataRepository repository;

  /**
   * Creates a PortfolioTrader with repository as argument for reading/writing portfolios
   * from the data-source.
   *
   * @param repository the data repository responsible for reading/writing data.
   */
  public PortfolioTradeOperation(DataRepository repository) {
    portfolios = new HashMap<>();
    this.repository = repository;
  }

  @Override
  public void create(Portfolio portfolio) throws IllegalArgumentException {
    // if the portfolio already exist then throw an invalid argument exception
    if (this.portfolios.containsKey(portfolio.name())) {
      throw new IllegalArgumentException("The portfolio with same name exists."
              + "It cannot be changed after creation.\n");
    }
    this.portfolios.put(portfolio.name(), portfolio);
  }


  @Override
  public Portfolio get(String trade) throws IllegalArgumentException {
    // if the portfolio does not exist throw an exception
    if (!this.portfolios.containsKey(trade)) {
      throw new IllegalArgumentException("The portfolio with name provided does not exist.\n");
    }
    return this.portfolios.get(trade);
  }

  @Override
  public Set<String> all() {
    return this.portfolios.keySet();
  }


  @Override
  public void save(String portfolio) throws Exception {
    // if the portfolio does not exist throw the exception
    if (!this.portfolios.containsKey(portfolio)) {
      throw new IllegalArgumentException("The portfolio with name : " + portfolio
              + " does not exist.");
    }
    //this.portfolios.get(portfolio).save();
    // save the portfolio in the data source
    repository.save(this.portfolios.get(portfolio).toString());

  }

  @Override
  public void load() throws Exception {
    String data = repository.read().toString();
    // Parse the data into required format
    List<Portfolio> portfolios = PortfolioDataParser.parse(data);
    for (Portfolio portfolio : portfolios) {
      // if the portfolio does not exist then load the portfolio in the application
      if (!this.portfolios.containsKey(portfolio.name())) {
        this.create(portfolio);
      }
    }
  }
}
