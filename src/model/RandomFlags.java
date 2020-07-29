package model;

import java.io.IOException;

/**
 * For random flags.
 */
public class RandomFlags implements Flags {
  private int[][][] image;
  private final int width;
  private final int height;
  private double ratio = 1;
  private final String country;
  
  
  /**
   * Constructor for the flags with ratio 8:11.
   * @param string three colors required for the flag
   * @param height of the flag in pixels
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public RandomFlags(String string, int height) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.country = string;
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
   * @param height of the flag in pixels
   * @param ratio of the flag
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public RandomFlags(String string, int height, double ratio) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.country = string;
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
    switch (this.country) {
      case "Switzerland":
        int[][] color = {
            {201, 22, 24},
            {255, 255, 255}
        };
        swiz(color);
        break;
        
      case "Extra":
        System.out.println("For adding more flags in the future");
        break;
        
      default:
        break;
    }

  }

  private void swiz(int[][] color) {
    
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[0][k];
        }
      }
    }
    
    for (int i = 13 * height / 32; i < 19 * height / 32; i++) {
      for (int j = 6 * width / 32; j < 26 * width / 32; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[1][k];
        }
      }
    }
    for (int i = 13 * width / 32; i < 19 * width / 32; i++) {
      for (int j = 6 * height / 32; j < 26 * height / 32; j++) {
        for (int k = 0; k < 3; k++) {
          image[j][i][k] = color[1][k];
        }
      }
    }
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
  public double getRatio() {
    return ratio;
  }
  
  @Override
  public int[][][] getFlag() {
    return image;
  }

}
