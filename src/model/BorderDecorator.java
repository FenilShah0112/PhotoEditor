package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Decorator class for applying border to the image.
 */
public class BorderDecorator implements ImageDecorator {
  
  private JLabel label;
  private int border;
  private Color borderColor;
  
  /**
   * Constructor for Border Decorator.
   * @param label which displays the image in View
   * @param border size of border in pixels
   * @param color of the border
   */
  public BorderDecorator(JLabel label, String border, Color color ) {
    this.label = label;
    try {
      this.border = Integer.parseInt(border);
    }
    catch (NumberFormatException e) {
      //do nothing
    }
    this.borderColor = color;
  }
  

  @Override
  public Image decorate() {
    ImageIcon icon = (ImageIcon) label.getIcon();
    Image image = icon.getImage();
    Graphics g = image.getGraphics();
    g.setColor(borderColor);
    g.fillRect(0, 0, image.getWidth(label), border);
    g.fillRect(0, 0, border, image.getHeight(label));
    g.fillRect(image.getWidth(label) - border, 0, image.getWidth(label), image.getHeight(label));
    g.fillRect(0, image.getHeight(label) - border, image.getWidth(label), image.getHeight(label));
    return image;
  }
}
