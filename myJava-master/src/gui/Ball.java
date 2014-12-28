package gui;
/*
 * Ball.java
 * A class that represents a circle that moves
 * under the influence of gravity.
 */
public class Ball {
	
	double x, y;
	double xvel, yvel;
	final int RADIUS = 20;
	final double GRAVITY = 1;
	int width, height;
	
	// The object to notify when a bounce
	// has occurred.
	ImpactListener listener;
	
	// Create a ball at (x, y) with random velocity.
	public Ball(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		
		xvel = Math.random() * 20 - 10;
		yvel = Math.random() * 20 - 10;
	}
	
	// Register a single object as a listener for 
	// bounces.
	public void setListener(ImpactListener listener) {
		this.listener = listener;
	}

	
	// Move the ball one time step.
	public void move() {
		// The position changes according to the velocity.
		// Note that velocity = distance moved per time step.
		x += xvel;
		y += yvel;
		
		// Gravity is a downward acceleration, so we increase
		// the velocity downward every second.
		yvel += GRAVITY;
		
		// We'll use the 'impact' variable to keep
		// track of whether a bounce has occurred or not.
		boolean impact = false;
		
		// If we hit the sides, reverse the velocity
		// and slow down a bit. This basically makes it
		// bounce back, and slowly reduce the bouncing over time.
		if (x <= 0 || x + RADIUS >= width) {
			xvel *= -0.9;
			impact = true;
		}
		if (y <= 0 || y + RADIUS >= height) {
			yvel *= -0.9;
			impact = true;
		}
		
		// If there is an impact, notify the listener
		// 1% of the time. We could just do it all the time,
		// but bounces happen pretty often.
		if (Math.random() < 0.1 && impact) {
			if (listener != null)
				listener.onImpact();
		}
	}

}
