package view.guiscreens;

import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.Features;

/**
 * Class to implement the Barchart.
 */
public class BarChart extends AbstractScreen {
  private final JComboBox<String> portfolios;
  private final JDatePickerImpl from;
  private final JDatePickerImpl to;

  private BarChartPanel chart;

  /**
   * Constructor to initialise all the variables.
   */
  public BarChart() {
    super("Show Bar Chart", "Portfolio Performance");
    super.setSize(1000, 800);

    this.chart = new BarChartPanel(new HashMap<>());
    this.chart.setPreferredSize(new Dimension(1000, 800));

    this.portfolios = this.createComboBoxField("Portfolio");
    this.from = this.createDateField("From");
    this.to = this.createDateField("To");

    JPanel mainPanel = new JPanel();
    mainPanel.add(this.portfolios);
    mainPanel.add(this.from);
    mainPanel.add(this.to);

    Box box = new Box(BoxLayout.Y_AXIS);
    box.add(mainPanel);
    box.add(this.chart);

    this.add(box, BorderLayout.CENTER);
    renderFrame();
  }


  @Override
  public void addFeatures(Features features) {
    features.getPortfolios().forEach(e -> this.portfolios.addItem(e));
    this.submit.addActionListener(e -> validateAndRenderChart(e, features));
  }

  private void validateAndRenderChart(ActionEvent e, Features features) {
    if (isInputsValid()) {
      String portfolioName = this.getComboBoxValue(this.portfolios);
      LocalDate f = this.getLocalDate(this.from);
      LocalDate t = this.getLocalDate(this.to);
      var data = features.values(portfolioName, f, t);
      draw(data);
    }
  }

  @Override
  protected boolean isInputsValid() {
    LocalDate f = this.getLocalDate(this.from);
    LocalDate t = this.getLocalDate(this.to);
    if (this.getComboBoxValue(this.portfolios) == null) {
      this.error("Invalid Portfolio Selected. Please select a portfolio and try again");
      return false;
    } else if (f.isAfter(LocalDate.now()) || t.isAfter(LocalDate.now())) {
      this.error("The selected evaluation date is in future.\nPlease select " +
              "a new date and try again");
      return false;
    } else if (f.isAfter(t)) {
      this.error("The selected from date should be less than to date.");
      return false;
    } else {
      this.error("");
    }
    return true;
  }

  private void draw(Map<LocalDate, Double> data) {
    this.chart.removeAll();
    this.chart.setData(data);
    this.chart.revalidate();
    this.chart.repaint();
  }

  class BarChartPanel extends JPanel {
    private static final long serialVersionUID = 8242113760993687819L;
    private final int margin = 20;
    private final Color background = Color.WHITE;
    private final List<Color> barColors = Arrays.asList(Color.RED, Color.BLUE, Color.GREEN,
            Color.GRAY, Color.MAGENTA, Color.ORANGE);

    private int width;
    private int height;
    private int x;
    private int y;
    private Map<LocalDate, Double> data;

    public BarChartPanel(Map<LocalDate, Double> data) {
      this.data = data;
    }

    public void setData(Map<LocalDate, Double> data) {
      this.data = data;
    }

    @Override
    protected void paintComponent(final Graphics g) {
      super.paintComponent(g);

      this.width = this.getWidth() - 4 * margin;
      this.height = this.getHeight() - (4 * margin);
      this.x = 4 * margin;
      this.y = this.height + margin;
      draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
      g.setColor(background);
      g.fillRect(0, 0, this.getWidth(), this.getHeight());

      if (this.data == null || this.data.size() == 0) {
        return;
      }
      double max = Collections.max(this.data.values());
      int barWidth = (int) ((this.width) / this.data.size());
      int gap = (int) Math.floor(0.1 * barWidth);

      int count = 0;
      for (var element : data.entrySet()) {
        int eachbarHeight = (int) (element.getValue() * this.height / max);
        int eachBarWidth = barWidth - gap;
        int xCoord = this.x + barWidth * count;
        int yCoord = this.y - eachbarHeight;

        if (count == 0) {
          drawXAxis(g);
        }

        g.setColor(this.barColors.get(count % this.barColors.size()));
        g.fillRect(xCoord, yCoord, eachBarWidth, eachbarHeight);

        g.setFont(getFont(0));
        g.drawString(String.format("%.2f", element.getValue()), xCoord + (eachBarWidth / 4),
                yCoord - (margin / 8));

        g.setFont(getFont(20));
        g.drawString(element.getKey().toString(), xCoord, this.y + (margin / 2));

        count += 1;
      }
    }

    private void drawXAxis(Graphics2D g) {
      g.setColor(Color.BLACK);
      g.fillRect(this.x - margin, this.y - this.height, 2, this.height);

      double max = Collections.max(this.data.values());
      double eachValue = max / 10;
      double each = this.height / 10;
      for (double i = 0, j = 0; i < this.height; i += each, j += eachValue) {
        g.setFont(getFont(0));
        g.drawString(this.xAxisLabelWithSuffix(j), 10, (int) (this.y - i));
      }
    }

    private Font getFont(double angle) {
      Font font = new Font(null, Font.BOLD, 12);
      AffineTransform affineTransform = new AffineTransform();
      affineTransform.rotate(Math.toRadians(angle), 0, 0);
      return font.deriveFont(affineTransform);
    }

    private String xAxisLabelWithSuffix(double value) {
      if (value < 1000) {
        return String.format("%.1f", value);
      }

      int exponent = (int) (Math.log(value) / Math.log(1000));
      char character = "kMGTPE".charAt(exponent - 1);
      return String.format("%.1f%c", value / Math.pow(1000, exponent), character);
    }
  }
}