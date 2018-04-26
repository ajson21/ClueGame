package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;

/**
 * Generic cancel button used in suggestion box 
 * 
 * @author ajson, jasonyu
 * 
 *
 */
public class CancelButton implements ActionListener {
	CancelButton(){
		
	}

	public void actionPerformed(ActionEvent e) {
		HumanSuggestionGUI.mainFrame.dispose();

	}

}
