package model.dataparser;

import java.time.LocalDate;
import java.util.Set;
import model.portfolio.Portfolio;
import model.portfolio.TransactionalPortfolio;
import model.stock.Stock;
import model.trade.TransactionalStockTrade;
import model.trade.Trade;

/**
 * Implementation of Parser interface for type of Transactional Portfolio which is responsible
 * for parsing the string data to Portfolio of type TransactionalPortfolio.
 */
public class TransactionalPortfolioParser extends AbstractPortfolioParser {

  @Override
  public Portfolio parse(String data) {

    // if portfolio is empty then skip this one
    if (data == null ||  data.isEmpty()) {
      return null;
    }
    String[] split = data.split("SALE :");
    // reads the metadata of the portfolio i.e. name, type of portfolio.
    String[] metadata = split[0].split("PURCHASES : ")[0].split("\n");
    // reads the purchases involved in the portfolio.
    String[] purchases = split[0].split("PURCHASES : ")[1].split("\n");
    // reads the sale involved in the portfolio.
    String[] sale = split[1].split("\n");
    String portfolioName = metadata[1].replace("Portfolio Name : ", "");
    // parse the purchased trades.
    Set<Trade<Stock>> purchased = this.getTrade(purchases);
    // parse the sale trades.
    Set<Trade<Stock>> sold = this.getTrade(sale);
    // return new transactional portfolio.
    return new TransactionalPortfolio(portfolioName, purchased, sold);
  }

  @Override
  protected Trade<Stock> createTrade(String stock, Double shares, LocalDate date,
                                     Double commission) {
    // returns a stock transaction trade.
    return new TransactionalStockTrade(stock, shares, date, commission);
  }
}
