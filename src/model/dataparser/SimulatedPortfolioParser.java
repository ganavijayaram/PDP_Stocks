package model.dataparser;

import java.time.LocalDate;
import java.util.Set;
import model.portfolio.SimulatedPortfolio;
import model.portfolio.Portfolio;
import model.stock.Stock;
import model.trade.SimulatedStockTrade;
import model.trade.Trade;

/**
 * Implementation of Parser interface for type of Simulated Portfolio which is responsible
 * for parsing the string data to Portfolio of type SimulatedPortfolio.
 */
public class SimulatedPortfolioParser extends AbstractPortfolioParser {

  @Override
  public Portfolio parse(String data) {

    // if portfolio is empty then skip this one
    if (data == null ||  data.isEmpty()) {
      return null;
    }
    // read the metadata of the portfolio : portfolio name, type
    String[] metadata = data.split("STOCKS :")[0].split("\n");
    // read the trades/stocks the portfolio comprises.
    String[] stocks = data.split("STOCKS :")[1].split("\n");
    String portfolioName = metadata[1].replace("Portfolio Name : ", "");
    // parse the trade/stocks data.
    Set<Trade<Stock>> shares = this.getTrade(stocks);
    // return new master portfolio.
    return new SimulatedPortfolio(portfolioName, shares);
  }

  @Override
  protected Trade<Stock> createTrade(String stock, Double shares, LocalDate date,
                                     Double commission) {
    // return the cumulative stock trade.
    return new SimulatedStockTrade(stock, shares);
  }
}
