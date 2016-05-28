package code;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import contents.JEImage;

public class EdgeDetector {
	
	//The image to detect edges in.
	JEImage image;

	
	public EdgeDetector() {
		
	}
	
	public EdgeDetector(JEImage jei) {
		image = jei;
	}
	
	public EdgeDetector(BufferedImage img) {
		image = new JEImage(img);
	}
	
	
	public void setImage(JEImage jei) {
		image = jei;
	}
	
	public void setImage(BufferedImage img) {
		image = new JEImage(img);
	}
	
	
	/**
	 * Detects the edges in a BufferedImage. Threshold is the amount of difference to check for in terms of color value. Returns
	 * a HashMap that represents the coordinate points of all of the edges on the image (or everything but the edges).
	 * 
	 * @param displayImage -- Whether or not this method will create a JFrame to show the image once the edges have been detected.
	 * This is just so the user can see where the edges were found.
	 * @param threshold -- The amount of difference to check for between pixels.
	 * @return coordinates.
	 */
	public HashMap<Integer, Integer> detect(boolean displayImage, int threshold) {
		JEImage copyImg = image.clone();
		HashMap<Integer, Integer> coordinates = new HashMap<Integer, Integer>();
		
		for(int y = 1; y < image.getHeight() - 1; y++) {
			
			for(int x = 1; x < image.getWidth() - 1; x++) {
				
				//The different pixels to look at.
				int current = image.getRGB(x, y);
				int left = image.getRGB(x-1, y);
				int bottom = image.getRGB(x, y+1);
				int top = image.getRGB(x, y-1);
				int right = image.getRGB(x+1, y);
				
				
				/* Comment out one of the two lines of code below. The first one shows everything EXCEPT for the edges dark,
				 * and the second one makes ONLY the edges dark. */
				if(Math.abs(current - left) <= threshold || Math.abs(current - bottom) <= threshold 
				|| Math.abs(current - right) <= threshold || Math.abs(current - top) <= threshold) {
					
					//copyImg.setRGB(x, y, 0);
					//coordinates.put(x, y);
				
				} else {
					
					copyImg.setRGB(x, y, 0);
					coordinates.put(x, y);
					
				}
			}
		}
		
		if(displayImage) {
			//Make a JFrame to display the image 
			JFrame frame = new JFrame("Edge Detector");
			frame.setSize(400,400);
			frame.setLocationRelativeTo(null);
			JPanel p = new JPanel();
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(copyImg.getImage()));
			p.add(l);
			frame.add(p);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		return coordinates;
 	}
	
	
	
	/**
	 * Detects the edges in a BufferedImage. Threshold is the amount of difference to check for in terms of color value. Returns
	 * a BufferedImage with all of the edges outlined (or everything but the edges).
	 * 
	 * @param threshold -- The amount of difference to check for between pixels.
	 * @return coordinates.
	 */
	public BufferedImage Detect(boolean displayImage, int threshold) {
		JEImage copyImg = image.clone();
		
		for(int y = 1; y < image.getHeight() - 1; y++) {
			
			for(int x = 1; x < image.getWidth() - 1; x++) {
				
				//The different pixels to look at.
				int current = image.getRGB(x, y);
				int left = image.getRGB(x-1, y);
				int bottom = image.getRGB(x, y+1);
				int top = image.getRGB(x, y-1);
				int right = image.getRGB(x+1, y);
				
				
				/* Comment out one of the two lines of code below. The first one shows everything EXCEPT for the edges dark,
				 * and the second one makes ONLY the edges dark. */
				if(Math.abs(current - left) <= threshold || Math.abs(current - bottom) <= threshold 
				|| Math.abs(current - right) <= threshold || Math.abs(current - top) <= threshold) {
					
					//copyImg.setRGB(x, y, 0);
				
				} else {
					
					copyImg.setRGB(x, y, 0);
					
				}
			}
		}
		
		if(displayImage) {
			//Make a JFrame to display the image 
			JFrame frame = new JFrame("Edge Detector");
			frame.setSize(400,400);
			frame.setLocationRelativeTo(null);
			JPanel p = new JPanel();
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(copyImg.getImage()));
			p.add(l);
			frame.add(p);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		return copyImg.getImage();
 	}
	

} //End of class
