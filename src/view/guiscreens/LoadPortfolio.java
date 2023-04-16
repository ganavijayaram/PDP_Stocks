package view.guiscreens;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;


/**
 * A class for loading portfolio in the GUI.
 */
public class LoadPortfolio extends AbstractScreen {

  private final JFileChooser file;

  /**
   * Constructor to initialise the variables.
   */
  public LoadPortfolio() {
    super("Load Portfolio", "");
    file = new JFileChooser();
    JPanel mainPanel = new JPanel();
    mainPanel.add(file);
    this.submit.setEnabled(false);
    this.add(mainPanel, BorderLayout.CENTER);

    renderFrame();
  }

  @Override
  public void addFeatures(Features features) {
    var frame = this;
    this.file.addPropertyChangeListener("JFileChooserDialogIsClosingProperty",
            dialogListener(features, file));
    file.setDialogTitle("Specify a file to open");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
    file.setAcceptAllFileFilterUsed(false);
    file.addChoosableFileFilter(filter);
    file.showOpenDialog(this);


  }

  private PropertyChangeListener dialogListener(Features features, JFileChooser file) {
    var frame = this;
    return new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        File selectedFile = file.getSelectedFile();
        if (selectedFile != null) {
          frame.setVisibility(true);
          features.load(selectedFile.getAbsolutePath());
        }
      }
    };
  }

  @Override
  protected boolean isInputsValid() {
    this.error("");
    return true;
  }
}
