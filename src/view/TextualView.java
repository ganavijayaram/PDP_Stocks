package view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.Features;
import model.utility.Utility;

/**
 * A class to implement the textual view.
 */
public class TextualView implements View {

  Map<Integer, Runnable> commands;
  private final Scanner scanner;
  private final PrintStream out;

  /**
   * Constructor to initialise the variables.
   *
   * @param input input stream
   * @param out   output stream
   */
  public TextualView(InputStream input, OutputStream out) {
    this.scanner = new Scanner(input);
    this.out = new PrintStream(out);
    commands = new HashMap<>();
  }

  @Override
  public void addFeatures(Features features) {
    this.initializeFeatures(features);
    while (true) {
      String menuOptions = "Main Menu :\n"
              + "1.  Create a new Simulated Portfolio\n"
              + "2.  Create a new Transactional Portfolio\n"
              + "3.  Purchase a Stock and Add it to Transactional Portfolio\n"
              + "4.  Sell a Stock from the Transactional Portfolio\n"
              + "5.  Get all the available Portfolios in the Application\n"
              + "6.  Get final composition for an Existing Portfolio \n"
              + "7.  Get composition for an Existing Portfolio at a specific date\n"
              + "8.  Get the total value on an Existing Portfolio\n"
              + "9.  Get the cost basis of an Existing Transactional Portfolio\n"
              + "10. Get the performance of an Existing Portfolio over a period of time\n"
              + "11. Save an Existing Portfolio to file\n"
              + "12. Load portfolios to the Application\n"
              + "13. Create a one time investment strategy\n"
              + "14. Create a recurring investment strategy\n"
              + "15. Get all the existing strategies\n"
              + "16. Apply a strategy to a portfolio\n"
              + "17. Create a Dollar Cost Averaging Portfolio\n"
              + "Enter the menu option you wish to choose.\n"
              + "Press and enter any other key to exit the application.\n";
      this.display(menuOptions);
      int menu = this.readMenu();
      if (!commands.containsKey(menu)) {
        this.display("You have decided to exit the application. See you next time\n");
        break;
      }
      commands.get(menu).run();
    }

  }

  private void initializeFeatures(Features features) {
    commands.put(1, () -> features.createInflexiblePortfolio(this.readTradeName(),
            this.readTradeData(false)));
    commands.put(2, () -> features.createFlexiblePortfolio(this.readTradeName()));
    commands.put(3, () -> features.buyStock(this.readTradeName(),
            this.readStockSymbol(),
            this.readShares(),
            this.readDateOfTransaction(),
            this.readCommissionFee()));
    commands.put(4, () -> features.sellStock(this.readTradeName(),
            this.readStockSymbol(),
            this.readShares(),
            this.readDateOfTransaction(),
            this.readCommissionFee()));
    commands.put(5, () -> {
      var portfolios = features.getPortfolios();
      String result = portfolios.size() > 0
              ? "Portfolio Names : \n" + String.join("\n", portfolios) + "\n"
              : "The application does not contain any portfolio.\n";
      this.display(result);
    });
    commands.put(6, () -> {
      var composition = features.composition(this.readTradeName());
      this.display(composition);
    });
    commands.put(7, () -> {
      var composition = features.composition(this.readTradeName(),
              this.readDateOfEvaluation());
      this.display(composition);
    });
    commands.put(8, () -> features.value(this.readTradeName(), this.readDateOfEvaluation()));
    commands.put(9, () -> features.costBasis(this.readTradeName(), this.readDateOfEvaluation()));
    commands.put(10, () -> {
      var portfolio = this.readTradeName();
      LocalDate from = this.readStartDate();
      LocalDate to = this.readEndDate();
      var values = features.values(portfolio,
              from,
              to);
      if (values != null) {
        this.display("Performance of portfolio " + portfolio + " from " + from + " to " + to
                + "\n");
        this.drawGraph(values);
      }
    });
    commands.put(11, () -> features.save("res/portfolio.txt", this.readTradeName()));
    commands.put(12, () -> features.load("res/portfolio.txt"));
    commands.put(13, () -> {
      String name = this.readStrategyName();
      Double principal = this.readPrincipal();
      var trade = this.readTradeData(true);
      var date = this.readDateOfTransaction();
      var commission = this.readCommissionFee();
      features.createStrategy(name,
              principal,
              trade,
              date,
              date,
              1,
              commission);
    });
    commands.put(14, () ->
            features.createStrategy(this.readStrategyName(),
                    this.readPrincipal(),
                    this.readTradeData(true),
                    this.readStartDate(),
                    this.isOngoingStrategy() ? this.readEndDate() : null,
                    this.readFrequency(),
                    this.readCommissionFee()
            ));
    commands.put(15, () -> {
      var strategies = features.getAllStrategy();
      String result = strategies.size() > 0
              ? "Strategy Names : \n" + String.join("\n", strategies) + "\n"
              : "The application does not contain any strategy.\n";
      this.display(result);
    });
    commands.put(16, () -> features.applyStrategy(this.readTradeName(),
            this.readStrategyName()));
    commands.put(17, () -> {
      var portfolioName = this.readTradeName();
      var strategyName = this.readStrategyName();
      features.createStrategy(strategyName,
              this.readPrincipal(),
              this.readTradeData(true),
              this.readStartDate(),
              this.isOngoingStrategy() ? this.readEndDate() : null,
              this.readFrequency(),
              this.readCommissionFee());
      features.createFlexiblePortfolio(portfolioName);
      features.applyStrategy(portfolioName,
              strategyName);
    });
  }


