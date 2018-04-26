package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import clueGame.ControlPanelGUI.DetectiveNotesListener;

/**
 * Main driver for clue game. Has board, control panel, and card panel JPanels in one main JFrame
 * 
 * 
 * @author ajson, jasonyu
 *
 *
 *
 */
public class clueGame {

	
	public static void main(String[] args) {
		
		Board board;
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");		
		board.initialize();
		
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue");
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem exitItem, notesItem;
		
		JMenu fileMenu = new JMenu("File");
		exitItem = new JMenuItem("Exit");
		notesItem = new JMenuItem("Notes");
		
		fileMenu.add(exitItem);
		fileMenu.add(notesItem);
		
		menuBar.add(fileMenu);
		
		notesItem.addActionListener(new DetectiveNotesListener());
		
		
		frame.setJMenuBar(menuBar);

		frame.setSize(850, 850);
		
		ControlPanelGUI gui = new ControlPanelGUI();
		frame.add(gui, BorderLayout.SOUTH);

		
		
		CardGUI cardGUI = new CardGUI();
		frame.add(cardGUI, BorderLayout.EAST);
		
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JOptionPane.showMessageDialog(null, "You are Miss Scarlett. Press Next Player to begin game.", "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE); 
		
		
	}
	
	
	
}
