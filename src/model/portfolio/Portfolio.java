package model.portfolio;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * This is a public interface for the portfolio related operations.
 */
public interface Portfolio {

  /**
   * To create portfolio.
   * @param portFolioName portfolio name
   * @param tickerSymbol stock
   * @param date date
   */
  public void createPortfolio(String portFolioName, String tickerSymbol, Date date);

  /**
   * To buy stocks.
   * @param portFolioName portfolio name
   * @param tickerSymbol stock
   * @param date date
   * @param shares quantity
   * @param commission commission fee for the given transaction
   */
  public void buy(String portFolioName, String tickerSymbol, Date date, int shares,
                  Double commission);

  /**
   * To get composition.
   * @param portFolioName portfolio name
   * @param date date
   * @return returns the composition of the portfolio for the given date
   */
  public String getComposition(String portFolioName, Date date);

  /**
   * To check the value for a given date.
   * @param portFolioName portfolio name
   * @param date date
   * @return value
   */
  public Double checkValue(String portFolioName, Date date);

  /**
   * To upload existing portfolio.
   * @param fileName name of the file
   * @throws IOException throws IOException
   * @throws ParseException throws ParseException for wrong date
   */

  public void uploadExisting(String fileName) throws IOException, ParseException;

  /**
   * To view all portfolios.
   * @return returns all protfolios
   */

  public String viewAllPortfolios();

  /**
   * To check valid portfolio names.
   * @param s name of the portfolio
   * @return true if the portfolio is present else false
   */
  public boolean checkValidPortfolioName(String s);

  /**
   * To save file.
   */
  public void saveFile();
}