  @Override
  public String input() {
    return scanner.nextLine();
  }

  @Override
  public void display(String message) {
    this.out.print(message);
  }

  @Override
  public void error(String message) {
    this.display(message);
  }


  private int readMenu() {
    try {
      String input = this.input();
      // read the menu option input by the user
      return Integer.parseInt(input);
    } catch (Exception e) {
      // if the input is illegal then returns 0
      return 0;
    }
  }

  private boolean isOngoingStrategy() {
    boolean parsed = false;
    boolean isOngoing = false;
    String input = "";
    while (!parsed) {
      this.display("Do you wish to enter a end trade for the strategy?(y/n)\n");
      input = this.input();
      if (input.equals("y") || input.equals("Y")) {
        isOngoing = true;
        parsed = true;
      } else if (input.equals("n") || input.equals("N")) {
        isOngoing = false;
        parsed = true;
      } else {
        this.display("Invalid Input\n");
      }
    }
    return isOngoing;
  }

  private Double readPrincipal() {
    boolean isParsed = false;
    Double amount = 0.0;
    while (!isParsed) {
      try {
        this.display("Enter the amount you wish to invest\n");
        amount = Double.parseDouble(this.input());
        if (amount > 0) {
          isParsed = true;
        } else {
          this.display("Invalid amount entered\n");
        }
      } catch (NumberFormatException ex) {
        this.display("Invalid amount entered\n");
      }
    }
    return amount;
  }

  private void drawGraph(Map<LocalDate, Double> portfolioData) {
    Double scale = scale(portfolioData);
    List<LocalDate> keys = new ArrayList<>(portfolioData.keySet());
    Collections.sort(keys);
    for (LocalDate key : keys) {
      Double stars = portfolioData.get(key) / scale;
      this.display(key + ": " + "*".repeat(stars.intValue()) + "\n");
    }
    this.display("Scale : * = $" + scale + "\n");
  }

  private double scale(Map<LocalDate, Double> portfolioData) {
    var max = Collections.max(portfolioData.values());
    var min = Collections.min(portfolioData.values());
    Double scale = Utility.scale(max.intValue(), min.intValue());
    return scale;
  }

  private String readTradeName() {
    this.display("Enter the name of the portfolio\n");
    String portfolio = this.input();
    while (portfolio == null || portfolio.isBlank() || portfolio.isEmpty()) {
      this.display("You have entered an invalid name. Please enter the name again\n");
      portfolio = this.input().trim();
    }
    return portfolio;
  }

  private String readStrategyName() {
    this.display("Enter the name of the strategy\n");
    String portfolio = this.input();
    while (portfolio == null || portfolio.isBlank() || portfolio.isEmpty()) {
      this.display("You have entered an invalid name. Please enter the name again\n");
      portfolio = this.input().trim();
    }
    return portfolio;
  }

  private Double readSharePercentage() {
    this.display("Enter the weight of shares\n");
    boolean parsed = false;
    Double shares = 0.0;
    while (!parsed) {
      try {
        String input = this.input();
        shares = Double.parseDouble(input);
        if (shares <= 100 && shares > 0) {
          parsed = true;
        } else {
          this.display("Invalid weight\n");
          this.display("Please Enter the weight of stock again\n");
        }
      } catch (NumberFormatException e) {
        this.display("Invalid weight\n");
        this.display("Please Enter the weight of stock again\n");
      }
    }
    return shares;
  }

