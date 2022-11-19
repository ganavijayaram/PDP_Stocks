package model.portfolio;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.TreeMap;

import model.plot.Graph;

import model.plot.LineGraph;
import model.stock.StockModel;
import model.stock.StockModelImpl;

/**
 * A public class for Flexible Portfolio which implements Flexible Portfolio.
 */
public class FlexiblePortfolio implements Flexible {
  private Map<String, TreeMap<Date, Map<String, Integer>>> flexiblePortfolio;

  private Map<String, Date> portfolioCreationDate;

  private Map<String, TreeMap<Date, Double>> investment;

  private Map<String, TreeMap<Date, Double>> value;

  private Map<String, TreeMap<Date, Map<String, Double>>> allCosts;

  protected String portfolioName;
  protected String tickerSymbol;

  protected Date date;

  private StockModel stock = new StockModelImpl();

  /**
   * A public constructor for FlexiblePortfolio class.
   */

  public FlexiblePortfolio() {
    this.flexiblePortfolio = new HashMap<>();
    this.portfolioCreationDate = new HashMap<>();
    this.investment = new HashMap<>();
    this.value = new HashMap<>();
    this.allCosts = new HashMap<>();

  }

  @Override
  public void createPortfolio(String portfolioName, String tickerSymbol, Date date)
          throws IllegalArgumentException {
    if (flexiblePortfolio.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio exists");
    }
    flexiblePortfolio.put(portfolioName, new TreeMap<>());
    portfolioCreationDate.put(portfolioName, date);
    value.put(portfolioName, new TreeMap<>());
    value.get(portfolioName).put(date, 0.0);
    investment.put(portfolioName, new TreeMap<>());
    investment.get(portfolioName).put(date, 0.0);
  }

  @Override
  public void sellStocks(String givenPortfolioName, String givenTickerSymbol,
                         Date givenDate, Integer givenShares, Double commission)
          throws IllegalArgumentException {
    if (flexiblePortfolio.containsKey(givenPortfolioName)) {

      Map<String, Integer> dateRecord;
      if (flexiblePortfolio.get(givenPortfolioName).containsKey(givenDate)) {
        //get the current record
        dateRecord = flexiblePortfolio.get(givenPortfolioName).get(givenDate);
      } else {
        //get the  latest record
        Date lastDate = getLastDate(givenPortfolioName, givenDate);
        dateRecord = flexiblePortfolio.get(givenPortfolioName).get(lastDate);
        if (dateRecord == null) {
          flexiblePortfolio.get(givenPortfolioName).put(givenDate, new HashMap<>());
        } else {
          Map<Date, Map<String, Integer>> newRecord = flexiblePortfolio.get(givenPortfolioName);
          flexiblePortfolio.get(givenPortfolioName).put(givenDate, dateRecord);
        }
        dateRecord = flexiblePortfolio.get(givenPortfolioName).get(givenDate);
      }
      //update the record
      boolean sold = false;
      int currShares = 0;
      if (!dateRecord.isEmpty()) {
        if (dateRecord.containsKey(givenTickerSymbol)) {
          int shares = dateRecord.get(givenTickerSymbol);
          if (shares >= givenShares) {
            currShares = shares - givenShares;
            dateRecord.put(givenTickerSymbol, currShares);
            sold = true;
          }
        }
        //update the future records only if stocks are sold successfully
        if (sold) {
          updateRecords(givenPortfolioName, givenTickerSymbol, givenDate, currShares);
          updateCosts(givenTickerSymbol, givenDate);
          updateInvestment(givenPortfolioName, givenTickerSymbol, givenDate, 0,
                  commission);
        }
      } else {
        throw new IllegalArgumentException("Enough number of shares not present");
      }
    } else {
      throw new IllegalArgumentException("Invalid Portfolio Name");
    }
  }

  private Date getLastDate(String givenPortfolioName, Date givenDate) {
    Date lastDate = portfolioCreationDate.get(givenPortfolioName);
    for (Date d : flexiblePortfolio.get(givenPortfolioName).keySet()) {
      if (d.compareTo(givenDate) < 0) {
        lastDate = d;
      } else {
        break;
      }
    }
    return lastDate;
  }

  private void updateRecords(String givenPortfolioName, String givenTickerSymbol,
                             Date givenDate, Integer currShares) {
    Map<Date, Map<String, Integer>> dateRecords = flexiblePortfolio.get(givenPortfolioName);
    for (Date d : dateRecords.keySet()) {
      if (d.compareTo(givenDate) > 0) {
        if (dateRecords.get(d).containsKey(givenTickerSymbol)) {
          if (currShares == 0) {
            dateRecords.get(d).remove(givenTickerSymbol);
          } else {
            dateRecords.get(d).put(givenTickerSymbol, currShares);
          }
        }
      }
    }
  }

