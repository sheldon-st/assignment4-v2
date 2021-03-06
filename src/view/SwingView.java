package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class opens the main window, that has different elements illustrated in
 * it. It also doubles up as all the listeners for simplicity. Such a design is
 * not recommended in general.
 */

public class SwingView extends JFrame implements ActionListener, ItemListener, ListSelectionListener, IView {
  private JPasswordField pfield;
  private JButton pButton;
  private JLabel pDisplay;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel checkboxDisplay;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private JLabel colorChooserDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JLabel inputDisplay;
  private JLabel optionDisplay;

  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;


  String sourceImage = "Source Image:";
  JLabel sourceLabel = new JLabel();
  JScrollPane s1ScrollPlane = new JScrollPane();

  String[] histogramTypes = {"Red", "Green", "Blue", "Intensity"};
  JLabel[] histogramLabels = new JLabel[histogramTypes.length];


  JScrollPane[] h1ScrollPane = new JScrollPane[histogramTypes.length];

  String destImage = "Dest Image:";
  JLabel destLabel = new JLabel();
  JScrollPane d1ScrollPlane = new JScrollPane();
  JLabel[] histogram2Labels = new JLabel[histogramTypes.length];
  JScrollPane[] h2ScrollPane = new JScrollPane[histogramTypes.length];



  public SwingView() {
    super();
    setTitle("Image Manager");
    setSize(1200, 800);

    mainPanel = new JPanel();

    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);



