package model.dataparser;

import model.portfolio.Portfolio;
import model.portfolio.PortfolioType;

/**
 * Factory used to return the concrete implementation of parser interface for portfolio.
 * Returns the Parser implementation that needs to be used to parse string into portfolio
 * of respective type. The type of the parser that needs to be used determined the parser
 * object that is to be returned by the factory.
 */
public class DataParserFactory {

  /**
   * The main method responsible for returning the parser object of type SimulatedPortfolioParser
   * which is used to parse string toSimulated Portfolio and TransactionalPortfolioParser which is
   * used to parse string to transactional portfolio. The argument decides which type of parser
   * needs to be returned by the factory.
   *
   * @param type the type of portfolio that needs to be parsed.
   * @return the parser object.
   */
  public static DataParser<Portfolio> getParser(PortfolioType type) {
    DataParser<Portfolio> parser = null;
    if (type == null) {
      return null;
    }
    if (type == PortfolioType.SIMULATED) {
      parser = new SimulatedPortfolioParser();
    } else if (type == PortfolioType.TRANSACTIONAL) {
      parser = new TransactionalPortfolioParser();
    }
    return parser;
  }
}
