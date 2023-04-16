import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.portfolio.Portfolio;
import model.stock.Stock;
import model.trade.Trade;

/**
 * Abstract class to test the features of Simulated and Transactional Portoflios.
 */
public abstract class AbstractPortfolioTest {

  protected Portfolio portfolio;

  protected abstract Portfolio createPortfolio(String name, Set<Trade<Stock>> shares);

  @Test
  public void createPortfolio() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    Assert.assertNotNull(this.portfolio);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createPortfolio_InvalidStock() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOO", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
  }

  @Test
  public void name() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    Assert.assertEquals("portfolio1", this.portfolio.name());
  }

  @Test
  public void value() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    Assert.assertEquals(867.0,
            this.portfolio.value(LocalDate.parse("2022-11-04")), 0.01);
  }

  @Test
  public void composition() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    String portfolioComposition = this.portfolio.composition();
    String expected = "Portfolio Name : portfolio1\n" +
            "STOCKS : \n" +
            "Stock Symbol : GOOG,Quantity : 10.0\n" +
            "------ END ------\n";
    Assert.assertTrue(portfolioComposition.contains(expected));
  }

  @Test
  public void composition_AtDate() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    String portfolioComposition = this.portfolio.composition(LocalDate.parse("2022-11-01"));
    String expected = "Portfolio Name : portfolio1\n" +
            "STOCKS : \n" +
            "Stock Symbol : GOOG,Quantity : 10.0\n" +
            "------ END ------\n";
    Assert.assertTrue(portfolioComposition.contains(expected));
  }

  @Test(expected = IllegalArgumentException.class)
  public void analyze_startAfterEnd() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    LocalDate startDate = LocalDate.parse("2022-11-12");
    LocalDate endDate = LocalDate.parse("2022-11-11");
    Map<LocalDate, Double> values = this.portfolio.values(startDate, endDate);

  }

  @Test(expected = IllegalArgumentException.class)
  public void analyze_startInFuture() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    LocalDate startDate = LocalDate.now().plusDays(10);
    LocalDate endDate = LocalDate.parse("2022-11-11");
    Map<LocalDate, Double> values = this.portfolio.values(startDate, endDate);

  }

  @Test(expected = IllegalArgumentException.class)
  public void analyze_startAndEndInFuture() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    LocalDate startDate = LocalDate.now().plusDays(10);
    LocalDate endDate = LocalDate.now().plusDays(10);
    Map<LocalDate, Double> values = this.portfolio.values(startDate, endDate);

  }

  @Test
  public void analyze() {
    Set<Trade<Stock>> shares = new HashSet<>();
    shares.add(this.createPurchase("GOOG", 10.0,
            LocalDate.parse("2022-10-24"), 1.0));
    this.portfolio = createPortfolio("portfolio1", shares);
    LocalDate startDate = LocalDate.parse("2022-11-07");
    LocalDate endDate = LocalDate.parse("2022-11-11");
    Map<LocalDate, Double> values = this.portfolio.values(startDate, endDate);
    Assert.assertEquals(5, values.size());

    Assert.assertEquals(886.5, values.get(startDate), 0.01);
    Assert.assertEquals(889.09, values.get(startDate.plusDays(1)), 0.01);
    Assert.assertEquals(874.0, values.get(startDate.plusDays(2)), 0.01);
    Assert.assertEquals(941.7, values.get(startDate.plusDays(3)), 0.01);
    Assert.assertEquals(967.30, values.get(startDate.plusDays(4)), 0.01);
  }


  protected abstract Trade<Stock> createPurchase(String stock, Double quantity,
                                                 LocalDate date, Double commission);
}
