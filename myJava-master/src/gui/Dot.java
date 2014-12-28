package gui;

/*

 * Dot.java
 * A class that represents a circle that moves
 * under the influence of gravity.
 */
import java.awt.Color;

public class Dot {
	double x, y;
	double xvel, yvel;
	Color color;
	final int RADIUS = 20;
	final double GRAVITY = 1;
	static int width, height;	
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
		
		// Initial velocity is random.
		xvel = Math.random() * 20 - 10;
		yvel = Math.random() * 20 - 10;
		
		// Choose a random color.
		color = new Color(random(), random(), random());
	}
	
	static int random() { return (int) (Math.random() * 256); }
	
	static void setCanvasSize(int w, int h) {
		width = w;
		height = h;
	}
	
	// Move the ball for one time step.
	public void move() {
		// The position changes according to the velocity.
		// Note that velocity = distance moved per time step.
		x += xvel;
		y += yvel;
		
		// Gravity is a downward acceleration, so we increase
		// the velocity downward every second.
		yvel += GRAVITY;
		
		// If we hit the sides, reverse the velocity
		// and slow down a bit. This basically makes it
		// bounce back, and slowly reduce the bouncing over time.
		if (x <= 0 || x + RADIUS >= width) {
			xvel *= -0.9;
		}
		if (y <= 0 || y + RADIUS >= height) {
			yvel *= -0.9;
		}
	}
}
