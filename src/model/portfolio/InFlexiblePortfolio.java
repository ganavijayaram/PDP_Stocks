package model.portfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.TreeMap;

import model.stock.StockModel;
import model.stock.StockModelImpl;

/**
 * A public class for inflexible portfolio which implements Portfolio interface.
 */
public class InFlexiblePortfolio implements Portfolio {
  private Map<String, Map<String, Integer>> inFlexiblePortfolio;

  private Map<String, Date> portfolioCreationDate;

  private Map<String, TreeMap<Date, Map<String, Double>>> allCosts;

  private Date date;

  /**
   * A public constructor for InFlexiblePortfolio class.
   */

  public InFlexiblePortfolio() {
    inFlexiblePortfolio = new HashMap<>();
    this.date = null;

  }

  @Override
  public void createPortfolio(String portfolioName, String tickerSymbol, Date date)
          throws IllegalArgumentException {
    if (inFlexiblePortfolio.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio exists");
    }
    inFlexiblePortfolio.put(portfolioName, new HashMap<>());

  }

  @Override
  public void buy(String givenPortfolioName, String givenTickerSymbol, Date givenDate,
                  int givenShares, Double commission) throws IllegalArgumentException {
    if (inFlexiblePortfolio.get(givenPortfolioName).containsKey(givenTickerSymbol)) {
      int currShares = inFlexiblePortfolio.get(givenPortfolioName).get(givenTickerSymbol);
      inFlexiblePortfolio.get(givenPortfolioName).put(givenTickerSymbol, currShares + givenShares);
    } else {
      inFlexiblePortfolio.get(givenPortfolioName).put(givenPortfolioName, givenShares);
    }
  }

  @Override
  public String getComposition(String givenPortfolioName, Date givenDate)
          throws IllegalArgumentException {
    StringBuilder composition = new StringBuilder();
    if (inFlexiblePortfolio.containsKey(givenPortfolioName)) {
      for (String s : inFlexiblePortfolio.get(givenPortfolioName).keySet()) {
        composition.append(toString(s, inFlexiblePortfolio.get(givenPortfolioName).get(s)));
      }
    } else {
      throw new IllegalArgumentException("Portfolio does not exist");
    }
    return composition.toString();
  }

  public String toString(String givenTicker, int numberOfShares) {
    return String.format("Ticker: %s, Shares: %d", givenTicker, numberOfShares);
  }

  @Override
  public Double checkValue(String givenPortfolioName, Date givenDate)
          throws IllegalArgumentException {
    this.date = givenDate;
    double totalValue = 0;
    if (portfolioCreationDate.containsKey(givenPortfolioName)) {
      if (inFlexiblePortfolio.containsKey(givenPortfolioName)) {
        for (String s : inFlexiblePortfolio.get(givenPortfolioName).keySet()) {
          if (allCosts.containsKey(s)) {
            if (allCosts.get(s).containsKey(givenDate)) {
              totalValue += allCosts.get(s).get(givenDate).get("close");
            } else {
              StockModel stocks = new StockModelImpl();
              double costs = stocks.getClosingValue(s, givenDate);
              allCosts.get(s).get(givenDate).put("close", costs);
            }
            return totalValue;
          } else {
            throw new IllegalArgumentException("Portfolio does not exists");
          }
        }
      } else {
        throw new IllegalArgumentException("Portfolio does not exists");
      }
      return totalValue;
    }
    return totalValue;
  }

  private void downloadToFile(String fileName, String content) {
    try {
      File writeName = new File(fileName);
      writeName.createNewFile();
      BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
      out.write(content);
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void loadPortfolio(String fileName) throws IOException {
    if (!(fileName.endsWith(".csv"))) {
      throw new IllegalArgumentException("Wrong format.");
    }

    String portfolioName = fileName.substring(0, fileName.length() - 4);
    BufferedReader b = new BufferedReader(new FileReader(fileName));
    String line;
    int counter = 0;
    b.readLine();
    while ((line = b.readLine()) != null) {
      String[] cols = line.split(",");
      if (counter != 0 && !(cols[0].equals("Total"))) {
        double costs;
        Integer shares;
        try {
          shares = Integer.parseInt(cols[1]);
        } catch (Exception e) {
          throw new IllegalArgumentException("Share should be an integer.");
        }

        try {
          costs = Double.parseDouble(cols[2]);
        } catch (Exception e) {
          throw new IllegalArgumentException("Cost should be a double.");
        }

        inFlexiblePortfolio.get(portfolioName).put(cols[0], shares);
        allCosts.get(portfolioName).get(this.date).put(cols[0], costs);
      }
      counter++;
    }
  }

  @Override
  public void uploadExisting(String fileName) throws IOException, ParseException {
    loadPortfolio(fileName);
  }

  @Override
  public String viewAllPortfolios() {
    StringBuilder portfolios = new StringBuilder();
    for (String s : inFlexiblePortfolio.keySet()) {
      portfolios.append(s + "\n");
    }
    return portfolios.toString();
  }

  @Override
  public boolean checkValidPortfolioName(String s) {
    return inFlexiblePortfolio.containsKey(s);
  }

  @Override
  public void saveFile() {
    for (String pf : inFlexiblePortfolio.keySet()) {
      StringBuilder saved = new StringBuilder().append("Stock,Shares,Cost").append("\n");
      String res;
      double total = 0;
      for (String s : inFlexiblePortfolio.keySet()) {
        for (String ticker : inFlexiblePortfolio.get(s).keySet()) {
          saved.append(ticker).append(",");
          saved.append(inFlexiblePortfolio.get(s).get(s)).append(",");
          saved.append(allCosts.get(ticker).get(date).get("close")).append(",");
        }
      }
      res = saved.toString();
      downloadToFile(pf + ".csv", res);
    }
  }
}