  @Override
  public TreeMap<String, Integer> performance(String givenPortfolioName,
                                              Date strDate, Date eDate)
          throws IllegalArgumentException {
    Graph graph = new LineGraph();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    String startDate = formatter.format(strDate);
    String endDate = formatter.format(eDate);
    int years = graph.findYears(startDate, endDate);
    int months = graph.findMonth(startDate, endDate);
    int days = graph.findDays(startDate, endDate);
    TreeMap<String, Integer> tm = new TreeMap<>();
    return tm;
  }


  @Override
  public void buy(String givenPortfolioName, String givenTickerSymbol,
                  Date givenDate, int givenShares,
                  Double commission) throws IllegalArgumentException {
    //Taking care of buying before portfolio is created
    if (portfolioCreationDate.containsKey(givenPortfolioName)) {
      //PASS Check date is given portfolio
      if (givenDate.compareTo(portfolioCreationDate.get(givenPortfolioName)) < 0) {
        throw new IllegalArgumentException("Portfolio does not exist!");
      }
    } else {
      throw new IllegalArgumentException("Portfolio does not exist");
    }
    if (flexiblePortfolio.containsKey(givenPortfolioName)) {
      //old
      TreeMap<Date, Map<String, Integer>> activePortfolio =
              flexiblePortfolio.get(givenPortfolioName);
      //Given date exists
      if (activePortfolio.containsKey(givenDate)) {
        //if ticker exists in stock model
        if (activePortfolio.get(givenDate).containsKey(givenTickerSymbol)) {
          int presentShare = activePortfolio.get(givenDate).get(givenTickerSymbol);
          activePortfolio.get(givenDate).put(givenTickerSymbol, givenShares + presentShare);
        } else {
          activePortfolio.get(givenDate).put(givenTickerSymbol, givenShares);
        }
      }
      //Given date does not exist
      else {
        //get the lastest record
        //if hashmap is empty
        if (activePortfolio.isEmpty()) {
          activePortfolio.put(givenDate, new HashMap<>());
          activePortfolio.get(givenDate).put(givenTickerSymbol, givenShares);
        } else {
          //get latest record
          Date lastDate = getLastDate(givenPortfolioName, givenDate);
          //add all the stocks from previous date
          activePortfolio.put(givenDate, activePortfolio.get(lastDate));
          int currShares = 0;
          //ticker symbol exists
          if (activePortfolio.get(givenDate).containsKey(givenTickerSymbol)) {
            currShares = activePortfolio.get(lastDate).get(givenTickerSymbol);
          }
          int finalCurrShares = currShares;

          Map<String, Integer> m = new HashMap<>();
          m.put(givenTickerSymbol, givenShares + finalCurrShares);
          activePortfolio.put(givenDate, m);
        }
      }
    } else {
      throw new IllegalArgumentException("Portfolio does not exist");
    }
    updateCosts(givenTickerSymbol, givenDate);
    updateInvestment(givenPortfolioName, givenTickerSymbol, givenDate, givenShares, commission);
    updateValue(givenPortfolioName, givenTickerSymbol, givenDate, givenShares);
  }

  private void updateValue(String givenPortfolioName, String givenTickerSymbol,
                           Date givenDate, Integer givenShares) {
    if (value.containsKey(givenPortfolioName)) {
      if (value.get(givenPortfolioName).containsKey(givenDate)) {
        Double currVal = value.get(givenPortfolioName).get(givenDate);
        Double newVal = stock.getClosingValue(givenTickerSymbol, givenDate) * givenShares;

        value.get(givenPortfolioName).put(givenDate, currVal + newVal);
      } else {
        Double newVal = stock.getClosingValue(givenTickerSymbol, givenDate) * givenShares;
        value.get(givenPortfolioName).put(givenDate, newVal);

      }
    } else {
      throw new IllegalArgumentException("Portfolio not found");
    }

  }

  private void updateCosts(String givenTickerSymbol, Date givenDate) {

    if (allCosts.containsKey(givenTickerSymbol)) {
      if (!allCosts.get(givenTickerSymbol).containsKey(givenDate)) {
        Map<String, Double> costRecords = allCosts.get(givenTickerSymbol).get(givenDate);

        double closeValue = stock.getClosingValue(givenTickerSymbol, givenDate);
        allCosts.get(givenTickerSymbol).put(givenDate, new HashMap<>());
        allCosts.get(givenTickerSymbol).get(givenDate).put("close", closeValue);

      }
    } else {

      double closeValue = stock.getClosingValue(givenTickerSymbol, givenDate);
      allCosts.put(givenTickerSymbol, new TreeMap<>());
      allCosts.get(givenTickerSymbol).put(givenDate, new HashMap<>());
      allCosts.get(givenTickerSymbol).get(givenDate).put("close", closeValue);
    }
  }

