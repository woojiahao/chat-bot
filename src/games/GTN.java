package games;

import custom.CustomFrame;
import custom.Log;
import custom.NumberTextField;
import global.Const;
import global.Shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

class GTN {
	private JLabel tries, triesScore, range, rangeText;
	private JButton guessBtn;
	private NumberTextField input;
	private JFrame frame;
	private JPanel mainPanel, textPanel, guessPanel;
	private GridBagConstraints gbc;
	private GamesMenuBar menuBar;

	private Dimension guessSize, inputSize;

	private int triesCounter, rangeValue, answer, guessValue, gameMode = 0;

	protected GTN () {
		// display the range selector before anything else
		// ensures that the user enters the range before starting the game
		new RangeSelectorDialog(new JFrame(), "Range Selection");
		initVariables();
		initComponents();
	}

	// <editor-fold defaultstate="collapsed" desc="Event Handlers"

	// when the user presses the guess button
	private void guessPressed (ActionEvent event) {
		String inputText = "";

		// keep checking for valid number
		do {
			inputText = input.getText();

			// checking for empty input
			if (!inputText.trim().equals("")) {
				// if the input is not empty
				if (input.containsLetter()) {
					// check if the input contains numbers
					JOptionPane.showMessageDialog(
							null, "Invalid input\nTry again!",
							"Invalid input", JOptionPane.ERROR_MESSAGE
					);
					// break out of do-while loop
					break; // prevent the message dialog from popping up repeatedly
				}
			} else {
				// if the input is empty
				JOptionPane.showMessageDialog(
						null, "Please enter your guess",
						"Invalid guess", JOptionPane.ERROR_MESSAGE
				);
				// break out of do-while loop
				break; // prevent the message dialog from popping up repeatedly
			}
		} while (input.containsLetter());

		// this prevents invalid input from being parsed into an integer
		try {
			guessValue = Integer.parseInt(inputText);
		} catch (NumberFormatException ob) {
			Log.i(getClass(), "Input is not valid");
			return;
		}
		Log.i(getClass(), "Guess: " + guessValue);

		// check if higher or lower
		if (guessValue > answer) {
			// if guess is higher than answer
			JOptionPane.showMessageDialog(
					null, "Too high!\nTry again!"
			);
			input.setText(""); // reset text
			triesScore.setText(String.valueOf(++triesCounter)); // increase triesCounter
			Shared.setPlayerScore(triesCounter); // increment the score
		} else if (guessValue < answer) {
			// if guess is lower than answer
			JOptionPane.showMessageDialog(
					null, "Too low!\nTry again!"
			);
			input.setText(""); // reset text
			triesScore.setText(String.valueOf(++triesCounter)); // increase triesCounter
			Shared.setPlayerScore(triesCounter); // increment the score
		} else {
			// if guess == answer
			input.setText(""); // reset text
			triesScore.setText(String.valueOf(++triesCounter)); // increase triesCounter
			Shared.setPlayerScore(triesCounter); // increment the score
			CongratulationsDialog cd = new CongratulationsDialog(
					new JFrame(), frame, gameMode
			);
		}
	}

	// </editor-fold>

	// initializing all variables
	// resets all scores to 0
	// gets the input from RangeSelectorDialog
	private void initVariables () {
		Shared.setPlayerScore(0);
		triesCounter = 0;
		rangeValue = RangeSelectorDialog.getRange();
		answer = new Random().nextInt(rangeValue + 1);
		Log.i(getClass(), "playerScore: " + Shared.getPlayerScore());
		Log.i(getClass(), "answer: " + answer);
	}

	// initializing the window
	private void initComponents () {
		// initialize frame
		frame = new CustomFrame(
				"Guess the number", Const.GTN_WIDTH, Const.GTN_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// adding menuBar
		menuBar = new GamesMenuBar(frame, gameMode);

		// initialize all components
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING); // this adds window padding
		// use BoxLayout to allow the top and bottom panels to be evenly spaced
		mainPanel.setLayout(new BoxLayout(
				mainPanel, BoxLayout.Y_AXIS
		));

		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		// textPanel
		textPanel = new JPanel();
		textPanel.setLayout(new GridBagLayout());
		mainPanel.add(textPanel);

		// guessPanel
		guessPanel = new JPanel();
		guessPanel.setLayout(new GridBagLayout());
		mainPanel.add(guessPanel);

		// tries
		tries = new JLabel("Tries");
		tries.setFont(Const.GAME_FONT);

		// triesScore
		triesScore = new JLabel(String.valueOf(triesCounter));
		triesScore.setFont(Const.GAME_FONT);

		// range
		range = new JLabel("Max Possible");
		range.setFont(Const.GAME_FONT);

		// rangeText
		rangeText = new JLabel(String.valueOf(rangeValue));
		rangeText.setFont(Const.GAME_FONT);

		// input
		input = new NumberTextField();
		input.setFont(Const.TEXT_FONT);

		// guessBtn
		guessBtn = new JButton("Guess");
		guessBtn.setFont(Const.BUTTON_FONT);

		// resizing components
		// resize input to be the same height as send
		guessSize = guessBtn.getPreferredSize();
		inputSize = input.getPreferredSize();
		guessSize.height = inputSize.height;
		guessBtn.setPreferredSize(
				new Dimension(guessSize.width, guessSize.height)
		);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// adding tries to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		textPanel.add(tries, gbc);

		// adding triesScore to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		textPanel.add(triesScore, gbc);

		// adding range to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		textPanel.add(range, gbc);

		// adding rangeText to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		textPanel.add(rangeText, gbc);

		// adding input to guessPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		guessPanel.add(input, gbc);

		// adding guessBtn to guessPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		guessPanel.add(guessBtn, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		guessBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				guessPressed(e);
			}
		});

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
