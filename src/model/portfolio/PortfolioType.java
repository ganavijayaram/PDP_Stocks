package model.portfolio;

/**
 * The types of portfolio that the application support.
 * Simulated portfolio are used for planning/analyzing the combination of stocks that
 * work well for the user. This type of stock does not support trading on the stocks i.e.
 * once created the user does not have the ability to sell/buy stock from and to the portfolio.
 * Transactional Portfolio support trading operations such as buying and selling of the stocks
 * from the portfolio.
 */
public enum PortfolioType {
  SIMULATED,
  TRANSACTIONAL
}
