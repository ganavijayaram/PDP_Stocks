package view;

import java.io.IOException;
import java.util.List;

/**
 * A class which implements CommandView interface.
 */
public class CommandViewImpl implements CommandView {

  private final Appendable out;

  /**
   * A public constructor for CommandViewImpl Class.
   * @param out appendable object
   * @throws IllegalArgumentException throws exception if out is null
   */
  public CommandViewImpl(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException();
    }
    this.out = out;
  }


  /**
   * Outputs the string to the output stream.
   * @param s the string to be output into the stream
   * @throws IllegalStateException exception for invalid arguments
   */
  private void output(String s) throws IllegalStateException {
    try {
      this.out.append(s);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void displayWelcomeMessage() {
    output("Welcome to the Upgraded Mock Stock Trading System!\n\n");
  }

  @Override
  public void displayMainOptions() {
    output("Please select one of the following options: \n"
            + "1. Create New Flexible Portfolio\n"
            + "2. Create New InFlexible Portfolio\n"
            + "3. Load Existing Flexible Portfolio\n"
            + "4. Load Existing InFlexible Portfolio\n"
            + "5. Exit\n\n");
  }

  @Override
  public void displayFlexiblePortfolioOptions() {
    output("Please select one of the following options: \n"
            + "1.  Upload Existing Portfolio\n"
            + "2.  Buy Stocks\n"
            + "3.  Sell Stocks\n"
            + "4.  View Portfolio Composition\n"
            + "5.  View Portfolio Value\n"
            + "6.  View Portfolio Cost-Basis\n"
            + "7.  View the Performance of the Portfolio\n"
            + "8.  View All Portfolios\n"
            + "9.  Main Menu\n\n");
  }

  @Override
  public void displayInFlexiblePortfolioOptions() {
    output("Please select one of the following options: \n"
            + "1.  Upload Existing Portfolio\n"
            + "2.  View Portfolio Composition\n"
            + "3.  View Portfolio Value\n"
            + "4.  View All Portfolios\n"
            + "5.  Main Menu\n\n");
  }

  @Override
  public void enterNumberOfShares() {
    output("Enter Number of shares\n");
  }

  @Override
  public void displayInvalidNumberOfShares() {
    output("Invalid Number of shares\n");
  }

  @Override
  public void displayInvalidOption() {
    output("Please enter Valid Option\n\n");
  }

  @Override
  public void displayInvalidPortfolioName() {
    output("Invalid Portfolio Name\n");
  }

  @Override
  public void displayInvalidDate() {
    output("Invalid Portfolio Date\n");
  }

  @Override
  public void displayPortfolioNameNotExists() {
    output("Portfolio Name does not exist\n");
  }

  @Override
  public void displayPortfolioNameExists() {
    output("Portfolio Name Exists\n");
  }

  @Override
  public void displayInvalidTickerName() {
    output("Invalid Ticker "
            + "Symbol\n");
  }

  @Override
  public void displayMessage(String s) {
    output(s + "\n");
  }

  @Override
  public void displayInvalidCommission() {
    output("Invalid Commission\n");
  }

  @Override
  public void displayInvalidMessage(String s) {
    output("Invalid " + s + "\n");
  }


  @Override
  public void enterPortfolioName() {
    output("Enter Portfolio Name\n");
  }

  @Override
  public void buyMoreStocks() {
    output("Do you want to buy more stocks?? Y/y for Yes N/y for N");
  }



  @Override
  public void enterTickerSymbol() {
    output("Please enter Ticker Symbol\n");
  }

  @Override
  public void enterDate(String s) {
    output("Enter " + s + "date in yyyy-mm-yy\n");
  }


  @Override
  public void enterFileName() {
    output("Please enter CSV filename\n");
  }

  @Override
  public void enterGetCommission() {
    output("Enter the commission amount\n");
  }

  @Override
  public void graphTitle(String s) {
  // yet to be implemented
  }

  @Override
  public void plot(List<String> s, List<Integer> count) {
  // yet to be implemented
  }

  @Override
  public void scale(Double s) {
  // yet to be implemented
  }
}
