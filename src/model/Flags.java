package model;

/**
 * Represents flags of different countries.
 */
public interface Flags {

  /**
   * Generates the flag image based on the attributes of the class.
   */
  void generateFlag();
  
  /**
   * Saves the image using the name provided in the location /res/"image".
   * @param name of the image used for storing
   */
  void saveFlag(String name);
  
  /**
   * Returns the flag to the model.
   * @return rgb values for the flag
   */
  int[][][] getFlag();
  
  /**
   * Returns the ratio of width to height for the flag.
   */
  double getRatio();
}
