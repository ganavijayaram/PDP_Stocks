package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.portfolio.Flexible;
import model.portfolio.Portfolio;
import model.portfolio.FlexiblePortfolio;
import model.portfolio.InFlexiblePortfolio;
import view.CommandView;

/**
 * A public class MainControllerImpl which implements MainController interface.
 */
public class MainControllerImpl implements MainController {

  private final Readable in;

  private CommandView view;

  /**
   * A public constructor for the MainControllerImpl class.
   *
   * @param in   Readable object
   * @param view CommandView object
   * @throws IllegalArgumentException throw exception if the objects are null
   */

  public MainControllerImpl(Readable in, CommandView view)
          throws IllegalArgumentException {
    if (in == null) {
      throw new IllegalArgumentException();
    }
    this.in = in;
    this.view = view;
  }

  /**
   * Separates the input scanner object by blank space or \n.
   *
   * @param scan the input scanner object
   * @return the string input
   * @throws IllegalStateException exception for invalid arguments
   */
  private String input(Scanner scan) throws IllegalStateException {
    String s;
    try {
      s = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException();
    }
    return s;
  }


  @Override
  public void begin() {
    Scanner scan = new Scanner(this.in);
    String portFolioName;
    Date date = null;
    String tickerSymbol;
    view.displayWelcomeMessage();
    Flexible fObj = new FlexiblePortfolio();
    Portfolio pObj = new InFlexiblePortfolio();
    loop:
    while (true) {
      view.displayMainOptions();
      String option = input(scan);
      switch (option) {
        case "1":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            continue;
          }
          if (fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              continue;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }

          tickerSymbol = getTickerSymbol();
          if (tickerSymbol.equals("0")) {
            continue;
          }
          fObj.createPortfolio(portFolioName, tickerSymbol, date);
          break;
        case "2":

          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            continue;
          }
          if (pObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameExists();
            continue;
          }
          tickerSymbol = getTickerSymbol();
          if (tickerSymbol.equals("0")) {
            continue;
          }
          pObj.createPortfolio(portFolioName, tickerSymbol, date);
          view.buyMoreStocks();
          String op = input(scan);
          while (op.equals("Y") || op.equals("y")) {
            portFolioName = getPortFolioName();
            if (portFolioName.equals("0")) {
              continue;
            }
            if (!pObj.checkValidPortfolioName(portFolioName)) {
              view.displayPortfolioNameExists();
              continue;
            }
            int shares = getNumberOfShares();
            if (shares == -1) {
              break loop;
            }

            tickerSymbol = getTickerSymbol();
            if (tickerSymbol.equals("0")) {
              continue;
            }
            pObj.buy(portFolioName, tickerSymbol, date, shares, 0.0);
            view.buyMoreStocks();
            op = input(scan);
          }
          break;
        case "3":
          flexiblePortfolioOptions(fObj);
          break;
        case "4":
          inFlexiblePortfolioOptions(pObj);
          break;
        case "5":
          fObj.saveFile();
          //pObj.saveFile();
          break loop;
        default:
          view.displayInvalidOption();
      }
    }
  }

  private void flexiblePortfolioOptions(Flexible fObj) {
    Scanner scan = new Scanner(this.in);
    String portFolioName;
    Date date = null;
    String tickerSymbol;
    Double commission;
    int shares;
    loop:
    while (true) {
      view.displayFlexiblePortfolioOptions();
      String flexOption = input(scan);
      switch (flexOption) {
        case "1":
          String fileName = getFileName();
          if (fileName.equals("")) {
            break loop;
          }
          try {
            fObj.uploadExisting(fileName);
          } catch (IOException e) {
            view.displayInvalidMessage(e.getMessage());
          } catch (ParseException e) {
            view.displayInvalidMessage(e.getMessage());
          }
          break;
        case "2":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          tickerSymbol = getTickerSymbol();
          if (tickerSymbol.equals("0")) {
            break loop;
          }
          shares = getNumberOfShares();
          if (shares == -1) {
            break loop;
          }
          commission = getCommission();
          try {
            fObj.buy(portFolioName, tickerSymbol, date, shares, commission);
          } catch (IllegalArgumentException e) {
            view.displayMessage(e.getMessage());
            break loop;
          }
          break;
        case "3":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          tickerSymbol = getTickerSymbol();
          if (tickerSymbol.equals("0")) {
            break loop;
          }
          shares = getNumberOfShares();
          if (shares == -1) {
            break loop;
          }
          commission = getCommission();
          try {
            fObj.sellStocks(portFolioName, tickerSymbol, date, shares, commission);
          } catch (IllegalArgumentException e) {
            view.displayMessage(e.getMessage());
            continue;
          }
          break;
        case "4":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          view.displayMessage(fObj.getComposition(portFolioName, date));
          break;
        case "5":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          view.displayMessage("Cost-Basis of " + portFolioName + " "
                  + fObj.checkValue(portFolioName, date).toString());
          break;
        case "6":
          Date startDate;
          Date endDate;
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          view.displayMessage("Cost-Basis of " + portFolioName + " "
                  + fObj.checkInvestment(portFolioName, date));
          break;
        case "7":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!fObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            startDate = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          try {
            endDate = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          //view.graphTitle();
          //view.plot(fObj.performance(portFolioName, endDate, startDate));
          //view.scale();
          break;
        case "8":
          view.displayMessage(fObj.viewAllPortfolios());
          break;
        case "9":
          break loop;
        default:
          view.displayInvalidOption();
      }
    }
  }

  private void inFlexiblePortfolioOptions(Portfolio pObj) {
    Scanner scan = new Scanner(this.in);
    String portFolioName;
    Date date = null;
    loop:
    while (true) {
      view.displayInFlexiblePortfolioOptions();
      String inflexOption = input(scan);
      switch (inflexOption) {
        case "1":
          String fileName = getFileName();
          if (fileName.equals("")) {
            break loop;
          }
          try {
            pObj.uploadExisting(fileName);
          } catch (IOException e) {
            view.displayInvalidMessage(e.getMessage());
          } catch (ParseException e) {
            view.displayInvalidMessage(e.getMessage());
          }
          break;
        case "2":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!pObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          view.displayMessage(pObj.getComposition(portFolioName, date));
          break;
        case "3":
          portFolioName = getPortFolioName();
          if (portFolioName.equals("0")) {
            break loop;
          }
          if (!pObj.checkValidPortfolioName(portFolioName)) {
            view.displayPortfolioNameNotExists();
            continue;
          }
          try {
            date = getDate();
            if (date.equals("0")) {
              break loop;
            }
          } catch (ParseException e) {
            view.displayInvalidDate();
          }
          pObj.checkValue(portFolioName, date);
          break;
        case "4":
          pObj.viewAllPortfolios();
          break;
        case "5":
          break loop;
        default:
          view.displayInvalidOption();
          break;
      }
    }
  }

  private String getPortFolioName() {
    Scanner scan = new Scanner(this.in);
    view.enterPortfolioName();
    String portfolioName = input(scan);
    if (portfolioName == null) {
      view.displayInvalidPortfolioName();
      return "0";
    }
    //Call the model to check validity
    return portfolioName;
  }

  private Date getDate() throws ParseException {
    Scanner scan = new Scanner(this.in);
    view.enterDate("Enter Date ");
    String stringDate = input(scan);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = dateFormat.parse(stringDate);
    if (date == null) {
      view.displayInvalidDate();
      return dateFormat.parse("00-00-0000");
    }
    return date;
  }

  private String getTickerSymbol() {
    Scanner scan = new Scanner(this.in);
    view.enterTickerSymbol();
    String tickerSymbol = input(scan);
    if (tickerSymbol == null) {
      view.displayInvalidTickerName();
      return "0";
    }
    return tickerSymbol;
  }

  private double getCommission() {
    Scanner scan = new Scanner(this.in);
    view.enterGetCommission();
    double commission = Integer.parseInt(input(scan));
    if (commission < 0) {
      view.displayInvalidCommission();
      return -1;
    }
    return commission;
  }

  private String getFileName() {
    Scanner scan = new Scanner(this.in);
    view.enterFileName();
    String fileName = input(scan);
    if (fileName.equals("")) {
      return "";
    }
    return fileName;
  }

  private int getNumberOfShares() {
    Scanner scan = new Scanner(this.in);
    view.enterNumberOfShares();
    int shares;
    try {
      shares = Integer.parseInt(input(scan));
      if (shares < 0) {
        view.displayInvalidNumberOfShares();
        return -1;
      }
      return shares;
    } catch (IllegalArgumentException e) {
      view.displayInvalidNumberOfShares();
    }
    return -1;
  }
}
