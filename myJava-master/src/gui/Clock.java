package gui;

/*
 * Clock.java
 * A clock that updates in real time using threads.
 */
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Clock {

	JFrame window;
	JLabel currentTime;
	
	public Clock() {
		// Boilerplate.
		window = new JFrame("Clock");
		
		window.setLocation(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout());
		
		// Create the label that will display the clock,
		// and give it a large font.
		currentTime = new JLabel();
		currentTime.setFont(new Font("Serif", Font.BOLD, 100));
		window.add(currentTime);
				
		// Create a thread to run the clock. We can 
		// pass the runnable we made to this thread.
		// This is the generally preferred way to 
		// create a thread.
		Thread t = new Thread(new ClockRunnable());
		// Start the thread's execution.
		t.start();
		
		// pack() shrinks the window down to the minimum
		// size needed to display everything.
		window.pack();
		window.setVisible(true);
	}
	
	// A Runnable represents a chunk of code
	// that can be run as a thread. The run()
	// method contains the code that will be
	// executed by the thread.
	class ClockRunnable implements Runnable {
		@Override
		public void run() {
			// Loop forever.
			while (true) {
				// Update the label with the current time.
				setCurrentTime();
				
				// Sleep for 1 second.
				try {
					Thread.sleep(1000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	// Use the Calendar class to get the current time.
	// Format it into HH:MM:SS format and display that
	// in the label.
	void setCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		String clockText = String.format("%02d:%02d:%02d", h, m, s);
		
		currentTime.setText(clockText);
	}
	

	public static void main(String[] args) {
		new Clock();

	}

}
