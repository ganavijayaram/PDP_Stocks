package view.guiscreens;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import controller.Features;

/**
 * Screen get the weights of the stocks in the strategy.
 */
public class StockWeightScreen extends AbstractScreen {

  private final String portfolioName;
  private final String name;
  private final double principal;
  private final int stocks;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final int frequency;
  private final double commission;

  private final List<JTextField> stockTickers;
  private final List<JTextField> percentage;

  /**
   * Initializes the screen to input stock weights for each stock in the strategy.
   *
   * @param portfolioName name of the portfolio to be created.
   * @param name       name of the strategy.
   * @param principal  the principal amount to be invested.
   * @param stocks     the number of stocks containing in the strategy.
   * @param startDate  the start date of the strategy.
   * @param endDate    the end date of the strategy.
   * @param frequency  the frequency of recurring strategy.
   * @param commission the commission fee for the strategy.
   */
  public StockWeightScreen(String portfolioName, String name, Double principal, int stocks,
                           LocalDate startDate, LocalDate endDate, int frequency,
                           double commission) {
    super("Trading Application - Enter Stock Weightage", "");
    this.portfolioName = portfolioName;
    this.name = name;
    this.principal = principal;
    this.stocks = stocks;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frequency = frequency;
    this.commission = commission;
    this.stockTickers = new ArrayList<>();
    this.percentage = new ArrayList<>();

    initComponents();
  }

  private void initComponents() {
    List<Map.Entry<String, JComponent>> components = new ArrayList<>();
    for (int i = 0; i < this.stocks; i++) {
      var stockField = this.createTextField("");
      this.stockTickers.add(stockField);

      var percentageField = this.createTextField("");
      this.percentage.add(percentageField);

      components.add(new AbstractMap.SimpleEntry<String, JComponent>("Enter the stock symbol : "
              , stockField));
      components.add(new AbstractMap.SimpleEntry<String, JComponent>("Enter the percentage : "
              , percentageField));
    }
    var mainPanel = this.createMainPanel(components);
    var mainScrollPane = new JScrollPane(mainPanel);
    mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    add(mainScrollPane);
    renderFrame();
  }

  @Override
  public void addFeatures(Features features) {
    this.submit.addActionListener(f -> {
      var isStrategyCreated = false;
      if (this.isInputsValid()) {
        isStrategyCreated = features.createStrategy(this.name,
                this.principal,
                this.toStockData(),
                this.startDate,
                this.endDate,
                this.frequency,
                this.commission
        );
        if (this.portfolioName != null && isStrategyCreated) {
          if (features.createFlexiblePortfolio(this.portfolioName)) {
            features.applyStrategy(this.portfolioName, this.name);
          }
        }
      }
    });
  }


  private Map<String, Double> toStockData() {
    var map = new HashMap<String, Double>();
    for (int i = 0; i < stocks; i++) {
      map.put(stockTickers.get(i).getText(),
              map.getOrDefault(stockTickers.get(i).getText(), 0.0) +
                      Double.parseDouble(percentage.get(i).getText()));
    }
    return map;
  }

  @Override
  protected boolean isInputsValid() {
    Double totalPercentage = 0.0;
    for (int i = 0; i < stockTickers.size(); i++) {
      var ticker = stockTickers.get(i);
      if (ticker == null || ticker.getText().isEmpty() || ticker.getText().isBlank()) {
        this.error("Invalid Ticker Symbol. Please enter again and try.");
        return false;
      }
      try {
        var percentage = this.percentage.get(i).getText();
        Double percent = toDouble(percentage);
        if (percent <= 0) {
          this.error("Invalid Ticker Symbol. Please enter again and try.");
          return false;
        }
        if (totalPercentage + percent > 100) {
          this.error("Invalid percentages. Please enter again and try");
          return false;
        }
        totalPercentage += toDouble(this.percentage.get(i).getText());
      } catch (NumberFormatException e) {
        this.error("Invalid Percentage Value. Please enter again and try.");
      }

    }
    if (totalPercentage != 100) {
      this.error("Invalid percentages. Please enter again and try");
      return false;
    }
    this.error("");
    return true;
  }
}
