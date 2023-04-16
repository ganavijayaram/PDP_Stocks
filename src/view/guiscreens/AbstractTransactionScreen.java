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
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controller.Features;

/**
 * Creates a screen for portfolio transactions.
 */
abstract class AbstractTransactionScreen extends AbstractScreen {

  private final JComboBox<String> portfolioName;
  private final JDatePickerImpl date;
  private final JTextField stock;
  private final JSpinner shares;
  private final JSpinner commission;

  /**
   * Initializes a screen for portfolio transactions.
   */
  public AbstractTransactionScreen(String caption) {
    super(caption, "");

    this.portfolioName = this.createComboBoxField("Enter Portfolio Name");
    this.stock = this.createTextField("Enter the name of the stock for " +
            "the transaction");
    this.shares = this.createSpinnerField("Enter the number of shares involved " +
            "in transaction");
    this.date = this.createDateField("Enter the date of sale");
    this.commission = this.createSpinnerField("Enter the commission fee for" +
            " the transaction");

    var self = this;
    List<Map.Entry<String, JComponent>> components = new ArrayList<>();
    components.add(new AbstractMap.SimpleEntry<>("Enter the portfolio name : ",
            self.portfolioName));
    components.add(new AbstractMap.SimpleEntry<>("Enter the name of the stock : ",
            self.stock));
    components.add(new AbstractMap.SimpleEntry<>("Enter the number of shares of the stock : "
            , self.shares));
    components.add(new AbstractMap.SimpleEntry<>("Enter the date of transaction of the stock : "
            , self.date));
    components.add(new AbstractMap.SimpleEntry<>("Enter the commission fee for the transaction : "
            , self.commission));
    var mainPanel = this.createMainPanel(components);
    this.add(mainPanel, BorderLayout.CENTER);
    renderFrame();
  }

  @Override
  public void addFeatures(Features features) {
    /*this.stock.getDocument().addDocumentListener(this.onTextFieldChange());
    this.commission.addChangeListener(this.onSpinnerChange());
    this.shares.addChangeListener(this.onSpinnerChange());
    this.portfolioName.addItemListener(this.onDropDownChange());*/
    features.getPortfolios().forEach(e -> this.portfolioName.addItem(e));

    this.submit.addActionListener(e -> {
      if (this.isInputsValid()) {
        LocalDate date = this.getLocalDate(this.date);
        String name = this.getComboBoxValue(this.portfolioName);
        this.performTrade(features, name, this.stock.getText(), toDouble(this.shares), date,
                toDouble(this.commission));
      }
    });

  }


  public abstract void performTrade(Features features, String portfolioName, String stock,
                                    Double shares, LocalDate date, Double commission);


  @Override
  protected boolean isInputsValid() {
    if (this.getComboBoxValue(this.portfolioName) == null) {
      this.error("Invalid Portfolio Selected. Please select a portfolio and try again");
      return false;
    } else if (this.stock == null || this.stock.getText().trim().isBlank() ||
            this.stock.getText().trim().isEmpty()) {
      this.error("The stock ticker entered is invalid. Please enter and try again");
      return false;
    } else if (toInt(this.shares) <= 0) {
      this.error("Invalid number of shares. Please enter and try again");
      return false;
    } else if (this.getLocalDate(this.date).isAfter(LocalDate.now())) {
      this.error("The selected evaluation date is in future.\nPlease select " +
              "a new date and try again");
      return false;
    } else if (toInt(this.commission) < 0) {
      this.error("Invalid commission fee. Please enter and try again");
      return false;
    } else {
      this.error("");
    }
    return true;
  }


}
