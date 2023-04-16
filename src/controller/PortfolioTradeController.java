package controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.datarepo.FileRepository;
import model.portfolio.Portfolio;
import model.portfolio.SimulatedPortfolio;
import model.portfolio.TransactionalPortfolio;
import model.stock.Stock;
import model.stocktradings.TradeOperation;
import model.strategy.InvestmentStrategy;
import model.strategy.Strategy;
import model.strategy.StrategyBuilder;
import model.trade.SimulatedStockTrade;
import model.trade.Trade;
import view.View;

/**
 * Controller to implement the features.
 */
public class PortfolioTradeController implements Features {

  private final View view;
  private final TradeOperation<Portfolio> model;
  private final TradeOperation<Strategy> strategyModel;


  /**
   * Constructor to initialise the class variables.
   * @param view view object
   * @param model model object
   * @param strategyModel model for keeping track of strategy.
   */
  public PortfolioTradeController(View view, TradeOperation<Portfolio> model,
                                  TradeOperation<Strategy> strategyModel) {
    this.view = view;
    this.model = model;
    this.strategyModel = strategyModel;
    view.addFeatures(this);
  }

  @Override
  public void createInflexiblePortfolio(String name, Map<String, Double> purchases) {
    try {
      Set<Trade<Stock>> trades = new HashSet<>();
      purchases.forEach((key, value) -> {
        try {
          trades.add(new SimulatedStockTrade(key, value));
        }
        catch (Exception e) {
          this.view.display(e.getMessage());
        }
      });
      if (trades.size() > 0) {
        // asks the model to buy the portfolio if there are any valid shares
        var portfolio = new SimulatedPortfolio(name, trades);
        this.model.create(portfolio);
        view.display("Portfolio created successfully.\n");
      } else {
        // if there are no valid shares, inform the user
        view.display("Portfolio could not be created since all the shares in the "
                + "portfolio are Invalid.\n");
      }
    } catch (IllegalArgumentException exception) {
      view.error(exception.getMessage());
    }
  }

  @Override
  public boolean createFlexiblePortfolio(String name) {
    try {
      var portfolio = new TransactionalPortfolio(name, new HashSet<>(), new HashSet<>());
      this.model.create(portfolio);
      this.view.display("The portfolio is created successfully!\n");
      return true;
    }
    catch (IllegalArgumentException e) {
      view.error(e.getMessage());
      return false;
    }
  }

  @Override
  public Set<String> getPortfolios() {
    Set<String> all = model.all();
    return all;
  }

  @Override
  public void buyStock(String portfolio, String stock, Double shares, LocalDate date,
                       Double commission) {
    try {
      model.get(portfolio).buy(stock, shares, date, commission);
      view.display("The purchase was completed successfully!\n");
    }
    catch (UnsupportedOperationException | IllegalArgumentException e) {
      view.error(e.getMessage());
    }
  }

  @Override
  public void sellStock(String portfolio, String stock, Double shares, LocalDate date,
                        Double commission) {
    try {
      model.get(portfolio).sell(stock, shares, date, commission);
      view.display("The sale was completed successfully!\n");
    }
    catch (UnsupportedOperationException | IllegalArgumentException e) {
      view.error(e.getMessage());
    }
  }

  @Override
  public void value(String portfolio, LocalDate date) {
    try {
      Double value = model.get(portfolio).value(date);
      view.display("The value of portfolio is $" + value + "\n");
    }
    catch (IllegalArgumentException e) {
      view.error(e.getMessage());
    }
  }

  @Override
  public void costBasis(String portfolio, LocalDate date) {
    try {
      Double costBasis = model.get(portfolio).costBasis(date);
      view.display("The cost basis for the portfolio is $" + costBasis + "\n");
    }
    catch (UnsupportedOperationException | IllegalArgumentException e) {
      view.error(e.getMessage());
    }
  }

  @Override
  public String composition(String portfolio) {
    try {
      String composition = model.get(portfolio).composition();
      return composition;
    }
    catch (IllegalArgumentException e) {
      view.error(e.getMessage());
      return "";
    }
  }

  @Override
  public String composition(String portfolio, LocalDate date) {
    try {
      String composition = model.get(portfolio).composition(date);
      return composition;
    }
    catch (IllegalArgumentException e) {
      view.error(e.getMessage());
      return "";
    }
  }

  @Override
  public void load(String dataSource) {
    try {
      FileRepository.getInstance().setDataSource(dataSource);
      model.load();
      view.display("The load of portfolio is successfully completed\n");
    }
    catch (Exception e) {
      view.error("There were issues with portfolio load. Please make sure the file is in"
              + "expected format.\n");
    }
  }

  @Override
  public void save(String dataSource, String portfolioName) {
    try {
      FileRepository.getInstance().setDataSource(dataSource);
      model.save(portfolioName);
      view.display("The portfolio saved to file successfully\n");
    }
    catch (Exception e) {
      view.error("The save could not be completed. Please make sure the portfolio name"
              + " is entered correctly and the data source(file) exist.\n");
    }
  }

  @Override
  public Map<LocalDate, Double> values(String portfolioName, LocalDate from, LocalDate end) {
    try {
      return model.get(portfolioName).values(from, end);
    } catch (IllegalArgumentException e) {
      view.error(e.getMessage());
    }
    return null;
  }


  @Override
  public boolean createStrategy(String name, Double principal, Map<String, Double> weights,
                     LocalDate start, LocalDate end, int days, Double commission) {
    try {
      StrategyBuilder strategyBuilder = new InvestmentStrategy.InvestmentStrategyBuilder()
              .setName(name)
              .setPrincipal(principal)
              .setStartDate(start)
              .setEndDate(end)
              .setFrequency(days)
              .setCommission(commission);
      for (Map.Entry<String, Double> entry : weights.entrySet()) {
        strategyBuilder.addStock(entry.getKey(), entry.getValue());
      }
      this.strategyModel.create(strategyBuilder.build());
      view.display("Strategy created successfully\n");
      return true;
    }
    catch (IllegalArgumentException e ) {
      view.error(e.getMessage());
      return false;
    }

  }

  @Override
  public Set<String> getAllStrategy() {
    return this.strategyModel.all();
  }

  @Override
  public void applyStrategy(String portfolioName, String strategyName) {
    try {
      Strategy strategy = this.strategyModel.get(strategyName);
      this.model.get(portfolioName).applyStrategy(strategy);
      view.display("Strategy applied successfully to the portfolio\n");
    }
    catch (IllegalArgumentException | UnsupportedOperationException e) {
      view.error(e.getMessage());
    }
  }
}
