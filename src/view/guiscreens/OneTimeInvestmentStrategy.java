package view.guiscreens;

import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controller.Features;

/**
 * Screen to create a fixed one time investment strategy.
 */
public class OneTimeInvestmentStrategy extends AbstractScreen {

  protected final JTextField name;
  protected final JTextField principal;
  protected final JSpinner stocks;
  protected final JDatePickerImpl date;
  protected final JSpinner commission;

  protected JPanel mainPanel;
  protected Screen frame;

  /**
   * Initializes a screen to create fixed one time investment strategy.
   */
  public OneTimeInvestmentStrategy() {
    this("Trading Application - Create One Time Strategy");
    this.mainPanel = initMainPanel();
    this.add(mainPanel, BorderLayout.CENTER);
    renderFrame();
  }

  /**
   * Initializes a screen to create fixed one time investment strategy with caption.
   *
   * @param caption The caption for the screen.
   */
  public OneTimeInvestmentStrategy(String caption) {
    super(caption, "");
    this.name = this.createTextField("Enter the strategy name");
    this.principal = this.createTextField("Enter the principal amount");
    this.stocks = this.createSpinnerField("Enter the number of stocks");
    this.commission = this.createSpinnerField("Enter the commission fee for the strategy");
    this.date = this.createDateField("Enter the date for one time investment");
    this.frame = null;
  }

  protected JPanel initMainPanel() {
    var components = getDefaultComponents();
    var mainPanel = this.createMainPanel(components);
    getAdditionalComponents().stream()
            .filter(e -> e != null)
            .forEach(e -> mainPanel.add(e));
    return mainPanel;
  }

  protected java.util.List<Map.Entry<String, JComponent>> getDefaultComponents() {
    var self = this;
    java.util.List<Map.Entry<String, JComponent>> components = new ArrayList<>();
    components.add(new AbstractMap.SimpleEntry<>("Enter the strategy name : ", self.name));
    components.add(new AbstractMap.SimpleEntry<>("Enter the principal amount : ", self.principal));
    components.add(new AbstractMap.SimpleEntry<>("Enter the number of stocks : ", self.stocks));
    components.add(new AbstractMap.SimpleEntry<>("Enter the commission fee for the strategy : "
            , self.commission));

    return components;
  }

  protected List<JPanel> getAdditionalComponents() {
    JPanel purchaseData = new JPanel();
    purchaseData.add(this.createLabelField("Enter the date for one time investment : "));
    purchaseData.add(this.date);
    return Arrays.asList(purchaseData);
  }

  protected String tradeName() {
    return null;
  }

  @Override
  public void display(String text) {
    if (this.frame != null) {
      this.frame.display(text);
    } else {
      super.display(text);
    }
  }

  @Override
  public void error(String text) {
    if (this.frame != null) {
      this.frame.error(text);
    } else {
      super.error(text);
    }
  }

  @Override
  public void addFeatures(Features features) {
    this.submit.addActionListener(f -> {
      if (this.isInputsValid()) {
        this.setVisibility(false);
        this.frame = new StockWeightScreen(this.tradeName(),
                this.name.getText(),
                toDouble(this.principal.getText()),
                toInt(this.stocks),
                this.getLocalDate(this.date),
                this.getLocalDate(this.date),
                1,
                toDouble(this.commission));
        var listeners = this.back.getActionListeners();
        this.frame.bindListener(listeners[0]);
        this.frame.addFeatures(features);
      }
    });
  }

  @Override
  public void disposeScreen() {
    if (frame != null) {
      this.frame.disposeScreen();
    }
    super.disposeScreen();
  }


  protected boolean isInputsValid() {
    if (!validateItems()) {
      return false;
    }
    if (this.getLocalDate(this.date).isAfter(LocalDate.now())) {
      this.error("The selected date is in future.\nPlease select " +
              "a new date and try again");
      return false;
    } else {
      this.error("");
    }
    return true;
  }

  protected boolean validateItems() {
    if (this.tradeName() != null) {
      if (this.tradeName().trim().isEmpty() || this.tradeName().trim().isBlank()) {
        this.error("Invalid Portfolio Name");
        return false;

      }
    }
    if (this.name == null || this.name.getText().trim().isBlank() ||
            this.name.getText().trim().isEmpty()) {
      this.error("Invalid Strategy Name. Please enter again and try");
      return false;
    }
    if (!this.isPrincipalAmountValid()) {
      this.error("Invalid Principal Amount. Please enter again and try");
      return false;
    }
    if (toInt(this.stocks) <= 0) {
      this.error("Invalid number of shares. Please enter and try again");
      return false;
    }
    if (toInt(this.commission) < 0) {
      this.error("Invalid Commission Fee. Please enter and try again");
      return false;
    }
    return true;
  }

  private boolean isPrincipalAmountValid() {
    try {
      var principal = Double.parseDouble(this.principal.getText());
      return principal > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
