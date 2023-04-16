package view.guiscreens;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Features;

/**
 * Screen to create a portfolio.
 */
public class CreatePortfolio extends AbstractScreen {

  private final JTextField portfolioName;

  /**
   * Initializes a screen to create a portfolio.
   */
  public CreatePortfolio() {
    super("Create Portfolio",
            "Please enter the name of the portfolio you wish to create?");

    this.portfolioName = this.createTextField("Enter Portfolio Name");

    JPanel portfolioDetails = new JPanel();
    portfolioDetails.add(this.portfolioName);

    List<Map.Entry<String, JComponent>> components = new ArrayList<>();
    var mainPanel = this.createMainPanel(components);
    mainPanel.add(portfolioDetails);
    this.add(mainPanel, BorderLayout.CENTER);
    renderFrame();
  }


  @Override
  public void addFeatures(Features features) {
    this.submit.addActionListener(e -> {
      if (isInputsValid()) {
        features.createFlexiblePortfolio(this.portfolioName.getText());
      } else {
        this.error("The portfolio name is invalid. Please input again and press submit.");
      }
    });
  }

  @Override
  protected boolean isInputsValid() {
    if (this.portfolioName == null || this.portfolioName.getText().trim().isBlank() ||
            this.portfolioName.getText().trim().isEmpty()) {
      this.error("Invalid Portfolio Name");
      return false;
    } else {
      this.error("");
    }
    return true;
  }


}
