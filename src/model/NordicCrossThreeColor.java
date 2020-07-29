package model;

import java.io.IOException;

/**
 * Represents all the flags with the Nordic Cross with three colors.
 * Most of these flags have a ratio of 8:11 by default.
 */
public class NordicCrossThreeColor implements Flags {
  private int[][][] image;
  private final int[][] color;
  private final int width;
  private final int height;
  private double ratio = 1.375;
  
  /**
   * Constructor for the flags with ratio 8:11.
   * @param color three colors required for the flag
   * @param height of the flag in pixels
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public NordicCrossThreeColor(int[][] color, int height) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.color = color;
    this.height = height;
    this.width = (int) (height * ratio);
    image = new int[this.height][this.width][3];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = -1;
        }
      }
    }
  }
  
  /**
   * Constructor for the flags with a different ratio.
   * @param color three colors required for the flag
   * @param height of the flag in pixels
   * @param ratio of the flag
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public NordicCrossThreeColor(int[][] color, int height, double ratio) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.color = color;
    this.height = height;
    this.ratio = ratio;
    this.width = (int) (height * ratio);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = -1;
        }
      }
    }
  }
  
  @Override
  public void generateFlag() {
    // Cross
    for (int i = 0; i < height; i++) {
      for (int j = width * 7 / 22; j < width * 9 / 22; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[2][k];
        }
      }
    }
    
    for (int i = height * 7 / 16; i < height * 9 / 16; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[2][k];
        }
      }
    }
    
    
    // Border of the Cross
    for (int i = 0; i < height; i++) {
      for (int j = width * 6 / 22; j < width * 10 / 22; j++) {
        for (int k = 0; k < 3; k++) {
          if (image[i][j][k] == -1) {
            image[i][j][k] = color[1][k];
          }
        }
      }
    }
    
    for (int i = height * 6 / 16; i < height * 10 / 16; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          if (image[i][j][k] == -1) {
            image[i][j][k] = color[1][k];
          }
        }
      }
    }
    
    // Rest of the map
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          if (image[i][j][k] == -1) {
            image[i][j][k] = color[0][k];
          }
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
