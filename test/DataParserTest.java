import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import model.dataparser.DataParserFactory;
import model.dataparser.SimulatedPortfolioParser;
import model.dataparser.TransactionalPortfolioParser;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioType;
import model.utility.PortfolioDataParser;

/**
 * Class to test the data Parser.
 */
public class DataParserTest {

  @Test
  public void parserFactory_Transactional() {
    var dataParser = DataParserFactory.getParser(PortfolioType.TRANSACTIONAL);
    boolean instance = false;
    if (dataParser instanceof TransactionalPortfolioParser) {
      instance = true;
    }
    Assert.assertTrue(instance);
  }

  @Test
  public void parserFactory_Simulated() {
    var dataParser = DataParserFactory.getParser(PortfolioType.SIMULATED);
    boolean instance = false;
    if (dataParser instanceof SimulatedPortfolioParser) {
      instance = true;
    }
    Assert.assertTrue(instance);
  }

  @Test
  public void parserFactory_Null() {
    var dataParser = DataParserFactory.getParser(null);
    Assert.assertNull(dataParser);
  }

  @Test
  public void parse() {
    var simulatedParser = new SimulatedPortfolioParser();
    var transactionalParser = new TransactionalPortfolioParser();
    var simulated = simulatedParser.parse(null);
    var transactional = transactionalParser.parse(null);
    Assert.assertNull(simulated);
    Assert.assertNull(transactional);
  }

  @Test
  public void parseSimulatedPortfolio() {

    String simulatedString = "TYPE : SIMULATED\n"
            + "Portfolio Name : test1\n"
            + "STOCKS : \n"
            + "Stock Symbol : GOOGL,Quantity : 1.0\n"
            + "Stock Symbol : AAPL,Quantity : 4.0\n"
            + "------ END ------\n";
    List<Portfolio> portfolio = PortfolioDataParser.parse(simulatedString);
    Assert.assertEquals(1, portfolio.size());
    Assert.assertNotNull(portfolio.get(0));
    Assert.assertEquals("test1", portfolio.get(0).name());
  }

  @Test
  public void parseTransactionalPortfolio() {

    String transactionalString = "TYPE : TRANSACTIONAL\n"
            + "Portfolio Name : transaction10\n"
            + "PURCHASES : \n"
            + "Stock Symbol : GOOG,Quantity : 10.0,Date of Purchase : 2022-10-24,Commission Fee : "
            + "100.0\n"
            + "Stock Symbol : AAPL,Quantity : 10.0,Date of Purchase : 2022-11-01,Commission Fee : "
            + "10.0\n"
            + "SALE : \n"
            + "Stock Symbol : AAPL,Quantity : 5.0,Date of Purchase : 2022-11-01,Commission "
            + "Fee : 10.0\n"
            + "------ END ------\n";
    List<Portfolio> portfolio = PortfolioDataParser.parse(transactionalString);
    Assert.assertEquals(1, portfolio.size());
    Assert.assertNotNull(portfolio.get(0));
    Assert.assertEquals("transaction10", portfolio.get(0).name());
  }

  @Test
  public void parseTransactionalAndSimulated() {
    String portfolios = "TYPE : TRANSACTIONAL\n"
            + "Portfolio Name : transaction10\n"
            + "PURCHASES : \n"
            + "Stock Symbol : GOOG,Quantity : 10.0,Date of Purchase : 2022-10-24,Commission Fee "
            + ": 100.0\n"
            + "Stock Symbol : AAPL,Quantity : 10.0,Date of Purchase : 2022-11-01,Commission Fee "
            + ": 10.0\n"
            + "SALE : \n"
            + "Stock Symbol : AAPL,Quantity : 5.0,Date of Purchase : 2022-11-01,Commission Fee : "
            + "10.0\n"
            + "------ END ------\n"
            + "TYPE : SIMULATED\n"
            + "Portfolio Name : test1\n"
            + "STOCKS : \n"
            + "Stock Symbol : GOOGL,Quantity : 1.0\n"
            + "Stock Symbol : AAPL,Quantity : 4.0\n"
            + "------ END ------\n";
    List<Portfolio> portfolio = PortfolioDataParser.parse(portfolios);
    Assert.assertEquals(2, portfolio.size());
    Assert.assertNotNull(portfolio.get(0));
    Assert.assertNotNull(portfolio.get(1));
    Assert.assertEquals("transaction10", portfolio.get(0).name());
    Assert.assertEquals("test1", portfolio.get(1).name());
  }
}
