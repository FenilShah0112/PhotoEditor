package view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * View class extending JFrame to create a GUI.
 */
public class ImageView extends JFrame {

  private static final long serialVersionUID = 6392592751117828515L;
  
  //Main menu items
  private JMenuItem load;
  private JMenuItem save;
  private JMenuItem bash;
  
  //Pattern menu items
  private JMenuItem vertical;
  private JMenuItem horizontal;
  private JMenuItem checker;
  
  //Flag menu items
  private JMenuItem france;
  private JMenuItem norway;
  private JMenuItem greece;
  private JMenuItem switzerland;
  
  //Filter menu items
  private JMenuItem blur;
  private JMenuItem sharp;
  private JMenuItem edge;
  private JMenuItem dither;
  private JMenuItem mosaic;
  private JMenuItem redEye;
  
  //Color menu items
  private JMenuItem grey;
  private JMenuItem greyEnhance;
  private JMenuItem sepia;
  
  //Decorator manu items
  private JMenuItem border;
  private JMenuItem sticker;
  private JMenuItem text;
  
  //Display Image
  private JLabel imageLabel;
  
  /**
   * Constructor class to initialize the JFrame and all its contents.
   */
  public ImageView() {
    super("Image Processing");
    
    //Setting up all the menus
    JMenuBar menu = new JMenuBar();
    JMenu main = new JMenu("Main");
    JMenu pattern = new JMenu("Patterns");
    JMenu flag = new JMenu("Flags");
    JMenu options = new JMenu("Options");
    JMenu filter = new JMenu("Filters");
    JMenu color = new JMenu("Colors");
    JMenu decorators = new JMenu("Decorators");
    
    //Setting up main menu
    load = new JMenuItem("Load");
    load.setActionCommand("load");
    bash = new JMenuItem("Batch Scritps");
    bash.setActionCommand("batch");
    save = new JMenuItem("Save");
    save.setActionCommand("save");
    
    //Setting up pattern sub-menu
    vertical = new JMenuItem("Vertical Rainbow");
    vertical.setActionCommand("vertical");
    horizontal = new JMenuItem("Horizontal Rainbow");
    horizontal.setActionCommand("horizontal");
    checker = new JMenuItem("Checker board");
    checker.setActionCommand("checker");
    
    //Setting up flag sub-menu
    france = new JMenuItem("France");
    france.setActionCommand("france");
    norway = new JMenuItem("Norway");
    norway.setActionCommand("norway");
    greece = new JMenuItem("Greece");
    greece.setActionCommand("greece");
    switzerland = new JMenuItem("Switzerland");
    switzerland.setActionCommand("switzerland");
    
    //Setting up filters sub-menu
    blur = new JMenuItem("Blur");
    blur.setActionCommand("blur");
    sharp = new JMenuItem("Sharpen");
    sharp.setActionCommand("sharp");
    edge = new JMenuItem("Edge Detection");
    edge.setActionCommand("edge");
    dither = new JMenuItem("Dither");
    dither.setActionCommand("dither");
    mosaic = new JMenuItem("Mosaic");
    mosaic.setActionCommand("mosaic");
    redEye = new JMenuItem("Red Eye");
    redEye.setActionCommand("redeye");
    
    //Setting up Color sub-menu
    grey = new JMenuItem("Greyscale");
    grey.setActionCommand("grey");
    greyEnhance = new JMenuItem("Greyscale Enhanced");
    greyEnhance.setActionCommand("greyEnhance");
    sepia = new JMenuItem("Sepia");
    sepia.setActionCommand("sepia");
    
    //Setting up decorators menu
    border = new JMenuItem("Add Border");
    border.setActionCommand("border");
    sticker = new JMenuItem("Add Sticker");
    sticker.setActionCommand("sticker");
    text = new JMenuItem("Add Text");
    text.setActionCommand("text");
    
    //Adding items to respective menus
    pattern.add(vertical);
    pattern.add(horizontal);
    pattern.add(checker);
    
    flag.add(france);
    flag.add(norway);
    flag.add(greece);
    flag.add(switzerland);
    
    filter.add(blur);
    filter.add(sharp);
    filter.add(edge);
    filter.add(dither);
    filter.add(mosaic);
    filter.add(redEye);
    
    color.add(grey);
    color.add(greyEnhance);
    color.add(sepia);
    
    decorators.add(border);
    decorators.add(sticker);
    decorators.add(text);
    
    options.add(filter);
    options.add(color);
    
    main.add(load);
    main.add(pattern);
    main.add(flag);
    main.add(save);
    main.add(bash);
    
    menu.add(main);
    menu.add(options);
    menu.add(decorators);
    
    //Setting up the image display;
    JPanel imagePanel = new JPanel();
    imagePanel.setBackground(Color.WHITE);
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.LINE_AXIS));
    
    imageLabel = new JLabel();
    imageLabel.setOpaque(true);
    imageLabel.setBackground(Color.WHITE);
    imageLabel.setForeground(Color.WHITE);
    imageLabel.setVerticalAlignment(JLabel.TOP);
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(700, 500));
    imageScrollPane.setBackground(Color.WHITE);
    imagePanel.add(imageScrollPane);
    add(imagePanel);
    
    setJMenuBar(menu);
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(true); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
  }

  /**
   * Sets the mouse and action listeners for all the options.
   * @param motion Mouse motion listener for drag
   * @param mouse Mouse Listener for release
   * @param action listener for selecting options from menubar
   */
  public void setListener(MouseMotionListener motion, MouseListener mouse,ActionListener action) {
    load.addActionListener(action);
    vertical.addActionListener(action);
    horizontal.addActionListener(action);
    checker.addActionListener(action);
    france.addActionListener(action);
    norway.addActionListener(action);
    greece.addActionListener(action);
    switzerland.addActionListener(action);
    blur.addActionListener(action);
    sharp.addActionListener(action);
    grey.addActionListener(action);
    greyEnhance.addActionListener(action);
    sepia.addActionListener(action);
    edge.addActionListener(action);
    dither.addActionListener(action);
    mosaic.addActionListener(action);
    redEye.addActionListener(action);
    save.addActionListener(action);
    bash.addActionListener(action);
    border.addActionListener(action);
    sticker.addActionListener(action);
    text.addActionListener(action);
    
    imageLabel.addMouseMotionListener(motion);
    imageLabel.addMouseListener(mouse);
  }

  /**
   * Creates a new buffer image and sets it as an icon to imageLabel.
   * @param rgb array used for creating the image
   */
  public void displayImage(int[][][] rgb) {
    BufferedImage img = new BufferedImage(rgb[0].length, rgb.length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < rgb.length; i++) {
      for (int j = 0; j < rgb[0].length; j++) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];        
        int color = (r << 16) + (g << 8) + b;
        img.setRGB(j, i, color);
      }
    }
    imageLabel.setIcon(new ImageIcon(img));
  }
  
  /**
   * Generates a File chooser dialog and returns the selected path to the controller.
   * @return path of the file to be loaded. null if nothing is selected
   */
  public String loadImage() {
    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
    j.addChoosableFileFilter(new FileFilter() {

      @Override
      public boolean accept(File f) {
          return f.getName().endsWith(".jpg") 
              || f.getName().endsWith(".png")
              || f.getName().endsWith(".jpeg");
      }
      
      @Override
      public String getDescription() {
          return "Image files";
      }
    });
    j.setAcceptAllFileFilterUsed(false);
    int r = j.showOpenDialog(null); 

    // if the user selects a file 
    if (r == JFileChooser.APPROVE_OPTION) { 
      return j.getSelectedFile().getAbsolutePath();
    } 
    return null;
  }
  
  /**
   * Generates a file chooser dialog for saving the image.
   * @return path of the location where the image is to be stored. Null id nothing selected
   */
  public String saveImage() {
    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
    j.addChoosableFileFilter(new FileFilter() {
      @Override
      public boolean accept(File f) {
          return f.getName().endsWith(".jpg") 
              || f.getName().endsWith(".png")
              || f.getName().endsWith(".jpeg");
      }
      
      @Override
      public String getDescription() {
          return "Image files";
      }
    });
    j.setAcceptAllFileFilterUsed(false);
    int r = j.showSaveDialog(null); 

    if (r == JFileChooser.APPROVE_OPTION) { 
      String path = j.getSelectedFile().getAbsolutePath();
      if (! (path.endsWith(".jpg") || path.endsWith(".png") || path.endsWith(".jpeg"))) {
        return (path + ".jpeg");
      }
      else {
        return path;
      }      
    }
    return null;
  } 

  /**
   * Shows Message dialog for obtaining user inputs. 
   * @param type of dialog to be displayed based on the option selected
   * @return String array of user inputs
   */
  public String[] showDialog(String type) {
    String[] temp = new String[2];
    switch (type) {
      case "rainbow":
        temp[0] = JOptionPane.showInputDialog("Enter Width");
        temp[1] = JOptionPane.showInputDialog("Enter Height");
        break;
        
      case "checker":
        temp[0] = JOptionPane.showInputDialog("Enter Size of Each Square");
        temp[1] = JOptionPane.showInputDialog("Enter Number of Squares per side");
        break;
        
      case "flag":
        temp[0] = JOptionPane.showInputDialog("Enter the height of the flag");
        break;
        
      case "mosaic":
        temp[0] = JOptionPane.showInputDialog("Enter the seed value (100 - 10000)");
        break;
        
      case "bash":
        temp[0] = JOptionPane.showInputDialog("Enter scripts");
        break;
        
      case "border":
        temp[0] = JOptionPane.showInputDialog("Enter the thickness of the border");
        break;
        
      case "text":
        temp[0] = JOptionPane.showInputDialog("Enter text for top");
        temp[1] = JOptionPane.showInputDialog("Enter text for bottom");
        break;
        
      default:
        break;
    }
    return temp;
  }

  /**
   * Opens a dialog box and displays he error message.
   * @param message to be displayed
   */
  public void showErrorDialog(String message) {
    JOptionPane.showMessageDialog(null, message,
        "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows an informative message to the user.
   * @param title Title of the dialog box
   * @param message Message to be displayed
   */
  public void showMessage(String title, String message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);    
  }

  /**
   * Opens a color chooser and returns  the user selected color.
   * @return Color selected by the user
   */
  public Color showColorChooser() {
    return JColorChooser.showDialog(null, "Pick a color", Color.BLACK);
  }

  /**
   * Returns the imageLabel to be used by decorators.
   * @return imageLabel for the decorator
   */
  public JLabel getLabel() {
    return imageLabel;
  }

  /**
   * Sets the imageLabel as returned by the decorator.
   * @param decorate the image to be displayed
   */
  public void setLabel(Image decorate) {
    imageLabel.setIcon(new ImageIcon(decorate));
  }
}
