package code;

import java.awt.Color;
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
	
	/** Blurs the image using a Gaussian blur. */
	private BufferedImage blur(JEImage img) {
		JEImage copy = img.clone();
		
		for(int i = 1; i < copy.getWidth() - 1; i++) {
			for(int j = 1; j < copy.getHeight() - 1; j++) {
				Color p1 = new Color(copy.getRGB(i-1, j));
				Color p2 = new Color(copy.getRGB(i+1, j));
				Color p3 = new Color(copy.getRGB(i, j-1));
				Color p4 = new Color(copy.getRGB(i, j+1));
				Color original = new Color(copy.getRGB(i, j));
				
				int avgRed = (p1.getRed() + p2.getRed() + p3.getRed() + p4.getRed()) / 4;
                int avgGreen = (p1.getGreen() + p2.getGreen() + p3.getGreen() + p4.getGreen()) / 4;
                int avgBlue = (p1.getBlue() + p2.getBlue() + p3.getBlue() + p4.getBlue()) / 4;
                
                original = new Color(avgRed, avgGreen, avgBlue);
                copy.setRGB(i, j, original.getRGB());
			}
		}		
		
		return copy.getImage();
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
		
		//Blur the image to help remove noise.
		copyImg.setImage(blur(copyImg));
		
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
		
		//Blur the image to help remove noise.
		copyImg.setImage(blur(copyImg));
		
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
