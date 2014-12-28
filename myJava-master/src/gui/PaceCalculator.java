package gui;

/*

 * PaceCalculator.java
 * 11/4/2013
 * 
 * Uses GUIs to compute the time it takes to run different kinds of races.
 */
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class PaceCalculator implements ActionListener, KeyListener {
	
	/*
	 * Each entry in this enum represents one kind of race.
	 */
	enum Race {
		FIVE_K("5K", 3.1),
		TEN_K("10K", 6.2),
		HALF_MARATHON("1/2 Marathon", 13.1),
		MARATHON("Marathon", 26.2),
		FIFTY_K("50K", 31);
		
		String description;	// Give each race a descriptive piece of text as a name.
		double length;		// It makes sense to encapsulate the race length in the corresponding enum.
		
		// Constructor, so we can initialize the fields.
		Race(String description, double length) {
			this.description = description;
			this.length = length;
		}
		
		// Accessor for the length.
		public double getLength() {
			return length;
		}
		
		// Return the nice description rather than the default alternative (enum variable name).
		@Override
		public String toString() {
			return description;
		}
	}
	
	JComboBox raceBox;
	JLabel result;
	JTextField minutes;
	JTextField seconds;
	
	public PaceCalculator() {
		// The usual yada-yada.
		JFrame window = new JFrame("Pace Calculator");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(300, 300);
		window.setLayout(new FlowLayout());
		window.setLocation(300, 300);
		
		/*
		 * We'll organize our components into two panels. The first one
		 * will contain all the input components (text fields and combo box), 
		 * while the other will contain the output components (a label, basically).
		 */
		JPanel inputPanel = new JPanel();
		minutes = new JTextField(3);
		inputPanel.add(minutes);
		inputPanel.add(new JLabel("mins"));
		
		minutes.addKeyListener(this);
		
		seconds = new JTextField(3);
		inputPanel.add(seconds);
		inputPanel.add(new JLabel("secs"));
		seconds.addKeyListener(this);
				
		// We'll create a combo box (drop-down list) using a 
		// constructor that takes an array of objects (the list elements).
		// In this case we'll just pass it an array containing all the enums.
		raceBox = new JComboBox(Race.values());
		
		// Set a titled border around the combo box. 
		raceBox.setBorder(BorderFactory.createTitledBorder("Race"));
		
		// The PaceCalculator class itself implements ActionListener,
		// so we'll just use this object as the action listener.
		raceBox.addActionListener(this);
		
		inputPanel.add(raceBox);
		
		// Put a border around the input panel.
		Border inputBorder = BorderFactory.createTitledBorder("Enter pace:");
		inputPanel.setBorder(inputBorder);
		
		// Add the result label to the other panel
		JPanel outputPanel = new JPanel();
		result = new JLabel("1:35:00");			// Some random text at the start.
		// Make the label text large and bold.
		result.setFont(new Font("Serif", Font.BOLD, 50));
		outputPanel.add(result);
		
		// Border around the output panel.
		outputPanel.setBorder(BorderFactory.createTitledBorder("Time estimate"));
		
		// Add both panels to the main window and make it visible.
		window.add(inputPanel);
		window.add(outputPanel);
		window.setVisible(true);
	}
	
	/*
	 * Reads the contents of a text field and converts them to
	 * a String. If the field is empty, it treats it as a 00, and 
	 * puts that in the text field.
	 * 
	 * If we're editing the text field, it'll still behave the same
	 * but without putting 00 in the field if it goes empty.
	 */
	int getContents(JTextField tf) {
		String contents = tf.getText();
		if (contents == null || contents.isEmpty()) {
			
			if (!tf.hasFocus()) {
				tf.setText("00");
			}
			return 0;
		}
		return Integer.parseInt(contents);
	}
	
	/*
	 * Convert pace to seconds.
	 */
	int getPaceSeconds() {
		int m = getContents(minutes);
		int s = getContents(seconds);
		return 60*m  +s;
	}
	
	/*
	 * Computes the time to run the selected race with the given pace.
	 */
	int getRaceTime() {
		int pace = getPaceSeconds();
		Race selectedRace = (Race)raceBox.getSelectedItem();
		double raceLength = selectedRace.getLength();
		
		int totalTime = (int)(raceLength * pace);
		return totalTime;
	}
	
	/*
	 * Fomat the time into <hours>:<minutes>:<seconds>
	 */
	String formatTime(int time) {
		int h = time / 3600;
		time %= 3600;
		int m = time / 60;
		time %= 60;
		int s = time;
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	public static void main(String[] args) {
		new PaceCalculator();
	}

	
	/*
	 * Handle the ActionEvent generated by selecting a 
	 * particular entry from the drop down list.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		result.setText(formatTime(getRaceTime()));
	}

	
	/*
	 * These three methods are needed since we're
	 * implementing KeyListener. Since we only
	 * care about one of them, we'll leave the other two blank.
	 */
	
	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Just invoke the ActionListener, since we
		// want to do the exact same thing it does anyway.
		actionPerformed(null);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
