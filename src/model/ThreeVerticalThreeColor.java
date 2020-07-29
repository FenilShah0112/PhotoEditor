package model;

import java.io.IOException;

/**
 * Represents all the flags with three equal vertical stripes and 3 different colors.
 * Most of the flags three vertical stripes have a default ratio of 2:3.
 */
public class ThreeVerticalThreeColor implements Flags {
  private int[][][] image;
  private final int[][] color;
  private final int width;
  private final int height;
  private double ratio = 1.5;
  
  /**
   * Constructor for the flags with ratio 2:3.
   * @param color three colors required for the flag
   * @param height of the flag in pixels
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public ThreeVerticalThreeColor(int[][] color, int height) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.color = color;
    this.height = height;
    this.width = (int) (height * ratio);
    image = new int[this.height][this.width][3];
  }
  
  /**
   * Constructor for the flags with a different ratio.
   * @param color three colors required for the flag
   * @param height of the flag in pixels
   * @param ratio of the flag
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public ThreeVerticalThreeColor(int[][] color, int height, double ratio) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.color = color;
    this.height = height;
    this.ratio = ratio;
    this.width = (int) (height * ratio);
    image = new int[this.height][this.width][3];
  }

  @Override
  public void generateFlag() {
    int color1;
    int color2;
    int color3;
    if (width % 2 == 2) {
      color1 = (width / 3) + 1;
      color2 = color1 + (width / 3) + 1;
      color3 = color2 + (width / 3);
    }
    else {
      color1 = (width / 3);
      color2 = color1 + (width / 3);
      color3 = color2 + (width / 3);
    }
    
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < color1; i++) {
        for (int k = 0; k < 3; k++) {
          image[j][i][k] = color[0][k];
        }
      }
      for (int i = color1; i < color2; i++) {
        for (int k = 0; k < 3; k++) {
          image[j][i][k] = color[1][k];
        }
      }
      for (int i = color2; i < color3; i++) {
        for (int k = 0; k < 3; k++) {
          image[j][i][k] = color[2][k];
        }
      }
    }
  }
  
  @Override
  public double getRatio() {
    return ratio;
  }

  @Override
  public void saveFlag(String name) {
    try {
      ImageUtilities.writeImage(image, image[0].length, image.length, "res/" + name + ".jpeg");
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int[][][] getFlag() {
    return image;
  }
}
