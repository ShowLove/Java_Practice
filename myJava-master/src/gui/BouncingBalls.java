package gui;

/*

 * BouncingBalls.java
 * A demonstration of animation using the Canvas.
 * 
 * 11/18/2013
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class BouncingBalls extends MouseAdapter implements ImpactListener {

	JFrame window;
	Canvas canvas;
	final int DURATION = 10000;

	List<Ball> ballList = new CopyOnWriteArrayList<Ball>();

	public BouncingBalls() {
		window = new JFrame("Bouncing balls");
		window.setSize(600, 400);
		window.setLocation(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout());

		canvas = new Canvas();
		canvas.setSize(550, 350);
		window.add(canvas);

		canvas.setBackground(Color.white);
		window.setVisible(true);
		for (int i = 0; i < 5; i++)
			addRandomBallToList();

		canvas.addMouseListener(this);

	}

	// Create a random ball at (225, 50).
	Ball randomBall() {
		return new Ball(225, 50, canvas.getWidth(), canvas.getHeight());
	}

	// Each mouse click will run the following
	// code.
	public void mouseClicked(MouseEvent e) {
		long startTime = System.currentTimeMillis();
		Graphics g = canvas.getGraphics();

		// Run for exactly 10 seconds. 
		while (System.currentTimeMillis() - startTime < DURATION) {
			
			// Animate each ball in the list.
			for (Ball b : ballList) {
				// Erase the ball from its current position.
				g.setColor(canvas.getBackground());
				g.fillOval((int) b.x, (int) b.y, b.RADIUS, b.RADIUS);

				// Move it to its new position.
				b.move();

				// Redraw it there.
				g.setColor(Color.red);
				g.fillOval((int) b.x, (int) b.y, b.RADIUS, b.RADIUS);
			}

			try {
				Thread.sleep(40);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new BouncingBalls();
	}

	// For each impact event we receive, we'll
	// add an additional ball.
	@Override
	public void onImpact() {
		addRandomBallToList();
	}

	// Put a random ball into the list of balls
	// to animate.
	public void addRandomBallToList() {
		Ball b = randomBall();
		// Register to be notified of bounce events
		// from this ball.
		b.setListener(this);
		ballList.add(b);
	}
}
