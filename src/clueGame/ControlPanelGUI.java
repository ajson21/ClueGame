package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanelGUI extends JPanel {
	
	public ControlPanelGUI(){
		
		setLayout(new GridLayout(3,2));
		JPanel panel = createControlPanel();
		add(panel);
		
	}
	
	private JPanel createControlPanel(){
		
		JPanel panel = new JPanel();

		JButton nextPlayerButton = new JButton("Next player");
		JButton accusationButton = new JButton("Make an accusation");	
		
		JPanel turnPanel = new JPanel();
		turnPanel.setBorder(new TitledBorder (new EtchedBorder(), "Whose Turn?"));
		JTextField currentPlayer = new JTextField("Current Player");
		currentPlayer.setEditable(false);
		turnPanel.add(currentPlayer);
		
		JPanel dieRollPanel = new JPanel();
		dieRollPanel.setBorder(new TitledBorder (new EtchedBorder(), "Die Roll"));
		JTextField dieRoll = new JTextField("Die roll");
		dieRoll.setEditable(false);
		dieRollPanel.add(dieRoll);
		
		JPanel guessPanel = new JPanel();
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		JLabel guess = new JLabel("Guess");
		guessPanel.add(guess);
		JTextField guessBox = new JTextField("Guess text box");
		guessBox.setEditable(false);
		guessPanel.add(guessBox);
		
		JPanel guessResultPanel = new JPanel();
		guessResultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		JLabel response = new JLabel("Response");
		guessResultPanel.add(response);
		JTextField guessResultBox = new JTextField("Guess result text box");
		guessResultBox.setEditable(false);
		guessResultPanel.add(guessResultBox);

		
		add(turnPanel);
		add(nextPlayerButton);
		add(accusationButton);
		add(dieRollPanel);
		add(guessPanel);
		add(guessResultPanel);
		
		return panel;
		
		
	}
	
	public static void main(String[] args) {
		
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
		
		frame.setJMenuBar(menuBar);

		frame.setSize(1000, 800);
		
		ControlPanelGUI gui = new ControlPanelGUI();
		frame.add(gui, BorderLayout.SOUTH);

		Board board;
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");		
		board.initialize();
		
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
