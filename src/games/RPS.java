package games;

import custom.CustomFrame;
import custom.Log;
import global.Const;
import global.Shared;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// 0 -> rock
// 1 -> paper
// 2 -> scissors

class RPS {
	private JLabel playerName, playerScore, botName, botScore;
	private JButton rockBtn, paperBtn, scissorsBtn;
	private JPanel textPanel, buttonPanel, mainPanel;
	private JFrame frame;
	private GridBagConstraints gbc;
	private GamesMenuBar menuBar;

	private Image rockImg, paperImg, scissorsImg;
	private Dimension rockSize, paperSize, scissorsSize;
	private Random r;

	private int playerScoreValue, botScoreValue, gameMode = 1, botChoice, playerChoice;

	protected RPS () {
		initVariables();
		initComponents();
	}

	// used to generate the bot's choice
	// uses simple RNG
	private void generateBotChoice () {
		botChoice = r.nextInt(3); // 0 - 2
	}

	// called when the user presses one of the buttons
	// generates the bot's response and checks for a win, loss or tie
	// win -> +1 player
	// loss -> +1 bot
	// tie -> +1 player, +1 bot
	private void buttonTriggered (ActionEvent event) {
		generateBotChoice();
		Log.i(getClass(), "playerChoice: " + playerChoice);
		Log.i(getClass(), "botChoice: " + botChoice);

		// check results
		if ((playerChoice == 0 && botChoice == 1) ||
				(playerChoice == 1 && botChoice == 2) ||
				(playerChoice == 2 && botChoice == 0)) {
			// player loss
			Log.i(getClass(), "Loss");

			// update botScoreValue and botScore
			botScore.setText(String.valueOf(++botScoreValue));

			// update Shared.botScore
			Shared.setBotScore(botScoreValue);
		} else if ((playerChoice == 0 && botChoice == 2) ||
				(playerChoice == 1 && botChoice == 0) ||
				(playerChoice == 2 && botChoice == 1)) {
			// player win
			Log.i(getClass(), "Win");

			// update botScoreValue and botScore
			playerScore.setText(String.valueOf(++playerScoreValue));

			// update Shared.playerScore
			Shared.setPlayerScore(playerScoreValue);
		} else {
			// tie
			// if tie, both scores will increase
			Log.i(getClass(), "Tie");
			playerScore.setText(String.valueOf(++playerScoreValue));
			Shared.setPlayerScore(playerScoreValue);
			botScore.setText(String.valueOf(++botScoreValue));
			Shared.setBotScore(botScoreValue);
		}
	}

	// reset all values to 0
	// prevent any overriding of scores
	private void initVariables () {
		r = new Random();
		playerScoreValue = 0;
		botScoreValue = 0;
		Shared.setPlayerScore(0);
		Shared.setBotScore(0);
		Log.i(getClass(), "playerScore: " + Shared.getPlayerScore());
		Log.i(getClass(), "botScore: " + Shared.getBotScore());
	}

	private void initComponents () {
		// initialize frame
		frame = new CustomFrame(
				"Rock-Paper-Scissors", Const.RPS_WIDTH, Const.RPS_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// add menuBar
		menuBar = new GamesMenuBar(frame, gameMode);

		// initialize all components
		// mainPanel
		mainPanel = new JPanel();
		// uses BoxLayout to ensure that the top and bottom panels are evenly spaced
		mainPanel.setLayout(new BoxLayout(
				mainPanel, BoxLayout.Y_AXIS
		));
		mainPanel.setBorder(Const.WINDOW_PADDING); // this is for window padding
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		// textPanel
		textPanel = new JPanel();
		textPanel.setLayout(new GridBagLayout());
		mainPanel.add(textPanel);

		// buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		mainPanel.add(buttonPanel);

		// playerName
		playerName = new JLabel(Shared.getUserName());
		playerName.setFont(Const.GAME_FONT);

		// playerScore
		playerScore = new JLabel(String.valueOf(playerScoreValue));
		playerScore.setFont(Const.GAME_FONT);

		// botName
		botName = new JLabel(Shared.getBotName());
		botName.setFont(Const.GAME_FONT);

		// botScore
		botScore = new JLabel(String.valueOf(botScoreValue));
		botScore.setFont(Const.GAME_FONT);

		// rockBtn
		rockBtn = new JButton();
		rockBtn.setFont(Const.GAME_BUTTON_FONT);

		// paperBtn
		paperBtn = new JButton();
		paperBtn.setFont(Const.GAME_BUTTON_FONT);

		// scissorsBtn
		scissorsBtn = new JButton();
		scissorsBtn.setFont(Const.GAME_BUTTON_FONT);

		// adding button icons
		try {
			rockImg = ImageIO.read(
					new File("assets/images/rock_64.png")
			);
			paperImg = ImageIO.read(
					new File("assets/images/paper_64.png")
			);
			scissorsImg = ImageIO.read(
					new File("assets/images/scissors_64.png")
			);

			rockBtn.setIcon(new ImageIcon(rockImg));
			paperBtn.setIcon(new ImageIcon(paperImg));
			scissorsBtn.setIcon(new ImageIcon(scissorsImg));
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to load image");
		}

		// resizing components
		// sets the width of all buttons to be the same
		rockSize = rockBtn.getPreferredSize();
		paperSize = paperBtn.getPreferredSize();
		scissorsSize = scissorsBtn.getPreferredSize();

		rockSize.width = scissorsSize.width;
		paperSize.width = scissorsSize.width;

		rockBtn.setPreferredSize(
				new Dimension(rockSize.width, rockSize.height)
		);

		paperBtn.setPreferredSize(
				new Dimension(paperSize.width, rockSize.height)
		);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints"

		// adding playerName to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		textPanel.add(playerName, gbc);

		// adding botName to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		textPanel.add(botName, gbc);

		// adding playerScore to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		textPanel.add(playerScore, gbc);

		// adding botScore to textPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		textPanel.add(botScore, gbc);

		// adding rockBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(rockBtn, gbc);

		// adding paperBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(paperBtn, gbc);

		// adding scissorsBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(scissorsBtn, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners"

		// all 3 buttons share the same method to respond to press
		// within all actionListeners, the playerChoice is assigned
		rockBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				playerChoice = 0;
				buttonTriggered(e);
			}
		});
		paperBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				playerChoice = 1;
				buttonTriggered(e);
			}
		});
		scissorsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				playerChoice = 2;
				buttonTriggered(e);
			}
		});

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
