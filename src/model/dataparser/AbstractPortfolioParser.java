package model.dataparser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import model.portfolio.Portfolio;
import model.stock.Stock;
import model.trade.Trade;


/**
 * Abstract class used to have the common code abstracted for different types
 * of portfolio parser. This class is used to create list of portfolios
 * of different type using the concrete implementation of different types
 * of portfolio parser.
 */
abstract class AbstractPortfolioParser implements DataParser<Portfolio> {

  /**
   * Creates the trade object using the string data given to the method.
   *
   * @param data String value of the trade object.
   * @return the Set of trades.
   */
  protected Set<Trade<Stock>> getTrade(String[] data) {
    Set<Trade<Stock>> trades = new HashSet<>();
    for (int i = 0; i < data.length; i++) {
      if (data[i].isEmpty() || data[i].isBlank()) {
        continue;
      }
      String[] lineParsed = data[i].replace("Stock Symbol : ", "")
              .replace("Quantity : ", "")
              .replace("Date of Purchase : ", "")
              .replace("Commission Fee : ", "")
              .split(",");
      String stock = lineParsed[0].trim();
      Double shares = Double.parseDouble(lineParsed[1]);
      LocalDate date = lineParsed.length == 4 ? LocalDate.parse(lineParsed[2]) : null;
      Double commission = lineParsed.length == 4 ? Double.parseDouble(lineParsed[3]) : null;
      Trade<Stock> share = this.createTrade(stock, shares, date, commission);

      // maintains a list of trade
      trades.add(share);
    }
    return trades;
  }

  /**
   * Creates a trade object with stock, shares, date and commission as arguments.
   *
   * @param stock      stock participating in the trade.
   * @param shares     the number of shares of a stock.
   * @param date       the date of the trade.
   * @param commission the commission fee involved in the trade,
   * @return the trade object.
   */
  protected abstract Trade<Stock> createTrade(String stock, Double shares, LocalDate date,
                                              Double commission);
}
