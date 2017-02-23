package code;
import je.visual.JEImage;
/**@author adeolauthman*/
import sun.applet.Main;

public class EdgeTester {

	public static void main(String[] args) {
		JEImage s = new JEImage(Main.class, "/images/10.png");
		
		EdgeDetector t = new EdgeDetector(s.getImage());
		
		t.detect(true, 150000);
	}

}
