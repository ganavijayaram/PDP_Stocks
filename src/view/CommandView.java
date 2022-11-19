package view;

import java.util.List;

/**
 * This Interface is used to display command line options.
 */
public interface CommandView {
  /**
   * Displays the welcome message.
   */
  void displayWelcomeMessage();

  /**
   * Displays the main message.
   */

  void displayMainOptions();

  /**
   * Displays the flexible portfolio options.
   */

  void displayFlexiblePortfolioOptions();

  /**
   * Display the inflexible options.
   */

  void displayInFlexiblePortfolioOptions();

  /**
   * Enter number of shares.
   */

  void enterNumberOfShares();

  /**
   * Dispaly invalid number of shares.
   */

  void displayInvalidNumberOfShares();

  /**
   * Display invalid option.
   */

  void displayInvalidOption();

  /**
   * Display invalid portfolio.
   */

  void displayInvalidPortfolioName();

  /**
   * Display invalid date.
   */

  void displayInvalidDate();

  /**
   * Display portfolio not exists.
   */

  void displayPortfolioNameNotExists();

  /**
   * Diplay portfolio exists.
   */

  void displayPortfolioNameExists();

  /**
   * Display invalid Ticker.
   */

  void displayInvalidTickerName();

  /**
   * Method to display message.
   *
   * @param s message to be diaplyed
   */

  void displayMessage(String s);

  /**
   * Display invalid commission.
   */

  void displayInvalidCommission();

  /**
   * Display invalid Message.
   *
   * @param s message
   */

  void displayInvalidMessage(String s);


  /**
   * Enter portfolio name.
   */

  void enterPortfolioName();

  /**
   * Buy more stocks.
   */

  void buyMoreStocks();


  /**
   * Enter ticker symbol.
   */

  void enterTickerSymbol();

  /**
   * Enter date.
   *
   * @param s date
   */

  void enterDate(String s);


  /**
   * Enter file name.
   */

  void enterFileName();

  /**
   * Enter get commission.
   */

  void enterGetCommission();

  /**
   * Graph title.
   *
   * @param s title
   */

  void graphTitle(String s);

  /**
   * A method to plot the graph.
   *
   * @param s     Timestamp
   * @param count Count of the stars for the given timestamp
   */

  void plot(List<String> s, List<Integer> count);

  /**
   * Scale of the graph.
   *
   * @param s scale
   */

  void scale(Double s);
}
