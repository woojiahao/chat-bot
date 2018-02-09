package games;

import custom.Log;
import global.Const;
import global.Shared;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// used when the user ends a game
class CongratulationsDialog extends JDialog {
	private JPanel mainPanel;
	private JLabel congratsText, scoreText, playAgainText, temp;
	private JButton yes, no;
	private GridBagConstraints gbc;
	private JFrame previousFrame;

	private BufferedImage img;
	private File file;
	private AudioInputStream audioStream;
	private Clip clip;

	private int gameMode;

	// <editor-fold defaultstate="collapsed" desc="Event Listeners">

	// when the user selects yes
	// dispose CongratulationsDialog
	// dispose previousFrame
	// start up a new game
	private void yesTriggered (ActionEvent event) {
		dispose();
		previousFrame.dispose();
		switch (gameMode) {
			case 0:
				// GTN
				new GTN();
				break;

			case 1:
				// RPS
				new RPS();
				break;

			default:
				Log.i(getClass(), "Game not found");
		}
	}

	// when the user selects no
	// dispose both windows
	private void noTriggered (ActionEvent event) {
		dispose();
		previousFrame.dispose();
	}

	// </editor-fold>

	// play the clapping sound effect
	private void playSound () {
		try {
			// create a file to the audio clip
			file = new File("assets/audio/clapping_effect.wav");
			audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			// opens the audio stream to make the audio file available
			clip.open(audioStream);
			// start the audio clip
			clip.start();
		} catch (Exception e) {
			Log.i(getClass(), "Error playing audio file");
		}
	}

	// initialize the window
	public CongratulationsDialog (JFrame parent, JFrame previousFrame, int gameMode) {
		super(parent, "Congratulations", true);
		this.gameMode = gameMode;
		this.previousFrame = previousFrame;
		if (parent != null) {
			setLocationRelativeTo(null);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// play the sound when it is opened
		playSound();

		// initialize all components
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING);
		mainPanel.setLayout(new GridBagLayout());
		getContentPane().add(mainPanel);

		// congratsText
		congratsText = new JLabel("CONGRATULATIONS");
		congratsText.setFont(Const.TEXT_FONT);

		// scoreText
		scoreText = new JLabel();
		scoreText.setFont(Const.TEXT_FONT);

		// scoreText will have different text depending on the gameMode
		switch (gameMode) {
			case 0:
				// GTN
				scoreText.setText("You took " + Shared.getPlayerScore() + " tries");
				break;

			case 1:
				// RPS
				scoreText.setText("You won " + Shared.getPlayerScore() + " times");
				break;

			default:
				Log.i(getClass(), "Game not found");
		}

		// playAgainText
		playAgainText = new JLabel("Play Again?");
		playAgainText.setFont(Const.TEXT_FONT);

		// loading up the image
		try {
			img = ImageIO.read(
					new File("assets/images/confetti_64.png")
			);
			temp = new JLabel(new ImageIcon(img));
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to load image");
			return; // do nothing
		}

		// yes
		yes = new JButton();
		yes.setText("Yes");
		yes.setFont(Const.BUTTON_FONT);

		// no
		no = new JButton();
		no.setText("No");
		no.setFont(Const.BUTTON_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// adding temp
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridheight = 2;
		gbc.insets = Const.RIGHT_MARGIN;
		mainPanel.add(temp, gbc);

		// adding congratsText
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		mainPanel.add(congratsText, gbc);

		// adding scoreText
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		mainPanel.add(scoreText, gbc);

		// adding playAgainText
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(playAgainText, gbc);

		// adding yes
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1;
		mainPanel.add(yes, gbc);

		// adding no
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weightx = 1;
		mainPanel.add(no, gbc);

		// </editor-fold>

		// if RPS, don't display playAgainText, yes and no
		if (gameMode == 1) {
			playAgainText.setVisible(false);
			yes.setVisible(false);
			no.setVisible(false);
		}

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				yesTriggered(e);
			}
		});

		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				noTriggered(e);
			}
		});

		// make sure to stop and close audio file
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened (WindowEvent e) {

			}

			@Override
			public void windowClosing (WindowEvent e) {
				clip.stop();
				clip.close();
			}

			@Override
			public void windowClosed (WindowEvent e) {
				clip.stop();
				clip.close();
				previousFrame.dispose();
			}

			@Override
			public void windowIconified (WindowEvent e) {

			}

			@Override
			public void windowDeiconified (WindowEvent e) {

			}

			@Override
			public void windowActivated (WindowEvent e) {

			}

			@Override
			public void windowDeactivated (WindowEvent e) {

			}
		});

		// </editor-fold>

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