  private Double readShares() {
    this.display("Enter the number of shares\n");
    boolean parsed = false;
    Double shares = 0.0;
    while (!parsed) {
      try {
        String input = this.input();
        shares = Double.parseDouble(input);
        if (shares > 0) {
          parsed = true;
        } else {
          this.display("Invalid number of shares\n");
          this.display("Please enter the number of shares again.\n");
        }
      } catch (NumberFormatException e) {
        this.display("Invalid number of shares\n");
        this.display("Please enter the number of shares again.\n");
      }
    }
    // fractional shares are not allowed
    // If shares are fractional then user is asked to input the number of shares again
    // until he/she enters a whole number.
    while (shares % 1.0 != 0) {
      this.display("Fractional number of shares are not allowed\n");
      this.display("Please enter the number of shares again\n");
      String input = this.input();
      shares = Double.parseDouble(input);
    }
    return shares;
  }

  private String readStockSymbol() {
    this.display("Enter the stock symbol\n");
    String stock = this.input();
    return stock;
  }

  private LocalDate readDateFromUser() {
    while (true) {
      try {
        LocalDate date = LocalDate.parse(this.input());
        return date;
      } catch (DateTimeParseException e) {
        this.display("You have entered an invalid date.Please re-enter the date\n");
      }
    }
  }

  private LocalDate readDateOfTransaction() {
    this.display("Enter the date of the transaction (in YYYY-MM-DD format)\n");
    return this.readDateFromUser();
  }

  private LocalDate readDateOfEvaluation() {
    this.display("Enter the date of the evaluation (in YYYY-MM-DD format)\n");
    return readDateFromUser();
  }

  private LocalDate readStartDate() {
    this.display("Enter the start date (in YYYY-MM-DD format)\n");
    return readDateFromUser();
  }

  private LocalDate readEndDate() {
    this.display("Enter the end date (in YYYY-MM-DD format)\n");
    return readDateFromUser();
  }

  private double readCommissionFee() {
    this.display("Enter the commission fee for this transaction\n");
    while (true) {
      try {
        Double commission = Double.parseDouble(this.input());
        if (commission >= 0.0) {
          return commission;
        }
        this.display("You have entered an invalid commission fee.Please re-enter the fee\n");
      } catch (NumberFormatException e) {
        this.display("You have entered an invalid commission fee.Please re-enter the fee\n");
      }
    }
  }

  private Map<String, Double> readTradeData(boolean isPercentage) {
    Map<String, Double> stockData = new HashMap<>();
    // reads number of trades that the portfolio is composed of.
    int stocks = this.readNumberOfStocks();
    double totalShares = 0;
    for (int i = 0; i < stocks; i++) {
      // reads the stock symbol
      String stock = this.readStockSymbol();
      // reads the number of shares.
      Double shares = isPercentage ? this.readSharePercentage() : this.readShares();
      if (isPercentage) {
        while (totalShares + shares > 100) {
          this.display("Invalid Weight for the stock\n");
          shares = this.readSharePercentage();
        }
        totalShares += shares;
      }
      stockData.put(stock, stockData.getOrDefault(stock, 0.0) + shares);
    }
    // parse the map to set of trades and return the resultant
    return stockData;
  }

  private int readNumberOfStocks() {
    boolean parsed = false;
    int stocks = 0;
    while (!parsed) {
      try {
        this.display("Enter the number of stocks you wish to purchase\n");
        stocks = Integer.parseInt(this.input());
        if (stocks > 0) {
          parsed = true;
        } else {
          this.display("Invalid number of stocks\n");
        }
      } catch (NumberFormatException e) {
        this.display("Invalid number of stocks\n");
      }
    }
    return stocks;

  }

  private Integer readFrequency() {
    boolean parsed = false;
    Integer frequency = 0;
    while (!parsed) {
      try {
        this.display("Enter the Frequency in days\n");
        String input = this.input();
        frequency = Integer.parseInt(input);
        if (frequency > 0) {
          parsed = true;
        } else {
          this.display("Invalid Frequency Amount\n");
        }
      } catch (NumberFormatException e) {
        this.display("Invalid Frequency Amount\n");
      }
    }
    return frequency;
  }
}
