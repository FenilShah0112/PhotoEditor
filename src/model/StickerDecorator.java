package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Decorator class for adding stickers to the image.
 */
public class StickerDecorator implements ImageDecorator {
  
  private JLabel label;
  private int[][][] sticker;
  private int[] mStart;
  private int[] mEnd;

  /**
   * Constructor for Sticker Decorator.
   * @param label which displays the image in view
   * @param readImage is the sticker to be added
   * @param mStart top left corner of sticker area
   * @param mEnd bottom right corner of the sticker area
   */
  public StickerDecorator(JLabel label, int[][][] readImage, int[] mStart, int[] mEnd) {
    this.label = label;
    sticker = readImage;
    this.mStart = mStart;
    this.mEnd = mEnd;
  }

  @Override
  public Image decorate() {
    BufferedImage img = new BufferedImage(sticker[0].length, sticker.length,
        BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < sticker.length; i++) {
      for (int j = 0; j < sticker[0].length; j++) {
        int r = sticker[i][j][0];
        int g = sticker[i][j][1];
        int b = sticker [i][j][2];        
        int color = (r << 16) + (g << 8) + b;
        img.setRGB(j, i, color);
      }
    }
    int xStart = Math.min(mStart[0], mEnd[0]);
    int xEnd = Math.max(mStart[0], mEnd[0]);
    int yStart = Math.min(mStart[1], mEnd[1]);
    int yEnd = Math.max(mStart[1], mEnd[1]);
    ImageIcon icon = (ImageIcon) label.getIcon();
    Image image = icon.getImage();
    Graphics g = image.getGraphics();
    if (xStart > image.getHeight(null) || yStart > image.getWidth(null)) {
      return null;
    }
    g.drawImage(img, xStart, yStart, 
        Math.min(xEnd, image.getHeight(null)),
        Math.min(yEnd, image.getWidth(null)),
        0, 0, img.getWidth(),
        img.getHeight(), null);

    return image;
  }

}
