package model;

import java.awt.Image;

/**
 * Decorator interface for applying decorators to the image in View.
 */
public interface ImageDecorator {
  
  /**
   * Method which edits the graphics of the label icon and returns the new image.
   * @return Image to be added to the label
   */
  Image decorate();

}
