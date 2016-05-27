package code;

public class Pixel {

	@SuppressWarnings("unused")
	private int red, green, blue, average;
	
	
	public Pixel() {}
	public Pixel(int average) { this.average = average; }
	
	
	public void setRed(int r) { red = r; }
	public void setGreen(int g) { green = g; }
	public void setBlue(int b) { blue = b; }
	public void setAverage() { average = (red+green+blue)/3; }
	
	public int getRed() { return red; }
	public int getGreen() { return green; }
	public int getBlue() { return blue; }
	public int getAverage() { return (red+green+blue)/3; }
	
}
