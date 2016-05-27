package code;

import java.awt.image.BufferedImage;

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
	
	
	/**
	 * Detects the edges in a BufferedImage. Threshold is the amount of difference to check for in terms of pixel value.
	 */
	public void detect(int threshold) {
		JEImage copyImg = image.clone();
		
		for(int y = 1; y < image.getHeight() - 1; y++) {
			
			for(int x = 1; x < image.getWidth() - 1; x++) {
				
				int current = image.getRGB(x, y);
				int left = image.getRGB(x-1, y);
				int bottom = image.getRGB(x, y+1);
				
				int newPix = copyImg.getRGB(x, y);
				
				if(Math.abs(current - left) <= threshold || Math.abs(current - bottom) <= threshold) {
					newPix = 0;
					copyImg.setRGB(x, y, newPix);
				}
			}
		}
		
		//Make a JFrame to display image 
		JFrame frame = new JFrame("Frame");
		frame.setSize(300,300);
		frame.setLocationRelativeTo(null);
		JPanel p = new JPanel();
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(copyImg.getImage()));
		p.add(l);
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
 	}
	
	

} //End of class
