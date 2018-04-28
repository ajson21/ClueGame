package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener that closes incorrect accusation GUIs
 * 
 * @author ajson
 *
 */
public class IncorrectAccusationSubmitButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		HumanIncorrectAccusation.mainFrame.dispose();
		ComputerIncorrectAccusation.mainFrame.dispose();
		
	}
	
}
