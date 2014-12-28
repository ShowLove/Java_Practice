package gui;

/*

 * CannedDialogs.java
 * A demonstration of canned dialogs using JOptionPane.
 */
import javax.swing.JOptionPane;

public class CannedDialogs {

	public static void main(String[] args) {
		// Prompt the user to decide whether to destroy the world or not, and
		// don't settle for anything but a hard decision.
		boolean decided = false;
		while (!decided) {
			decided = true;
			
			// The method returns an int representing the choice made
			// by the user.
			int choice = JOptionPane.showConfirmDialog(
					null,								// Parent component. 
					"Destroy the world?",				// Message to display.
					"Fate of the World",				// Window title.
					JOptionPane.YES_NO_CANCEL_OPTION);	// Which buttons to display.
			
			if (choice == JOptionPane.YES_OPTION) {
				// If they selected Yes, we use showInputDialog() to get the
				// name of the destroyer.
				String name = JOptionPane.showInputDialog(null,
							"Who should I say destroyed it?");
				String msg = String.format(
						"%s was responsible for the annihilation of all creation!", 
						name);
				// Display a message with the name.
				JOptionPane.showMessageDialog(null, msg);
				
				/*
				 * As an exercise, maybe add a confirmation dialog of the form
				 * 'Are you SURE you want to destroy the world, Mr So-and-so?' ;)
				 */
				
			} else if (choice == JOptionPane.NO_OPTION) {
				// If they selected NO, pop up a message expressing relief.
				JOptionPane.showMessageDialog(null, "Whew!");
			} else {
				// Otherwise, chide them for indecision and keep the loop going.
				JOptionPane.showMessageDialog(null, 
						"This is a terrible time to be indecisive");
				decided = false;
			}
		}
		
		
		// If we use showOptionDialog(), we can modify a lot of things, such
		// as the icon displayed, the text of the buttons, and so on.
		String[] pills = new String[] {"Red", "Blue" };
		int choice = JOptionPane.showOptionDialog(null, 		// Parent component
				"Do you choose the red pill or the blue pill?", // Message 
				"Morpheus pretends to be profound",				// Title
				JOptionPane.YES_NO_OPTION, 		// Pretend to be a YES/NO choice, even if the text is different.
				JOptionPane.QUESTION_MESSAGE, 	// Type of message.
				null,							// Icon to display, null since we don't have one.
				pills,							// Array containing the text of the buttons.
				pills[0]);						// The button text selected by default.
		
		// Display a message depending on the pill chosen.
		if (choice == JOptionPane.YES_OPTION) {
		JOptionPane.showMessageDialog(null, "You picked the red pill");
		} else {
			JOptionPane.showMessageDialog(null, "You picked the blue pill");
		}
		
	}

}
