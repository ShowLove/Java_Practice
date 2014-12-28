package gui;

/*

 * DotFountain.java
 * A demonstration of multithreaded GUI code.
 * 
 * 11/18/2013
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DotFountain extends MouseAdapter {

	JFrame window;
	Canvas canvas;
	
	public DotFountain() {
		// The usual boilerplate.
		window = new JFrame("Dot Fountain");
		window.setSize(600, 400);
		window.setLocation(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout());

		// Set up the canvas.
		canvas = new Canvas();
		canvas.setSize(550, 350);
		
		// Dots need to know canvas dimensions 
		// so they'll know when to bounce.
		Dot.setCanvasSize(550, 350);
		
		window.add(canvas);

		canvas.setBackground(Color.white);
		window.setVisible(true);

		// Register a mouse listener so the canvas will
		// respond to click events.
		canvas.addMouseListener(this);
	}
	
	class Fountain extends Thread {
		// The list of dots to display.
		// We could just as easily use an array here.
		List<Dot> dotList;
		final int NUM_DOTS = 10;
		

		// Create a fountain of dots at (x, y).
		public Fountain(int x, int y) {
			super();
			// Fill up the list of dots.
			// Dots have random velocities, so they'll 
			// just erupt from the point we clicked in random
			// directions.
			dotList = new ArrayList<Dot>();
			for (int i = 0; i < NUM_DOTS; i++)
				dotList.add(new Dot(x, y));
		}
		
		@Override
		public void run() {
			Graphics g = canvas.getGraphics();
			// Run an infinite erase-move-redraw loop.
			while (true) {
				// Run through all the dots and erase-move-redraw each one.
				for (Dot b : dotList) {
					// Erase.
					g.setColor(canvas.getBackground());
					g.fillOval((int) b.x, (int) b.y, b.RADIUS, b.RADIUS);

					// Move
					b.move();

					// Redraw.
					g.setColor(b.color);
					g.fillOval((int) b.x, (int) b.y, b.RADIUS, b.RADIUS);
				}

				// Pause briefly between each redraw cycle.
				try {
					Thread.sleep(40);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Have each mouse click create a new burst of dots.
	 * Each fountain is a thread of its own, so it doesn't
	 * block the event-handling thread.
	 * 
	 * In the old BouncingBall example, the animation code
	 * executes on the event-handling thread. Since it's slow (it runs
	 * for 10 seconds), it visibly slows down all other event-handling.
	 * For example, if you click the Close button while the balls are
	 * bouncing, the program won't close until the animation finishes.
	 * 
	 * In this program, we avoid such problems, since the Fountain is
	 * a separate thread, and doesn't interfere with the standard processing
	 * of events.
	 */
	public void mouseClicked(MouseEvent e) {
		Fountain f = new Fountain(e.getX(), e.getY());
		f.start();
	}

	public static void main(String[] args) {
		new DotFountain();
	}
}
