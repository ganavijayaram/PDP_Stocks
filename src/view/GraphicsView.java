package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;

import controller.Features;
import view.guiscreens.AllPortfolio;
import view.guiscreens.ApplyStrategyToPortfolio;
import view.guiscreens.BarChart;
import view.guiscreens.BuyStock;
import view.guiscreens.CreatePortfolio;
import view.guiscreens.DollarCostAveragingPortfolio;
import view.guiscreens.LoadPortfolio;
import view.guiscreens.MainScreen;
import view.guiscreens.OneTimeInvestmentStrategy;
import view.guiscreens.PortfolioComposition;
import view.guiscreens.PortfolioCostBasis;
import view.guiscreens.PortfolioValue;
import view.guiscreens.RecurringInvestmentStrategy;
import view.guiscreens.SavePortfolio;
import view.guiscreens.Screen;
import view.guiscreens.SellStock;

/**
 * A class to implement the GUI.
 */
public class GraphicsView implements View, ActionListener {

  private final Map<String, Runnable> actionMap;
  private Screen currentScreen;
  private Screen mainScreen;

  private Features features;

  /**
   * Constructor to initialise the variables.
   */
  public GraphicsView() {
    setDefaultUI();
    this.mainScreen = new MainScreen();
    this.mainScreen.bindListener(this);
    this.actionMap = initializeMap();
  }

  @Override
  public void addFeatures(Features features) {
    this.features = features;
  }

  @Override
  public String input() {
    return null;
  }

  @Override
  public void display(String text) {
    this.currentScreen.display(text);
  }

  @Override
  public void error(String message) {
    this.currentScreen.error(message);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    var actionCommand = e.getActionCommand();
    this.actionMap.get(actionCommand).run();
  }

  private Map<String, Runnable> initializeMap() {
    Map<String, Runnable> commands  = new HashMap<>();
    commands.put("Create Portfolio", () -> {
      createScreenAndSetDefault(new CreatePortfolio(), features);
    });
    commands.put("Show All Portfolios", () -> {
      createScreenAndSetDefault(new AllPortfolio(), features);
    });
    commands.put("Home", () -> {
      this.mainScreen.setVisibility(true);
      this.currentScreen.disposeScreen();
    });
    commands.put("Buy Stock", () -> {
      createScreenAndSetDefault(new BuyStock(), features);
    });
    commands.put("Sell Stock", () -> {
      createScreenAndSetDefault(new SellStock(), features);
    });
    commands.put("Portfolio Composition", () -> {
      createScreenAndSetDefault(new PortfolioComposition(), features);
    });
    commands.put("Evaluate Value", () -> {
      createScreenAndSetDefault(new PortfolioValue(), features);
    });
    commands.put("Evaluate Cost Basis", () -> {
      createScreenAndSetDefault(new PortfolioCostBasis(), features);
    });
    commands.put("Load Portfolio", () -> {
      createScreenAndSetDefault(new LoadPortfolio(), features);

    });
    commands.put("Save Portfolio", () -> {
      createScreenAndSetDefault(new SavePortfolio(), features);

    });
    commands.put("Show Bar Chart", () -> {
      createScreenAndSetDefault(new BarChart(), features);
    });
    commands.put("Create a One Time Fixed Investment Strategy", () -> {
      createScreenAndSetDefault(new OneTimeInvestmentStrategy(), features);
    });
    commands.put("Create a Recurring Investment Strategy", () -> {
      createScreenAndSetDefault(new RecurringInvestmentStrategy(), features);
    });
    commands.put("Apply a Strategy to a Portfolio", () -> {
      createScreenAndSetDefault(new ApplyStrategyToPortfolio(), features);
    });
    commands.put("Create a Dollar-cost Averaging Portfolio", () -> {
      createScreenAndSetDefault(new DollarCostAveragingPortfolio(), features);
    });
    //
    return commands;
  }

  private void createScreenAndSetDefault(Screen screen, Features features) {
    this.mainScreen.setVisibility(false);
    this.currentScreen = screen;
    this.currentScreen.bindListener(this);
    this.currentScreen.addFeatures(features);
  }

  private void setDefaultUI() {
    Font f = new Font(null, Font.PLAIN, 15);
    UIManager.put("Button.font", f.deriveFont(1));
    UIManager.put("RadioButton.font", f.deriveFont(14.0F));
    UIManager.put("Label.font", f);
    UIManager.put("TextField.font", f);
  }
}
