import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import controller.Features;
import controller.PortfolioTradeController;
import model.stocktradings.TradeOperation;
import view.TextualView;
import view.View;

/**
 * Testing for PortfolioTradeController by mocking the model in MVC.
 */
public class PortfolioControllerUnitTest {

  private StringBuilder modelBuilder;

  private void setup(String input) throws Exception {
    modelBuilder = new StringBuilder();
    View view = new TextualView(new ByteArrayInputStream(input.getBytes()),
            new PrintStream(new ByteArrayOutputStream()));
    TradeOperation model = new LoggingModel(modelBuilder);
    TradeOperation strategy = new StrategyLoggingModel(modelBuilder);
    Features controller = new PortfolioTradeController(view, model, strategy);
  }

  @Test
  public void doesControllerCallCreate() {
    try {
      String expectedPortfolioName = "test1";
      Map<String, Double> stocks = new HashMap<>();
      stocks.put("AAPL", 1.0);
      StringBuilder input = this.createSimulatedPortfolio(expectedPortfolioName, stocks);
      StringBuilder expected = this.getSimulatedPortfolio(expectedPortfolioName, stocks);
      this.setup(input.toString());
      Assert.assertEquals(expected.toString(), modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void doesControllerCallCreateWithMultipleStock() {
    try {
      String expectedPortfolioName = "test1";
      Map<String, Double> stocks = new HashMap<>();
      stocks.put("AAPL", 1.0);
      stocks.put("NOW", 1.0);
      stocks.put("IBM", 1.0);
      stocks.put("TSLA", 1.0);
      stocks.put("GOOGL", 1.0);
      stocks.put("META", 1.0);
      stocks.put("COST", 1.0);
      StringBuilder input = this.createSimulatedPortfolio(expectedPortfolioName, stocks);
      StringBuilder expected = this.getSimulatedPortfolio(expectedPortfolioName, stocks);
      this.setup(input.toString());
      Assert.assertEquals(expected.toString(), modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void doesControllerShowAllPortfolios() {
    try {
      String input = getMenuOptions("AllPortfolio") + "\n";
      String expected = "getAllTrades called\n";
      this.setup(input);
      Assert.assertEquals(expected, modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void doesControllerEvaluateValue() {
    try {
      String input = getMenuOptions("PortfolioEvaluation") + "\n";
      String portfolioName = "test1";
      LocalDate date = LocalDate.now();
      String expected = "Get composition method called for test1\n";
      this.setup(input + portfolioName + "\n" + date + "\n");
      Assert.assertEquals(expected, modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void doesControllerGet() {

    try {
      String portfolioName = "test1";
      String input = getMenuOptions("PortfolioComposition") + "\n";
      this.setup(input + portfolioName + "\n");
      String expected = "Get composition method called for " + portfolioName + "\n";
      Assert.assertEquals(expected, modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }


  @Test
  public void doesControlledCallSave() {
    try {
      String portfolioName = "test1";
      String input = getMenuOptions("SavePortfolio") + "\n";
      String expected = "Save portfolio : " + portfolioName + "\n";
      this.setup(input + portfolioName + "\n");
      Assert.assertEquals(expected, modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void doesControlledCallLoad() {
    try {
      String expected = "Load all the portfolio in the data source\n";
      String input = getMenuOptions("LoadPortfolio") + "\n";
      this.setup(input);
      Assert.assertEquals(expected, modelBuilder.toString());
    } catch (Exception e) {
      Assert.fail();
    }
  }

  private StringBuilder getSimulatedPortfolio(String name, Map<String, Double> stocks) {
    StringBuilder input = new StringBuilder("Received : addPortfolio\n");
    input.append("TYPE : SIMULATED\n");
    input.append("Portfolio Name : " + name + "\n");
    input.append("STOCKS : \n");
    stocks.forEach((key, value) -> {
      input.append("Stock Symbol : " + key.toUpperCase() + ",Quantity : " + value + "\n");
    });
    input.append("------ END ------\n");
    return input;
  }

  private StringBuilder createSimulatedPortfolio(String name, Map<String, Double> stocks) {
    int operation = this.getMenuOptions("createSimulatedPortfolio");
    StringBuilder input = new StringBuilder(operation + "\n");
    input.append(name + "\n");
    input.append(stocks.keySet().size() + "\n");
    stocks.forEach((key, value) -> {
      input.append(key + "\n");
      input.append(value + "\n");
    });
    return input;
  }

  private Integer getMenuOptions(String operation) {
    Map<String, Integer> commands = new HashMap<>();
    commands.put("createSimulatedPortfolio", 1);
    commands.put("createTransactionalPortfolio", 2);
    commands.put("BuyStock", 3);
    commands.put("SellStock", 4);
    commands.put("AllPortfolio", 5);
    commands.put("PortfolioComposition", 6);
    commands.put("PortfolioCompositionAtDate", 7);
    commands.put("PortfolioEvaluation", 8);
    commands.put("EvaluateCostBasis", 9);
    commands.put("EvaluatePortfolioPerformance", 10);
    commands.put("SavePortfolio", 11);
    commands.put("LoadPortfolio", 12);
    return commands.get(operation);
  }


}
