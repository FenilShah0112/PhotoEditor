package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Model class for operations on an image.
 */
public class ImageModel {
  
  private int[][][] image;
  
  /**
   * Saves the image using the name provided in the location /res/"image".
   * @param name of the image used for storing
   */
  public void saveImage(String name) {
    try {
      ImageUtilities.writeImage(image, image[0].length, image.length, name);
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Loads an image as a 3D array.
   * @param img as a 3D array
   */
  public void loadImage(int[][][] img) {
    image = img;
  }
  
  /**
   * Creates a vertical rainbow with user specified dimensions.
   * @param w width of the rainbow
   * @param h height of the ranibow
   */
  public int verticalRainbow(String w, String h) {
    int height;
    int width;
    try {
      width = Integer.parseInt(w);
      height = Integer.parseInt(h);
    }
    catch (NumberFormatException e) {
      return 0;
    }
    image = new int[height][width][3];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        rainbowHelper(j, i, j * 7 / height);
      }
    }
    return 1;
  }
  
  /**
   * Creates a horizontal rainbow with user specified dimensions.
   * @param w width of the rainbow
   * @param h height of the ranibow
   */
  public int horizontalRainbow(String w, String h) {
    int height;
    int width;
    try {
      width = Integer.parseInt(w);
      height = Integer.parseInt(h);
    }
    catch (NumberFormatException e) {
      return 0;
    }
    image = new int[height][width][3];
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        rainbowHelper(j, i, i * 7 / width);
      }
    }
    return 1;
  }
  
  /**
   * Helper function for generating a rainbow.
   * @param j jth row of pixels
   * @param i ith column of pixels
   * @param k kth strip of the rainbow
   */
  private void rainbowHelper(int j, int i, int k) {
    switch (k) {
      case 0:
        image[j][i][0] = 255;
        image[j][i][1] = 0;
        image[j][i][2] = 0;
        break;
        
      case 1:
        image[j][i][0] = 255;
        image[j][i][1] = 127;
        image[j][i][2] = 0;
        break;
        
      case 2:
        image[j][i][0] = 255;
        image[j][i][1] = 255;
        image[j][i][2] = 0;
        break;
        
      case 3:
        image[j][i][0] = 0;
        image[j][i][1] = 255;
        image[j][i][2] = 0;
        break;
        
      case 4:
        image[j][i][0] = 0;
        image[j][i][1] = 0;
        image[j][i][2] = 255;
        break;
        
      case 5:
        image[j][i][0] = 127;
        image[j][i][1] = 0;
        image[j][i][2] = 255;
        break;
        
      case 6:
        image[j][i][0] = 255;
        image[j][i][1] = 0;
        image[j][i][2] = 255;
        break;
        
      default:
        break;
    }
  }

  /**
   * Creates a checkerboard pattern with user specified dimensions.
   * @param size squareSize the height and width of a single square
   * @param num numSquares number of squares in a single row/column
   */
  public int checkerboard(String size, String num) {
    int squareSize;
    int numSquares;
    try {
      squareSize = Integer.parseInt(size);
      numSquares = Integer.parseInt(num);
    }
    catch (NumberFormatException e) {
      return 0;
    }
    image = new int[squareSize * numSquares][squareSize * numSquares][3];
    for (int i = 0; i < squareSize * numSquares; i++) {
      for (int j = 0; j < squareSize * numSquares; j++) {
        if (((i / squareSize) + (j / squareSize)) % 2 == 0) {
          image[i][j][0] = 0;
          image[i][j][1] = 0;
          image[i][j][2] = 0;
        }
        else {
          image[i][j][0] = 255;
          image[i][j][1] = 255;
          image[i][j][2] = 255;
        }
      }
    }
    return 1;
  }
  
  /**
   * Applies all types of filters on the image.
   * @param filter matrix to be used to apply filters
   */
  private int[][][] filter(float[][] filter, int[][][] image) {
    
    int[][][] temp = new int[image.length][image[0].length][3];
    
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          int newValue = 0;
          for (int a = 0; a < filter.length; a++) {
            for (int b = 0; b < filter.length; b++) {
              if ((i - filter.length / 2 + a) >= 0 
                  && (i - filter.length / 2 + a) < image.length
                  && (j - filter.length / 2 + b) >= 0
                  && (j - filter.length / 2 + b) < image[0].length) {
                newValue += 
                    filter[a][b] * image[i - filter.length / 2 + a][j - filter.length / 2 + b][k];
              }
            }
          }
          if (newValue < 0) {
            newValue = 0;
          }
          else if (newValue > 255) {
            newValue = 255;
          }
          temp[i][j][k] = newValue;
        }
      }
    }
    return temp;
  }
  
  /**
   * Applies all types of color transformation to the image.
   * @param transformation matrix to be applied for transforming colors
   */
  private void colorTransform(float[][] transformation) {
    
    int r;
    int g;
    int b;
    
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        r = image[i][j][0];
        g = image[i][j][1];
        b = image[i][j][2];
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = (int) (transformation[k][0] * r) + (int) (transformation[k][1] * g)
              + (int) (transformation[k][2] * b);
          if (image[i][j][k] < 0) {
            image[i][j][k] = 0;
          }
          else if (image[i][j][k] > 255) {
            image[i][j][k] = 255;
          }
        }
      }
    }
  }
  
  /**
   * Blurs the image.
   */
  public void blur() {
    
    float[][] blur = {
        {(float) 0.0625, (float) 0.125, (float) 0.0625},
        {(float) 0.125, (float) 0.25, (float) 0.125},
        {(float) 0.0625, (float) 0.125, (float) 0.0625}
    };
    image = filter(blur, image);
  }
  
  /**
   * Sharpens the image.
   */
  public void sharpen() {
    float[][] sharp = {
        {(float) -0.125, (float) -0.125, (float) -0.125, (float) -0.125, (float) -0.125},
        {(float) -0.125, (float) 0.25, (float) 0.25, (float) 0.25, (float) -0.125},
        {(float) -0.125, (float) 0.25, (float) 1, (float) 0.25, (float) -0.125},
        {(float) -0.125, (float) 0.25, (float) 0.25, (float) 0.25, (float) -0.125},
        {(float) -0.125, (float) -0.125, (float) -0.125, (float) -0.125, (float) -0.125}
    };
    image = filter(sharp, image);
  }
  
  /**
   * Produces a dithered image.
   */
  public void dither() {
    greyScale();
    int threshold = (0 + 255) / 2;
    int oldColor;
    int newColor;
    int error;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        oldColor = image[i][j][0];
        if (oldColor > threshold) {
          newColor = 255;
        }
        else {
          newColor = 0;
        }
        error = oldColor - newColor;
        image[i][j][0] = newColor;
        image[i][j][1] = newColor;
        image[i][j][2] = newColor;
        if (j < image[0].length - 1) {
          image[i][j + 1][0] += 7 * error / 16;
        }
        if (i < image.length - 1) {
          image[i + 1][j][0] += 5 * error / 16;
          if (j < image[0].length - 1) {
            image[i + 1][j + 1][0] += 1 * error / 16;
          }
          if (j > 0) {
            image[i + 1][j - 1][0] += 3 * error / 16;
          }
        }
      }
    }
  }
  
  /**
   * Produces a greyscale image with edge detection.
   */
  public void edgeDetection() {
    int[][][] gx = image.clone();
    int[][][] gy = image.clone();
    
    float[][] kx = {
        {(float) 1, (float) 0, (float) -1},
        {(float) 2, (float) 0, (float) -2},
        {(float) 1, (float) 0, (float) -1}
    };
    
    float[][] ky = {
        {(float) 1, (float) 2, (float) 1},
        {(float) 0, (float) 0, (float) 0},
        {(float) -1, (float) -2, (float) -1}
    };
    
    gx = filter(kx, gx);
    gy = filter(ky, gy);
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = 
              (int) Math.round(Math.sqrt(Math.pow(gx[i][j][k], 2) + Math.pow(gy[i][j][k], 2)));
          if (image[i][j][k] < min) {
            min = image[i][j][k];
          }
          if (image[i][j][k] > max) {
            max = image[i][j][k];
          }
        }
      }
    }
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = (image[i][j][k] - min) * 255 / (max - min);
        }
      }
    }
    greyScale();   
  }
  
  /**
   * Enhances a grey scale image by applying Histogram Equalization.
   */
  public void greyEnhance() {
    greyScale();
    
    //Step 1: Frequency of grey intensity
    int[] freq = new int[255];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        freq[image[i][j][0]]++;
      }
    }
    
    //Step 2: Calculating the cummulative frequency
    int[] cf = new int[255];
    cf[0] = freq[0];
    for (int i = 1; i < cf.length; i++)  {
      cf[i] = freq[i] + cf[i - 1];
    }
    
    //Step 3: Calculating the ideal Frequency
    int[] idealFreq = new int[255];
    int idealValue = image.length * image[0].length / 255;
    int carryOver = image.length * image[0].length % 255;
    for (int i = 0; i < idealFreq.length; i++) {
      idealFreq[i] = idealValue;
      if (i >= ((idealFreq.length - carryOver) / 2) && i <= ((idealFreq.length + carryOver) / 2)) {
        idealFreq[i]++;
      }
    }
    
    //Step 4: Calculating ideal cummulative frequency
    for (int i = 1; i < idealFreq.length - 1; i++) {
      idealFreq[i] += idealFreq[i - 1];
    }
    idealFreq[254] = image.length * image[0].length;
    
    int[] temp = new int[255];
    for (int i = 0; i < 255; i++) {
      temp[i] = cf[i];
    }
    
    //Step 5: Mapping the cummulative frequencies
    int counter1 = 0;
    while (freq[counter1] < idealFreq[0]) {
      cf[counter1] = 0;
      counter1++;
    }
    
    for (int i = counter1; i < cf.length; i++) {
      for (int j = 0; j < idealFreq.length; j++) {
        if (cf[i] == idealFreq[j]) {
          cf[i] = j;
        }
        else if (j == idealFreq.length - 1) {
          cf[i] = j;
        }
        else if (cf[i] > idealFreq[j] && cf[i] <= idealFreq[j + 1]) {
          if (cf[i] - idealFreq[j] > idealFreq[j + 1] - cf[i]) {
            cf[i] = j + 1;
          }
          else {
            cf[i] = j;
          }
          break;
        }
      }
    }
      
    //Step 6: Replacing the original values.
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int color = image[i][j][0];
        image[i][j][0] = cf[color];
        image[i][j][1] = cf[color];
        image[i][j][2] = cf[color];
      }
    }

  }
  
  /**
   * Transforms image to greyscale.
   */
  public void greyScale() {
    float[][] grey = {
        {(float) 0.2126, (float) 0.7152, (float) 0.0722},
        {(float) 0.2126, (float) 0.7152, (float) 0.0722},
        {(float) 0.2126, (float) 0.7152, (float) 0.0722}
    };
    colorTransform(grey);
  }
  
  /**
   * Transforms image to sepia tone.
   */
  public void sepia() {
    float[][] sepia = {
        {(float) 0.393, (float) 0.769, (float) 0.189},
        {(float) 0.349, (float) 0.686, (float) 0.168},
        {(float) 0.272, (float) 0.534, (float) 0.131}
    };
    colorTransform(sepia);
  }
  
  /**
   * Applying Mosaic effect to the image using k-means clustering.
   */
  public int mosaic(String num) {
    int numOfSeed;
    try {
      numOfSeed = Integer.parseInt(num);
    }
    catch (NumberFormatException e) {
      return 0;
    }
    
    HashMap<int[], List<int[]>> map = new HashMap<>();
    int[] seeds;
    for (int i = 0; i < numOfSeed; i++) {
      seeds = new int[2];
      seeds[0] = (int) (Math.random() * image.length);
      seeds[1] = (int) (Math.random() * image[0].length);
      if (map.containsKey(seeds)) {
        i--;
        continue;
      }
      map.put(seeds, new ArrayList<>());
      int[] temp = new int[5];
      temp[0] = seeds[0];
      temp[1] = seeds[1];
      temp[2] = image[seeds[0]][seeds[1]][0];
      temp[3] = image[seeds[0]][seeds[1]][1];
      temp[4] = image[seeds[0]][seeds[1]][2];
      map.get(seeds).add(temp);
    }
    
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        assignCluster(map, i, j);
      }
    }
    
    for (int[] key: map.keySet()) {
      int r = 0;
      int g = 0;
      int b = 0;
      for (int i = 1; i < map.get(key).size(); i++) {
        r += map.get(key).get(i)[2];
        g += map.get(key).get(i)[3];
        b += map.get(key).get(i)[4];
      }
      r /= map.get(key).size();
      g /= map.get(key).size();
      b /= map.get(key).size();
      for (int i = 0; i < map.get(key).size(); i++) {
        int[] temp = map.get(key).get(i);
        image[temp[0]][temp[1]][0] = r;
        image[temp[0]][temp[1]][1] = g;
        image[temp[0]][temp[1]][2] = b;
      }
    }
    return 1;    
  }
  
  
  private void assignCluster(HashMap<int[], List<int[]>> map, int i, int j) {
    int[] cluster = new int[2]; 
    cluster[0] = i;
    cluster[1] = j;
    if (map.containsKey(cluster)) {
      return;
    }
    double min = Double.MAX_VALUE;
    for (int[] key: map.keySet()) {
      double distance = Math.sqrt(Math.pow(key[0] - i, 2) + Math.pow(key[1] - j, 2));
      if (distance < min) {
        min = distance;
        cluster = key;
      }
    }
    int[] temp = new int[5];
    temp[0] = i;
    temp[1] = j;
    temp[2] = image[i][j][0];
    temp[3] = image[i][j][1];
    temp[4] = image[i][j][2];
    map.get(cluster).add(temp);
  }

  /**
   * Generates flags based on the type of flag selected.
   * Currently supports 4 types of flags. 
   * 1) Three Vertical Stripes (France)
   * 2) Nordic Cross (Norway)
   * 3) Many Horizontal lines (Greece)
   * 4) Random flags (Switzerland)
   * @param type of the flag
   * @param h height of the flag
   */
  public int generateFlag(int type, String h) {
    int height;
    try {
      height = Integer.parseInt(h);
    }
    catch (NumberFormatException e) {
      return 0;
    }
    Flags flag;
    switch (type) {
      case 1:
        int[][] france = {
            {0, 17, 131},
            {255, 255, 255},
            {229, 13, 44}
        };
        flag = new ThreeVerticalThreeColor(france, height);
        flag.generateFlag();
        image = flag.getFlag();
        break;
        
      case 2:
        int[][] norway = {
            {232, 17, 35},
            {255, 255, 255},
            {1, 27, 85}
        };
        flag = new NordicCrossThreeColor(norway, height);
        flag.generateFlag();
        image = flag.getFlag();
        break;
        
      case 3:
        int[][] greece = {
            {15, 72, 159},
            {255, 255, 255}
        };
        flag = new ManyHorizontalEqualTwoColor(greece, height, 9);
        flag.generateFlag();
        image = flag.getFlag();
        break;
        
        
      case 4:
        flag = new RandomFlags("Switzerland", height);
        flag.generateFlag();
        image = flag.getFlag();
        break;
      
      default:
        break;
    }
    return 1;
  }

  public int[][][] getImage() {
    return image;
  }

  public void newImage() {
    image = null;
  }

  public boolean hasImage() {
    return image != null;
  }

  /**
   * Removes Red Eye when provided with the area having redEye.
   * @param mStart Top left pixel of area
   * @param mEnd bottom right pixel of area
   */
  public void redEye(int[] mStart, int[] mEnd) {
    int xStart = Math.min(mStart[0], mEnd[0]);
    int xEnd = Math.max(mStart[0], mEnd[0]);
    int yStart = Math.min(mStart[1], mEnd[1]);
    int yEnd = Math.max(mStart[1], mEnd[1]);
    for (int j = xStart; j < xEnd && j < image[0].length; j++) {
      for (int i = yStart; i < yEnd && i < image.length; i++) {
        if (image[i][j][0] > image[i][j][1] + image[i][j][2]) {
          image[i][j][0] = (image[i][j][1] + image[i][j][2]) / 2;
        }
      }
    }
  }
}
