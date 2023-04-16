package view.guiscreens;

import java.awt.BorderLayout;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import controller.Features;

/**
 * Screen to apply an investment strategy to the portfolio.
 */
public class ApplyStrategyToPortfolio extends AbstractScreen {
  private final JComboBox<String> portfolioName;
  private final JComboBox<String> strategyName;

  /**
   * Initializes a screen to apply an investment strategy to the portfolio.
   */
  public ApplyStrategyToPortfolio() {
    super("Trading Window - Apply Strategy to Portfolio", "");

    this.portfolioName = this.createComboBoxField("Enter the portfolio name : ");
    this.strategyName = this.createComboBoxField("Enter the strategy name");

    var self = this;
    List<Map.Entry<String, JComponent>> components = new ArrayList<>();
    components.add(new AbstractMap.SimpleEntry<>("Enter the portfolio name : ",
            self.portfolioName));
    components.add(new AbstractMap.SimpleEntry<>("Enter the strategy name : "
            , self.strategyName));
    var mainPanel = this.createMainPanel(components);
    this.add(mainPanel, BorderLayout.CENTER);
    renderFrame();
  }

  @Override
  public void addFeatures(Features features) {
    features.getPortfolios().forEach(e -> this.portfolioName.addItem(e));
    features.getAllStrategy().forEach(e -> this.strategyName.addItem(e));
    this.submit.addActionListener(f -> {
      if (isInputsValid()) {
        features.applyStrategy(
                this.getComboBoxValue(this.portfolioName),
                this.getComboBoxValue(this.strategyName));
      }
    });
  }


  @Override
  protected boolean isInputsValid() {
    if (this.getComboBoxValue(this.portfolioName) == null) {
      this.error("Invalid Portfolio Selected. Please select a portfolio and try again");
      return false;
    } else if (this.getComboBoxValue(this.strategyName) == null) {
      this.error("Invalid Strategy Selected. Please select a strategy and try again");
      return false;
    } else {
      this.error("");
    }
    return true;
  }
}