  private void updateInvestment(String givenPortfolioName, String givenTickerSymbol,
                                Date givenDate, Integer numShares, Double commission) {
    if (investment.containsKey(givenPortfolioName)) {

      double cost = 0;
      if (allCosts.containsKey(givenTickerSymbol)) {

        if (allCosts.get(givenTickerSymbol).containsKey(givenDate)) {

          cost = allCosts.get(givenTickerSymbol).get(givenDate).get("close");
        }
        //I think the else is redundant, because updating already happens
        else {

          cost = stock.getClosingValue(givenTickerSymbol, givenDate);
          double finalCost = cost;
          TreeMap<Date, Map<String, Double>> dateRecord = allCosts.get(givenTickerSymbol);
          dateRecord.put(givenDate, new TreeMap<>());
          dateRecord.get(givenDate).put("close", finalCost);
        }
      } else {

        //cost = stock.getClosingValue(givenTickerSymbol, givenDate);
        cost = allCosts.get(givenTickerSymbol).get(givenDate).get("close");
        double finalCost = cost;
        allCosts.put(givenTickerSymbol, new TreeMap<>());
        allCosts.get(givenTickerSymbol).put(givenDate, new TreeMap<>());
        allCosts.get(givenTickerSymbol).get(givenDate).put("close", finalCost);
      }
      //Update the future records investment
      Map<Date, Double> dateRecords = investment.get(givenPortfolioName);
      for (Date d : dateRecords.keySet()) {

        if (d.compareTo(givenDate) > 0 || d.compareTo(givenDate) == 0) {

          double currentValue = investment.get(givenPortfolioName).get(d);

          dateRecords.put(givenDate, (currentValue - cost) + commission);

        }
      }
      //Date not present

      if (!investment.get(givenPortfolioName).containsKey(givenDate)) {

        investment.get(givenPortfolioName).put(givenDate, cost + commission);
      }

    }
  }

  @Override
  public String getComposition(String givenPortfolioName, Date givenDate)
          throws IllegalArgumentException {
    StringBuilder composition = new StringBuilder();
    if (flexiblePortfolio.containsKey(givenPortfolioName)) {
      if (flexiblePortfolio.get(givenPortfolioName).containsKey(givenDate)) {
        if (flexiblePortfolio.get(givenPortfolioName).get(givenDate) == null) {
          return "No data in getComposition";
        }
        if (flexiblePortfolio.get(givenPortfolioName).get(givenDate).isEmpty()) {
          return "No data in getComposition";
        }
        for (String s : flexiblePortfolio.get(givenPortfolioName).get(givenDate).keySet()) {
          composition.append(s + " "
                  + flexiblePortfolio.get(givenPortfolioName).get(givenDate).get(s)).append("\n");
        }
        //flexiblePortfolio.get(givenPortfolioName).get(givenDate).forEach((key, value) -> {
        //composition.append(toString(key, value)).append("\n");
        //});
      } else if (flexiblePortfolio.get(givenPortfolioName).isEmpty()) {
        return "No Stocks!";
      } else {
        Date lastDate = getLastDate(givenPortfolioName, givenDate);
        flexiblePortfolio.get(givenPortfolioName).get(lastDate).forEach((key, value) -> {
          composition.append(toString(key, value)).append("\n");
        });
      }
    } else {
      throw new IllegalArgumentException("Portfolio Not Present");
    }
    return composition.toString();
  }

  public String toString(String givenTicker, int numberOfShares) {
    return String.format("Ticker: %s, Shares: %d", givenTicker, numberOfShares);
  }



  @Override
  public double checkInvestment(String givenPortfolioName, Date givenDate)
          throws IllegalArgumentException {
    if (flexiblePortfolio.containsKey(givenPortfolioName)) {
      if (flexiblePortfolio.get(givenPortfolioName).containsKey(givenDate)) {
        return investment.get(givenPortfolioName).get(givenDate);
      } else {
        //most recent date
        Date lastDate = getLastDate(givenPortfolioName, givenDate);
        return investment.get(givenPortfolioName).get(lastDate);
      }
    } else {
      throw new IllegalArgumentException("Portfolio does not exist");
    }

  }

