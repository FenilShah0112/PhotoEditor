package model;

import java.io.IOException;

/**
 * Represents all the flags with many horizontal stripes and 2 different colors.
 */
public class ManyHorizontalEqualTwoColor implements Flags {
  private int[][][] image;
  private final int[][] color;
  private final int width;
  private final int height;
  private double ratio = 1.5;
  int numOfStripes;
  
  
  /**
   * Constructor for the flags with ratio 2:3.
   * @param color three colors required for the flag
   * @param height of the flag in pixels
   * @param numOfStripes based on the flag selected
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public ManyHorizontalEqualTwoColor(int[][] color, int height, int numOfStripes) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    //height = height - (height % numOfStripes);
    this.color = color;
    this.height = height;
    this.width = (int) (height * ratio);
    this.numOfStripes = numOfStripes;
    image = new int[this.height][this.width][3];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = 0;
        }
      }
    }
  }
  
  /**
   * Constructor for the flags with a different ratio.
   * @param color three colors required for the flag
   * @param height of the flag in pixels
   * @param numOfStripes based on the flag selected
   * @param ratio of the flag
   * @throws IllegalArgumentException if the height of the flag is less than 100 pixels
   */
  public ManyHorizontalEqualTwoColor(int[][] color, int height, int numOfStripes, double ratio) 
      throws IllegalArgumentException {
    if (height < 100) {
      throw new IllegalArgumentException("Height has to be at least 100 pixels");
    }
    this.color = color;
    this.height = height;
    this.ratio = ratio;
    this.numOfStripes = numOfStripes;
    this.width = (int) (height * ratio);
    image = new int[this.height][this.width][3];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = 0;
        }
      }
    }
  }
  
  @Override
  public void generateFlag() {
    double stripCount = height / numOfStripes;
    int stripFloor = (int) Math.floor(stripCount);
    int stripCeil = (int) Math.ceil(stripCount);
    boolean flag = true;
    for (int i = 0; i < ((int) (height / 9)) * 9; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          int temp;
          if (flag) {
            temp = stripFloor;
          }
          else {
            temp = stripCeil;
          }
          if ((i / temp) % 2 == 0) {
            flag = !flag;
            image[i][j][k] = color[0][k];
          }
          else {
            image[i][j][k] = color[1][k];
          }
        }
      }
    }
    
    for (int i = 0; i < ((int) (height / 9)) * 5; i++) {
      for (int j = 0; j < ((int) (width / 27)) * 10; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[0][k];
        }
      }
    }
    
    
    for (int i = 0; i < ((int) (height / 9)) * 5; i++) {
      for (int j = ((int) (width / 27)) * 4; j < ((int) (width / 27)) * 6; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[1][k];
        }
      }
    }
    
    for (int i = ((int) (height / 9)) * 2; i < ((int) (height / 9)) * 3; i++) {
      for (int j = 0; j < ((int) (width / 27)) * 10; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = color[1][k];
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
