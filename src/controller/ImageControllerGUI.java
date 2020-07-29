package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.StringReader;

import model.BorderDecorator;
import model.ImageModel;
import model.ImageUtilities;
import model.StickerDecorator;
import model.TextDecorator;
import view.ImageView;

/**
 * Controller class for GUI operations on image.
 */
public class ImageControllerGUI implements ActionListener {
  
  private final ImageModel model;
  private final ImageView view;
  private boolean redEye = false;
  private boolean sticker = false;
  private String path;
  
  /**
   * Constructor class for controller.
   * @param model reference to the model class
   * @param view reference to the view class
   */
  public ImageControllerGUI(ImageModel model, ImageView view) { 
    this.model = model;
    this.view = view;
    Mouse m = new Mouse();
    view.setListener(m, m, this);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    int result;
    switch (e.getActionCommand()) {
    
      case "load":
        path = view.loadImage();
        if (path != null) {
          try {
            model.loadImage(ImageUtilities.readImage(path));
            view.displayImage(model.getImage());
          }
          catch (IOException exceptions) {
            view.showErrorDialog("File not found");
          }
        }
        break;
        
      case "vertical":
        String[] temp = view.showDialog("rainbow");
        result = model.verticalRainbow(temp[0], temp[1]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "horizontal":
        temp = view.showDialog("rainbow");
        result = model.horizontalRainbow(temp[0], temp[1]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "checker":
        temp = view.showDialog("checker");
        result = model.checkerboard(temp[0], temp[1]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "france":
        temp = view.showDialog("flag");
        result = model.generateFlag(1, temp[0]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "norway":
        temp = view.showDialog("flag");
        result = model.generateFlag(2, temp[0]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "greece":
        temp = view.showDialog("flag");
        result = model.generateFlag(3, temp[0]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "switzerland":
        temp = view.showDialog("flag");
        result = model.generateFlag(4, temp[0]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        break;
        
      case "blur":
        model.blur();
        view.displayImage(model.getImage());
        break;
        
      case "sharp":
        model.sharpen();
        view.displayImage(model.getImage());
        break;
        
      case "grey":
        model.greyScale();
        view.displayImage(model.getImage());
        break;
        
      case "sepia":
        model.sepia();
        view.displayImage(model.getImage());
        break;
        
      case "edge":
        model.edgeDetection();
        view.displayImage(model.getImage());
        break;
        
      case "dither":
        model.dither();
        view.displayImage(model.getImage());
        break;
        
      case "greyEnhance":
        model.greyEnhance();
        view.displayImage(model.getImage());
        break;
        
      case "mosaic":
        temp = view.showDialog("mosaic");
        view.showMessage("Begin", "I swear it works. Just wait for a few minutes :D");
        result = model.mosaic(temp[0]);
        if (result == 0) {
          view.showErrorDialog("Enter valid inputs");
          break;
        }
        view.displayImage(model.getImage());
        view.showMessage("Done", "See. I told you!");
        break;
        
      case "redeye":
        view.showMessage("Red Eye", "Select only the area with the Red Eye");
        redEye = true;
        break;
        
      case "border":
        temp = view.showDialog("border");
        Color color = view.showColorChooser();
        if (color == null) {
          view.setLabel(new BorderDecorator(view.getLabel(), temp[0], Color.BLACK).decorate());
        }
        else {
          view.setLabel(new BorderDecorator(view.getLabel(), temp[0], color).decorate()); 
        }
        break;
        
      case "sticker":
        path = view.loadImage();
        view.showMessage("Select Area", "Select the area where you want to insert the sticker");
        sticker = true;
        break;
        
      case "text":
        temp = view.showDialog("text");
        color = view.showColorChooser();
        if (color == null) {
          view.setLabel(new TextDecorator(view.getLabel(), temp, Color.BLACK).decorate());
        }
        else {
          view.setLabel(new TextDecorator(view.getLabel(), temp, color).decorate()); 
        }
        break;
        
        
      case "save":
        if (model.hasImage()) {
          path = view.saveImage();
          if (path != null) {
            model.saveImage(path);
          }
          break;
        }  
        view.showErrorDialog("No image to save");
        break;
        
      case "batch":
        temp = view.showDialog("bash");
        try {
          if (temp != null) {
            result = new ImageControllerBash(new StringReader(temp[0]), System.out).run(model);
            if (result == 0) {
              view.showErrorDialog("Enter a valid command");
            }
            else {
              view.displayImage(model.getImage()); 
            }
          }
          else {
            view.showErrorDialog("Enter a valid command");
          }
        }
        catch (IOException e1) {
          view.showErrorDialog("File not found");
        }
        break;
        
      default:
        view.showErrorDialog("What did you click??? o_O");
        break;
    }
  }
  
  private class Mouse implements MouseListener, MouseMotionListener {
    
    private int[] mStart;
    private int[] mEnd;

    @Override
    public void mouseClicked(MouseEvent e) {
      //do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
      //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (mEnd == null) {
        mEnd = new int[2];
        mEnd[0] = e.getX();
        mEnd[1] = e.getY();
      }
      if (redEye && (mStart[0] != mEnd[0] && mStart[1] != mEnd[1])) {
        model.redEye(mStart, mEnd);
        view.displayImage(model.getImage());
        redEye = false;
      }
      else if (sticker && path != null && (mStart[0] != mEnd[0] && mStart[1] != mEnd[1])) {
        try {
          Image sticker = new StickerDecorator(view.getLabel(),
              ImageUtilities.readImage(path), mStart, mEnd).decorate();
          if (sticker != null) {
            view.setLabel(sticker);
          }
          else {
            view.showErrorDialog("Sticker has to be placed inside the image");
          }
        }
        catch (IOException exceptions) {
          view.showErrorDialog("File not found");
        }
        sticker = false;
      }
      mStart = null;
      mEnd = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //do nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (mStart == null) {
        mStart = new int[2];
        mStart[0] = e.getX();
        mStart[1] = e.getY();
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      //do nothing
    }
  }
}
