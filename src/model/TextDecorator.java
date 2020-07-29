package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Decorator class for making memes using text to the image.
 */
public class TextDecorator implements ImageDecorator {
  
  JLabel label;
  String textTop;
  String textBottom;
  Color color;

  /**
   * Constructor for the Text decorator.
   * @param label which displays the image in View
   * @param text to be added in the photo 
   * @param color of the text
   */
  public TextDecorator(JLabel label, String[] text, Color color) {
    this.label = label;
    textTop = text[0];
    textBottom = text[1];
    this.color = color;
  }

  @Override
  public Image decorate() {
    ImageIcon icon = (ImageIcon) label.getIcon();
    Image image = icon.getImage();
    Graphics g = image.getGraphics();
    g.setColor(color);
    g.setFont(new Font("Apple Chancery", Font.BOLD, image.getHeight(null) / 10));
    g.drawString(textTop, (image.getWidth(null) - g.getFontMetrics().stringWidth(textTop)) / 2,
        image.getHeight(null) * 3 / 20);
    g.drawString(textBottom,
        (image.getWidth(null) - g.getFontMetrics().stringWidth(textBottom)) / 2,
        image.getHeight(null) * 19 / 20);
    return image;
  }

}
