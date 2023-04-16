package view.guiscreens;

import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import controller.Features;

/**
 * Creates a screen for portfolio evaluations.
 */
abstract class AbstractEvaluationScreen extends AbstractScreen {
  private final JComboBox<String> portfolioName;
  private final JDatePickerImpl date;

  /**
   * Initializes a screen for portfolio evaluations.
   */
  public AbstractEvaluationScreen(String caption) {
    super(caption, "");

    this.portfolioName = this.createComboBoxField("Enter Portfolio Name");
    this.date = this.createDateField("Enter the date of evaluation");

    var self = this;
    List<Map.Entry<String, JComponent>> components = new ArrayList<>();
    components.add(new AbstractMap.SimpleEntry<>("Enter the portfolio name : ",
            self.portfolioName));
    components.add(new AbstractMap.SimpleEntry<>("Enter the date of evaluation of the portfolio : "
            , self.date));
    var mainPanel = this.createMainPanel(components);
    this.add(mainPanel, BorderLayout.CENTER);
    renderFrame();

  }

  @Override
  public void addFeatures(Features features) {
    features.getPortfolios().forEach(e -> this.portfolioName.addItem(e));
    this.submit.addActionListener(e -> {
      if (isInputsValid()) {
        String name = this.getComboBoxValue(this.portfolioName);
        LocalDate date = this.getLocalDate(this.date);
        this.evaluateTrade(features, name, date);
      }
    });
  }

  public abstract void evaluateTrade(Features features, String portfolioName, LocalDate date);

  @Override
  protected boolean isInputsValid() {
    boolean valid = true;
    if (this.getComboBoxValue(this.portfolioName) == null) {
      valid = false;
      this.error("Invalid Portfolio Selected. Please select a portfolio and try again");
    } else if (this.getLocalDate(this.date).isAfter(LocalDate.now())) {
      valid = false;
      this.error("The selected evaluation date is in future.\nPlease select " +
              "a new date and try again");
    } else {
      this.error("");
    }
    return valid;
  }
}
