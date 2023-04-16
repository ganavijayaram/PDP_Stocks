package model.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.dataparser.DataParserFactory;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioType;

/**
 * Utility for parsing Data for loading the portfolio
 * in the application.
 */
public class PortfolioDataParser {

  // separator for each portfolio in FILE
  private static final String separator = "------ END ------\n";

  // regex to check type of each portfolio
  private static final String regex = "(?<=TYPE : )(.*)(?=\n)";

  /**
   * Parses the string data to List of Portfolio.
   *
   * @param data The string data read from source.
   * @return List of Portfolio.
   */
  public static List<Portfolio> parse(String data) {
    // list of portfolio that will be parsed
    List<Portfolio> portfolioList = new ArrayList<>();
    // All the portfolios
    String[] portfolios = data.split(separator);
    for (String portfolio : portfolios) {
      Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(portfolio);
      if (matcher.find()) {
        PortfolioType type = PortfolioType.valueOf(matcher.group(0));
        Portfolio parsedPortfolio = DataParserFactory.getParser(type).parse(portfolio);
        if (parsedPortfolio != null) {
          portfolioList.add(parsedPortfolio);
        }
      }
    }
    return portfolioList;
  }
}
