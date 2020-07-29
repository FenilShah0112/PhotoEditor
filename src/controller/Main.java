package controller;

import java.awt.AWTException;
import java.io.FileReader;
import java.io.IOException;

import model.ImageModel;
import view.ImageView;

/**
 * Main class used just to decide which contoller to choose. 
 */
public class Main {
  
  /**
   * Main method of the project.
   * @param args to decide which controller to use
   * @throws IOException if the image loaded doesn't exist
   * @throws AWTException if there is an error with java.awt
   */
  public static void main(String[] args) throws IOException, AWTException {
    ImageModel model = new ImageModel();
    
    if (args.length == 0) {
      System.out.println("Invalid argument\n");
      return;
    }
    
    
    if (args[0].equals("-interactive")) {
      ImageView view = new ImageView();
      new ImageControllerGUI(model, view);
    }
    else if (args[0].equals("-script")) {
      if (args.length == 1) {
        System.out.println("Enter a valid file name");
        return;
      }
      Readable reader = new FileReader(args[1]);
      ImageControllerBash controller = new ImageControllerBash(reader, System.out);
      controller.run(model);
    }
    else {
      System.out.println("Invalid argument\n");
      return;
    }
    System.out.println("Exiting code");
  }
}