    //JPanel histogramPanel =
    //mainPanel.add();
    //dialog boxes
    this.getHistogramPanel();
  }


  /**
   * Returns a JPanel component to visualize the histogram for the red, green, blue and intensity
   * values.
   */
  public void getHistogramPanel() {
    System.out.println("attempting to get hPanel: ");
    //show an image with a scrollbar


    JPanel previewPanel = new JPanel();
    previewPanel.setLayout(new GridLayout(1, 2, 10, 10));
    previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));


    JPanel sourcePanel = new JPanel();

    //a border around the panel with a caption
    sourcePanel.setBorder(BorderFactory.createTitledBorder("Source [Original]"));
    sourcePanel.setLayout(new GridLayout(1, 2, 10, 10));

    sourceLabel = new JLabel();
    s1ScrollPlane = new JScrollPane(sourceLabel);
    s1ScrollPlane.setPreferredSize(new Dimension(256, 512));

    JPanel h1Panel = new JPanel();
    //a border around the panel with a caption
    h1Panel.setBorder(BorderFactory.createTitledBorder("Histogram [Original]"));
    h1Panel.setLayout(new GridLayout(4, 1, 10, 10));

    for (int i = 0; i < histogramTypes.length; i++) {
      histogramLabels[i] = new JLabel();
      h1ScrollPane[i] = new JScrollPane(histogramLabels[i]);
      h1ScrollPane[i].setPreferredSize(new Dimension(100, 100));
      h1Panel.add(h1ScrollPane[i]);
    }


    sourcePanel.add(h1Panel);
    sourcePanel.add(s1ScrollPlane);

    JPanel destPanel = new JPanel();

    //a border around the panel with a caption
    destPanel.setBorder(BorderFactory.createTitledBorder("Dest [Edited]"));
    destPanel.setLayout(new GridLayout(1, 2, 10, 10));

    destLabel = new JLabel();
    d1ScrollPlane = new JScrollPane(destLabel);
    d1ScrollPlane.setPreferredSize(new Dimension(256, 512));

    JPanel h2Panel = new JPanel();
    //a border around the panel with a caption
    h2Panel.setBorder(BorderFactory.createTitledBorder("Histogram [Edited]"));
    h2Panel.setLayout(new GridLayout(4, 1, 10, 10));

    for (int i = 0; i < histogramTypes.length; i++) {
      histogram2Labels[i] = new JLabel();
      h2ScrollPane[i] = new JScrollPane(histogram2Labels[i]);
      h2ScrollPane[i].setPreferredSize(new Dimension(100, 100));
      h2Panel.add(h2ScrollPane[i]);
    }
    h2Panel.setMaximumSize(new Dimension(200, 600));


    destPanel.add(h2Panel);
    destPanel.add(d1ScrollPlane);

    previewPanel.add(sourcePanel);
    previewPanel.add(destPanel);

    mainPanel.add(previewPanel);
   // mainPanel.add(imagePanel2);

  }


  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
      case "password button":
        pDisplay.setText(new String(pfield.getPassword()));
        pfield.setText("");
        break;
      case "RB1":
        radioDisplay.setText("Radio button 1 was selected");
        break;

      case "RB2":
        radioDisplay.setText("Radio button 2 was selected");
        break;

      case "RB3":
        radioDisplay.setText("Radio button 3 was selected");
        break;

      case "RB4":
        radioDisplay.setText("Radio button 4 was selected");
        break;

      case "Size options":
        if (arg0.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) arg0.getSource();
          comboboxDisplay.setText("You selected: " + (String) box.getSelectedItem());


        }
        break;
      case "Color chooser":
        Color col = JColorChooser.showDialog(SwingView.this, "Choose a color", colorChooserDisplay.getBackground());
        colorChooserDisplay.setBackground(col);
        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(SwingView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Message":
        JOptionPane.showMessageDialog(SwingView.this, "This is a demo message", "Message", JOptionPane.PLAIN_MESSAGE);
        break;
      case "Input":
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter your username"));
        break;
      case "Option": {
        String[] options = {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "seis", "siete", "ocho", "nueve", "dies"};
        int retvalue = JOptionPane.showOptionDialog(SwingView.this, "Please choose number", "Options", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);
        optionDisplay.setText(options[retvalue]);
      }
      break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent arg0) {
    // TODO Auto-generated method stub
    String who = ((JCheckBox) arg0.getItemSelectable()).getActionCommand();

    switch (who) {
      case "CB1":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 1 was selected");
        } else {
          checkboxDisplay.setText("Check box 1 was deselected");
        }
        break;
      case "CB2":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 2 was selected");
        } else {
          checkboxDisplay.setText("Check box 2 was deselected");
        }
        break;
      case "CB3":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 3 was selected");
        } else {
          checkboxDisplay.setText("Check box 3 was deselected");
        }
        break;
      case "CB4":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 4 was selected");
        } else {
          checkboxDisplay.setText("Check box 4 was deselected");
        }
        break;

      case "CB5":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 5 was selected");
        } else {
          checkboxDisplay.setText("Check box 5 was deselected");
        }
        break;

    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // We don't know which list called this callback, because we're using it
    // for two lists.  In practice, you should use separate listeners
    JOptionPane.showMessageDialog(SwingView.this,
            "The source object is " + e.getSource(), "Source", JOptionPane.PLAIN_MESSAGE);
    // Regardless, the event information tells us which index was selected
    JOptionPane.showMessageDialog(SwingView.this,
            "The changing index is " + e.getFirstIndex(), "Index", JOptionPane.PLAIN_MESSAGE);
    // This gets us the string value that's currently selected
    JOptionPane.showMessageDialog(SwingView.this,
            "The current string item is " + this.listOfStrings.getSelectedValue(), "Selected string", JOptionPane.PLAIN_MESSAGE);
    // This gets us the integer value that's currently selected
    JOptionPane.showMessageDialog(SwingView.this,
            "The current number item is " + this.listOfIntegers.getSelectedValue(), "Selected integer", JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Prints the given string to the view.
   *
   * @param s
   */
  @Override
  public void showString(String s) {
    System.out.println(s);

  }

  /**
   * menu to the view.
   */
  @Override
  public void showOptions() {

  }

  /**
   * Prints the error message to the console .
   */
  @Override
  public void showOptionError() {

  }

  /**
   * Prints the input prefix to the console.
   */
  @Override
  public void prefix() {

  }

  /**
   * Adds the given gui component to the view.
   *
   * @param panel
   */
  @Override
  public void addPanel(JPanel panel) {
    this.add(panel);
  }





  @Override
  public void updateSourceHistogram(ImageIcon[] images) {
    for (int i = 0; i <  images.length; i++) {
      histogramLabels[i].setIcon(images[i]);
    }
  }

  @Override
  public void updateSourceImage(ImageIcon image) {
    sourceLabel.setIcon(image);
  }


  @Override
  public void updatePreviewHistogram(ImageIcon[] images) {
    for (int i = 0; i <  images.length; i++) {
      histogramLabels[i].setIcon(images[i]);
    }
  }

  @Override
  public void updatePreviewImage(ImageIcon image) {
    sourceLabel.setIcon(image);
  }
}
