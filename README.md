
Image Processing Application

Files included in the application:
1.	Main.java – Works as the Controller as well as the View part for the MVC pattern.

2.	ImageControllerBash.java - Works as a Controller for batch script inputs for the MVC pattern. All the user inputs are read and processed by the control which then passes the work to model based on the values.

3.	ImageControllerGUI.java - Works as a Controller for GUI part of the MVC pattern. Initialises a JFrame by initialising the View class and works as a listener for all the view components. Then processes the inputs provided by the user and decides which function to call. Also calls view to display the image.

4.	ImageModel.java – Works as the Model part for the MVC pattern. All the major operations on the image take place in this file.

5.	Flags.java – Represents the flag interface which is implemented by the rest of the flag classes to generate a flag.

6.	ThreeVerticalThreeColor.java – Represents all the flags with three vertical strips with three different colours. Can be used for 2 different colours by repeating the colours as per the pattern.

7.	NordicCrossThreeColor.java – Represents all the flags with Nordic Cross as in the flag of Norway.

8.	ManyHorizontalEqualTwoColor.java – Represents flags with multiple horizontal lines with alternating colours.

9.	RandomFlags.java – Represents all the random maps which do not have any common patterns.

10. 	ImageDecorator.java - Interface for all the decorator classes. Provides the method decorate which the decorator classes use to edit the image.

11. 	BorderDecorator.java - Implements the ImageDecorator and adds border to the graphics of the JLabel icon and returns the new image to be displayed at the GUI to controller.

12.	StickerDecorator.java - Implements the ImageDecorator and adds Sticker, to the selected area, to the graphics of the JLabel icon and returns the new image to be displayed at the GUI to controller.

13.	TextDecorator.java - Implements the ImageDecorator and adds Text, to the top and bottom, to the graphics of the JLabel icon and returns the new image to be displayed at the GUI to controller.

14.	ImageUtilities.java – Includes functionalities such as reading an image as well as writing an image from a 3D array of integers.

15.	ImageView.java - Implements the View functionality of the MVC pattern. Extends JFrame class and initialises a GUI with a MenuBar and provides functions to display image and generate dialog boxes to be called by the controller.
----------------------------------------------------------------------

How to Run the program:

  1) GUI: java -jar ImageProcessor.java -interactive

  2) Bash: java -jar ImageProcessor.java -script res/input1.txt
	   java -jar ImageProcessor.java -script res/input2.txt

Instructions:

GUI:
Follow the instructions provided in the GUI to display and save images. While using Bash Script in GUI, follow the below given instructions.

BASH:
1) Patterns: pattern <name of pattern> <additional arguments>
  a) Vertical rainbow: pattern vertical <width> <height>
  b) Horizontal rainbow: pattern horizontal <width> <height>
  c) Checkerboard: pattern checkerboard <size of inner square> <total squares per side>

2) Loaded images: load <image path> <list of operations> save <image path>
  Example: load image1.png blur sharpen blur save image1_blur.jpeg
  Operations: blur - blur the image
  	      sharpen - sharpen the image
	      greyscale - apply greyscale transformation
	      greyEnhance - applies greyscale and then histogram equalisation to improve contrast
 	      sepia - apply sepia transformation
	      edge - greyscale image with edge detection
	      dither - dithered image
	      mosaic <number> - divides the image into <number> tiles and averages each tile to create the effect
	      redeye <x1> <y1> <x2> <y2> - applies redeye removal to the area provided. Order of coordinates do not matter.
	      exit - exit without saving the image (cannot be saved later)

3) Flags: flag <flag type> <height>
  a) France: flag 1 <height>
  b) Norway: flag 2 <height>
  c) Greece: flag 3 <height>
  d) Switzerland: flag 4 <height>

4) Exit: exit (required)


----------------------------------------------------------------------

CHANGES in ImageModel.java

Added the new functionalities required for the Model.


CHANGES in ImageControllerBas.java

Replaced the return values to make it compatible with ImageControllerGUI


----------------------------------------------------------------------

Citations: Images, image1.png, image2.png, and image3.png, used in the application are owned by the author and have been authorised to be used in this project. image4.png and image5.png have been obtained from the demo images provided in the instructions of the assignment.

----------------------------------------------------------------------
