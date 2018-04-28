package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button Listener to exit program when exit menu button is pressed
 * 
 * @author ajson, jasonyu
 *
 */
public class ExitListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.exit(0);

	}

}