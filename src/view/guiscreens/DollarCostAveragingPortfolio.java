package view.guiscreens;

import java.awt.BorderLayout;
import java.util.AbstractMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Represents a screen to create dollar cost avg portfolio.
 */
public class DollarCostAveragingPortfolio extends RecurringInvestmentStrategy {

  private JTextField portfolioName;

  /**
   * Initializes a screen to create dollar cost avg portfolio.
   */
  public DollarCostAveragingPortfolio() {
    super("Trading Application - Create Portfolio with Dollar Cost Avg Strategy");
    this.portfolioName = this.createTextField("Enter the portfolio name");
    this.mainPanel = initMainPanel();
    this.add(mainPanel, BorderLayout.CENTER);
    renderFrame();
  }

  protected java.util.List<Map.Entry<String, JComponent>> getDefaultComponents() {
    var self = this;
    var list = super.getDefaultComponents();
    list.add(0, new AbstractMap.SimpleEntry<>("Enter the name of the portfolio : ",
            this.portfolioName));
    return list;
  }

  @Override
  protected String tradeName() {
    return this.portfolioName.getText();
  }

}
