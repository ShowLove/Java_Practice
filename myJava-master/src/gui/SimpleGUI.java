package gui;

/*

 * SimpleGUI.java
 * Building a simple graphical user interface with
 * Swing and adding some event handlers.
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


public class SimpleGUI {

	// Each of these objects represents some element of the GUI.
	JFrame window;					// The main window.
	JLabel label; 					// Displays a piece of text.
	JButton button;					// A button.
	JTextField textField;			// A single-line text box that you can type text into.
	
	public SimpleGUI() {
		// Create a JFrame with a given title.
		window = new JFrame("Hello World");
		// Make sure that clicking the close button actually exits the program.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Place the window roughly in the middle of the screen. This really
		// depends on your screen resolution, so you may want to adjust it.
		window.setLocation(500, 300);
		
		// Use a FlowLayout to place the components from left to right, wrapping
		// to the next line if it runs out of space.
		window.setLayout(new FlowLayout());

		// Create a label with some text and add it to the frame.
		label = new JLabel("I'm a label");
		window.add(label);
		
		// Create a button.
		button = new JButton("Click me");
		// Create an event listener for the button.
		ClickCounter counter = new ClickCounter();
		// Register the listener with the button. This will
		// cause all ActionEvents generated by clicking the button
		// to be sent to counter, by invoking counter.actionPerformed(theEvent);
		// In this way, the ClickCounter class will respond to the button click.
		button.addActionListener(counter);
		
		// Don't forget to add the button to the window.
		window.add(button);
		
		// Add a text field to the window.
		textField = new JTextField("Enter some text here");
		textField.setColumns(20);
		window.add(textField);
		
		// Create a menu bar, and add a menu to it.
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		// Obviously the menu needs menu items, so we make some of those.
		JMenuItem generateItem = new JMenuItem("Generate");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		// Clicking the exit button should close the window.
		// Instead of writing yet another inner class like ClickCounter,
		// we'll make an *anonymous inner class* that implements 
		// ActionListener. The reason it's called anonymous is that 
		// we never give it a class name. We implement ActionListener and
		// instantiate it in one move, and store the resulting object
		// in an ActionListener variable.
		ActionListener exitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				System.exit(0);
			}
		};
		// Register the listener with the menu item.
		exitItem.addActionListener(exitListener);
		
		// Similar technique to create an anonymous inner class.
		// Only this time, I create the listener object in the
		// arguments to addActionListener() itself. No need to
		// store it in a variable, we're only using it once.
		generateItem.addActionListener(new ActionListener() {
			String[] phrases = {
					"Tally ho!", 
					"Geronimo!", 
					"Allons-y!", 
					"Fantastic!"
			};
			int idx = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cycle through our array of phrases and 
				// set the contents of the text field to the
				// corresponding phrase.
				idx = (idx + 1) % phrases.length;
				textField.setText(phrases[idx]);
			}
		});
		
		
		// Put the menu items into the menu.
		fileMenu.add(generateItem);
		fileMenu.add(exitItem);
		
		// Ask the window to use our menu bar object.
		window.setJMenuBar(menuBar);
		
		// Fix a size for the window. Alternatively, comment the line out 
		// and use window.pack(), which will scrunch the window up to use the
		// the minimum amount of space possible. This is good if you're using 
		// a sophisticated layout manager, but FlowLayout is too simple to make
		// this look good.
		window.setSize(250, 150);
		
		// Finally, the most important bit. Make sure the user can actually
		// see your window. Not much use having an invisible one.
		window.setVisible(true);
	}
	
	// This is an inner class - a class defined inside
	// a class.
	// It's full name is SimpleGUI.ClickCounter, as it is 
	// a member of SimpleGUI. What makes this cool and useful
	// is that it can access the members of SimpleGUI, even
	// the private ones. Just like it were part of the class itself,
	// which is pretty true.
	// 
	// ClickCounter is an event listener that handles ActionEvents.
	// It counts the number of times it was invoked (i.e., the button
	// was clicked), and updates the text of the label accordingly.
	class ClickCounter implements ActionListener {

		private int count = 0;
		@Override
		public void actionPerformed(ActionEvent evt) {
			count++;
			String message = "You clicked " + count + " times.";
			label.setText(message);
		}
	}
	
	public static void main(String[] args) {
		// Create the object and let it run.
		new SimpleGUI();
	}

}
