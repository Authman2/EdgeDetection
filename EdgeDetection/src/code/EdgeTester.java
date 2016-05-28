package code;

import contents.JEImage;
import sun.applet.Main;

public class EdgeTester {

	public static void main(String[] args) {
		JEImage s = new JEImage(Main.class, "/images/clouds2.jpg");
		
		EdgeDetector t = new EdgeDetector(s.getImage());
		
		t.detect(100000);
	}

}
