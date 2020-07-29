package controller;

import java.io.IOException;
import java.util.Scanner;

import model.ImageModel;
import model.ImageUtilities;

/**
 * Controller class for batch script commands.
 */
public class ImageControllerBash {
  
  private final Readable in;
  private final Appendable out;
  
  public ImageControllerBash(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }
  
  /**
   * Driver method for the entire program. Takes input in a specific format
   * 'pattern' => 'vertical \width\ \height\' OR 'horizontal \width\ \height\' OR 
   * 'checkboard \size\ \squares\'
   * 'load \image name\' => 'blur/sharpen/greyscale/sepia/edge/dither' => 'save \image name\' 
   * 'flag \type of flag\ \height\' (1 for France; 2 for Norway; 3 for Greece; 4 for Switzerland).
   * @param model object of the model class used for doing operations on image
   * @throws IOException if the File is not found
   */
  @SuppressWarnings("resource")
  public int run(ImageModel model) throws IOException {
    
    Scanner sc = new Scanner(this.in);
    
    boolean exit = false;

    while (sc.hasNext()) {
      switch (sc.next()) {
        case "pattern":
          switch (sc.next()) {
            case "vertical":
              model.verticalRainbow(sc.next(), sc.next());
              break;
              
            case "horizontal":
              model.horizontalRainbow(sc.next(), sc.next());
              break;
              
            case "checkerboard":
              model.checkerboard(sc.next(), sc.next());
              break;
              
            default:
              break;  
          }
          break;
          
        case "load":
          model.loadImage(ImageUtilities.readImage(sc.next()));
          break;
          
        case "blur": 
          if (model.hasImage()) {
            model.blur();
          }
          break;
          
        case "sharpen":
          if (model.hasImage()) {
            model.sharpen();
          }
          break;
          
        case "greyEnhance":
          if (model.hasImage()) {
            model.greyEnhance();
          }
          break;
          
        case "greyscale":
          if (model.hasImage()) {
            model.greyScale();
          }
          break;
          
        case "sepia":
          if (model.hasImage()) {
            model.sepia();
          }
          break;
          
        case "edge":
          if (model.hasImage()) {
            model.edgeDetection();
          }
          break;
          
        case "dither":
          if (model.hasImage()) {
            model.dither();
          }
          break;
          
        case "flag":
          if (sc.hasNext()) {
            model.generateFlag(sc.nextInt(), sc.next());
          }
          break;
          
        case "mosaic":
          if (sc.hasNext()) {
            model.mosaic(sc.next());
          }
          break;
          
        case "redeye":
          if (sc.hasNext()) {
            int[] mStart = {sc.nextInt(), sc.nextInt()};
            int[] mEnd = {sc.nextInt(), sc.nextInt()};
            model.redEye(mStart, mEnd);
          }
          break;
          
        case "exit":
          System.out.println("Exiting code");
          System.exit(0);
          break;
          
        case "save":
          if (model.getImage() != null) {
            model.saveImage(sc.next());
          }
          break;
          
        default:
          this.out.append("Enter a valid command");
          break;
      }
      if (exit) {
        break;
      }
    }
    return 1; 
  }


}
