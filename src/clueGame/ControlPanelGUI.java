package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * 
 * JPanel for the Control Panel of clue.
 * Also contains logic for changing players due to inclusion of nextPlayerButton
 * 
 * @author ajson, jasonyu
 *
 */
public class ControlPanelGUI extends JPanel {

	public static JTextField dieRoll, currentPlayer, guessBox, guessResultBox;
	public static JButton nextPlayerButton, accusationButton;

	public ControlPanelGUI() {

		setLayout(new GridLayout(3, 2));
		JPanel panel = createControlPanel();
		add(panel);

	}

	private JPanel createControlPanel() {

		Board board;
		board = Board.getInstance();

		JPanel panel = new JPanel();

		nextPlayerButton = new JButton("Next player");
		nextPlayerButton.addActionListener(new NextPlayerButtonListener());
		accusationButton = new JButton("Make an accusation");
		accusationButton.addActionListener(new AccusationButtonListener());

		JPanel turnPanel = new JPanel();
		turnPanel.setBorder(new TitledBorder(new EtchedBorder(), "Whose Turn?"));
		currentPlayer = new JTextField("                                        ");
		currentPlayer.setEditable(false);
		turnPanel.add(currentPlayer);

		JPanel dieRollPanel = new JPanel();
		dieRollPanel.setBorder(new TitledBorder(new EtchedBorder(), "Die Roll"));
		dieRoll = new JTextField("                  ");
		dieRoll.setEditable(false);
		dieRollPanel.add(dieRoll);

		JPanel guessPanel = new JPanel();
		guessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		JLabel guess = new JLabel("Guess");
		guessPanel.add(guess);
		guessBox = new JTextField("                                                                        ");
		guessBox.setEditable(false);
		guessPanel.add(guessBox);

		JPanel guessResultPanel = new JPanel();
		guessResultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		JLabel response = new JLabel("Response");
		guessResultPanel.add(response);
		guessResultBox = new JTextField("                                    ");
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

	/**
	 * 
	 * Display for detective notes, opens if user clicks on menu item.
	 * 
	 * @author ajson, jasonyu
	 *
	 */
	public static class DetectiveNotesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DetectiveNotes notes = new DetectiveNotes();
			notes.displayGUI();

		}

	}

	/**
	 * AccusationButtonListener contains logic for making accusations.
	 * Only for human player
	 * 
	 * @author ajson
	 *
	 */
	public static class AccusationButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Board board;
			board = Board.getInstance();

			if (board.getCurrentPlayer() == 0) {

				nextPlayerButton.setEnabled(true);

				board.repaint();
				board.incrementPlayer();

			} else {

				JOptionPane.showMessageDialog(null, "      Error: It is not your turn!", "You dun goofed",
						JOptionPane.ERROR_MESSAGE);

			}

		}

	}

	/**
	 * 
	 * Next player logic class, handles most of the logic for playing clue, such
	 * as cycling players, correctly moving, and making guesses/accusations
	 * 
	 * @author ajson, jasonyu
	 *
	 */
	public static class NextPlayerButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Board board;
			board = Board.getInstance();

			if (board.getCurrentPlayer() == 0) {

				currentPlayer.setText(board.getPlayerList()[board.getCurrentPlayer()].getName());
				Random rand = new Random();
				int roll = rand.nextInt(6) + 1;
				dieRoll.setText(Integer.toString(roll));

				board.calcTargets(board.getPlayerList()[0].getRow(), board.getPlayerList()[0].getColumn(), roll);

				for (BoardCell target : board.getTargets()) {

					Graphics currentG = board.getGraphics();
					target.drawTarget(currentG, target.getColumn(), target.getRow());

				}

				nextPlayerButton.setEnabled(false);

			} else {

				currentPlayer.setText(board.getPlayerList()[board.getCurrentPlayer()].getName());

				guessBox.setText("");
				guessResultBox.setText("");

				ComputerPlayer current = (ComputerPlayer) board.getPlayerList()[board.getCurrentPlayer()];

				boolean madeAccusation = false;

				if (current.ableToMakeAccusation()) {

					ArrayList<Card> accusation = current.makeAccusation();
					boolean correct = board.checkAccusation(accusation);

					madeAccusation = true;

					if (correct) {

						

						// ADD IMPLEMENTATION AND DISPLAY

					} else {

						

					}

				}

				if (!madeAccusation) {

					Random rand = new Random();
					int roll = rand.nextInt(6) + 1;
					dieRoll.setText(Integer.toString(roll));

					board.calcTargets(board.getPlayerList()[board.getCurrentPlayer()].getRow(),
							board.getPlayerList()[board.getCurrentPlayer()].getColumn(), roll);

					Object[] targets = current.selectTargets(board.getTargets()).toArray();

					int randomTarget = rand.nextInt(targets.length);

					BoardCell target = (BoardCell) targets[randomTarget];

					current.setLocation(target.getRow(), (target.getColumn()));
					board.repaint();

					if (target.isDoorway()) {

						ArrayList<Card> suggestion = current.createSuggestion(target.getInitial());

						String guess = "";

						for (Card card : suggestion) {

							String partialGuess = card.getCardName();
							guess += partialGuess;
							guess += " ";

						}

						guessBox.setText(guess);

						Card result = board.handleSuggestion(suggestion, board.getCurrentPlayer());

						if (result == null) {

							guessResultBox.setText("No new clue");

						} else {

							guessResultBox.setText(result.getCardName());
							current.returnSuggestion(result);

						}

					}

				}

				board.repaint();
				board.incrementPlayer();

			}

		}

	}

}