  @Override
  public Double checkValue(String givenPortfolioName, Date givenDate)
          throws IllegalArgumentException {
    if (flexiblePortfolio.containsKey(givenPortfolioName)) {
      if (value.get(givenPortfolioName).containsKey(givenDate)) {
        return value.get(givenPortfolioName).get(givenDate);
      } else {
        //most recent date
        Date lastDate = getLastDate(givenPortfolioName, givenDate);
        return value.get(givenPortfolioName).get(lastDate);
      }
    } else {
      throw new IllegalArgumentException("Portfolio does not exist");
    }
  }

  /**
   * To save file.
   */
  public void saveFile() {
    for (String portfolioName : flexiblePortfolio.keySet()) {
      StringBuilder saved = new StringBuilder().append("CreationDate,Date, "
              + "Investment,Value,Ticker,NumberOfShares,CloseValue").append("\n");
      saved.append(portfolioCreationDate.get(portfolioName)).append(",");
      for (Date givenDate : flexiblePortfolio.get(portfolioName).keySet()) {
        TreeMap<Date, Map<String, Integer>> dateRecord = flexiblePortfolio.get(portfolioName);
        saved.append(givenDate).append(",");
        saved.append(investment.get(portfolioName).get(givenDate)).append(",");
        saved.append(value.get(portfolioName).get(givenDate)).append(",");
        for (String ticker : flexiblePortfolio.get(portfolioName).get(givenDate).keySet()) {
          //Map<String, Integer> tickerRecord= flexiblePortfolio.get(portfolioName).get(givenDate);
          saved.append(ticker).append(",");
          saved.append(flexiblePortfolio.get(portfolioName).get(givenDate).get(ticker)).append(",");
          saved.append(allCosts.get(ticker).get(givenDate).get("close"));
        }
      }
      saved.append("\n");
      String res = saved.toString();
      downloadToFile(portfolioName + "_flexible" + ".csv", res);
    }
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

  /**
   * Upload existing csv file.
   * @param fileName name of the file
   * @throws IOException throw exception
   * @throws ParseException throw exception
   */
  public void uploadExisting(String fileName) throws IOException, ParseException {
    if (!(fileName.endsWith(".csv"))) {
      throw new IllegalArgumentException("Wrong format.");
    }
    String portfolioName = fileName.substring(0, fileName.length() - 4);
    if (!portfolioName.contains("_flexible")) {
      throw new IllegalArgumentException("Please upload Flexible portfolio.");
    }
    BufferedReader b = new BufferedReader(new FileReader(fileName));
    String line;
    int counter = 0;
    flexiblePortfolio.put(portfolioName, new TreeMap<>());
    Map<Date, Map<String, Integer>> flexDateRecords = flexiblePortfolio.get(portfolioName);
    investment.put(portfolioName, new TreeMap<>());
    Map<Date, Double> investRecords = investment.get(portfolioName);
    value.put(portfolioName, new TreeMap<>());
    Map<Date, Double> valueRecords = value.get(portfolioName);
    allCosts.put(portfolioName, new TreeMap<>());
    Map<Date, Map<String, Double>> costRecord = allCosts.get(portfolioName);
    b.readLine();
    while ((line = b.readLine()) != null) {

      String[] cols = line.split(",");
      DateFormat formatter = new SimpleDateFormat("yyy-MM-dd");

      Date date = (Date) formatter.parse(cols[0]);


      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
              + "-" + cal.get(Calendar.DATE);
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      Date newDate = dateFormat.parse(formatedDate);

      portfolioCreationDate.put(portfolioName, newDate);
      Integer numShares = Integer.parseInt(cols[5]);
      if (flexDateRecords.containsKey(date)) {
        flexDateRecords.get(newDate).put(cols[4], numShares);

      } else {
        flexDateRecords.get(newDate).put(cols[4], numShares);
      }
      if (investRecords.containsKey(date)) {
        investRecords.put(newDate, Double.parseDouble(cols[2]));
      } else {
        investRecords.put(newDate, Double.parseDouble(cols[2]));
      }
      if (valueRecords.containsKey(newDate)) {
        valueRecords.put(newDate, Double.parseDouble(cols[3]));
      } else {
        valueRecords.put(newDate, Double.parseDouble(cols[3]));
      }
      if (costRecord.containsKey(date)) {
        costRecord.get(newDate).put(cols[4], Double.parseDouble(cols[3]));
      } else {
        costRecord.put(date, new TreeMap());
        costRecord.get(date).put(cols[4], Double.parseDouble(cols[3]));

      }
    }

  }

  @Override
  public String viewAllPortfolios() {
    StringBuilder portfolios = new StringBuilder();

    for (String pf : flexiblePortfolio.keySet()) {
      portfolios.append(pf).append("\n");
    }

    return portfolios.toString();
  }

  @Override
  public boolean checkValidPortfolioName(String s) {
    return flexiblePortfolio.containsKey(s);
  }
}
