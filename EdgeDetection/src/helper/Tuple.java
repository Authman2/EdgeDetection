package helper;

/** A generic class used for plotting coordinate points. X and Y are the x and y coordinates to be plotted. K is the 
 * key that can be used to keep track of which coordinate is being looked at. */
public class Tuple<X, Y, K> {

	public X x;
	public Y y;
	public K key;
	
	
	public Tuple(X x, Y y, K key) {
		this.x = x;
		this.y = y;
		this.key = key;
	}
	
	public X getX() { return x; }
	
	public Y getY() { return y; }
	
	public K getKey() { return key; }
}
