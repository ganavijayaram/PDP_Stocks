package view.guiscreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.Features;

/**
 * Main screen used for showing Menu Options to the user.
 */
public class MainScreen extends JFrame implements Screen {

  private final JButton submit;
  private final ButtonGroup menuGroup;
  private final List<JRadioButton> menuOptions;

  private JLabel output;

  /**
   * Initializes the main screen responsible for showing menu options to the user.
   */
  public MainScreen() {
    super();
    setTitle("Welcome to Trading Application");
    setSize(600, 600);
    menuOptions = new ArrayList<>();
    var header = new JPanel();
    var label = new JLabel("Choose the menu option you wish to proceed with.");
    header.add(label);
    var panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    this.menuGroup = new ButtonGroup();
    menuOptions.add(new JRadioButton("Create Portfolio"));
    menuOptions.add(new JRadioButton("Create a Dollar-cost Averaging Portfolio"));
    menuOptions.add(new JRadioButton("Show All Portfolios"));
    menuOptions.add(new JRadioButton("Buy Stock"));
    menuOptions.add(new JRadioButton("Sell Stock"));
    menuOptions.add(new JRadioButton("Portfolio Composition"));
    menuOptions.add(new JRadioButton("Evaluate Value"));
    menuOptions.add(new JRadioButton("Evaluate Cost Basis"));
    menuOptions.add(new JRadioButton("Save Portfolio"));
    menuOptions.add(new JRadioButton("Load Portfolio"));
    menuOptions.add(new JRadioButton("Show Bar Chart"));
    menuOptions.add(new JRadioButton("Create a One Time Fixed Investment Strategy"));
    menuOptions.add(new JRadioButton("Create a Recurring Investment Strategy"));
    menuOptions.add(new JRadioButton("Apply a Strategy to a Portfolio"));

    this.submit = new JButton("Submit");
    this.menuOptions.forEach(e -> {
      this.menuGroup.add(e);
      panel.add(e);
    });

    JPanel actions = new JPanel();
    this.output = new JLabel();
    this.output.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.submit.setEnabled(false);
    this.submit.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
    actions.add(this.output);
    actions.add(submit);


    this.add(header, BorderLayout.PAGE_START);

    this.add(panel, BorderLayout.CENTER);
    this.add(actions, BorderLayout.PAGE_END);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void display(String text) {
    this.output.setText(text);
    this.output.setForeground(Color.GREEN.darker());
    this.output.setFont(new Font(null, Font.BOLD, 15));
  }

  @Override
  public void error(String text) {
    // NOTHING
  }

  @Override
  public void bindListener(ActionListener listener) {
    menuOptions.forEach(menuOption -> {
      menuOption.addActionListener((e) -> {
        this.submit.setActionCommand(e.getActionCommand());
        this.display("You have selected menu option : " + e.getActionCommand());
        this.submit.setEnabled(true);
      });
    });
    this.submit.addActionListener(listener);
  }

  @Override
  public void addFeatures(Features features) {
    // EMPTY
  }


  @Override
  public void setVisibility(boolean visible) {
    setVisible(visible);
  }

  @Override
  public void disposeScreen() {
    this.dispose();
  }
}