package games;

import custom.Log;
import global.Shared;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

class GamesMenuBar {
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem exit, changeGame, newGame;
	private JSeparator separator;
	private JFrame parent;

	private int gameMode;

	// <editor-fold defaultstate="collapsed" desc="Event Handlers"

	// when the user changes the game
	// dispose of the current game (parent)
	// show GameSelection again
	// if RPS, show CongratulationsDialog
	private void changeGameTriggered(ActionEvent event) {
		if (gameMode == 1) {
			// if RPS, display congratulations dialog before exiting
			CongratulationsDialog cd = new CongratulationsDialog(
					new JFrame(), parent, gameMode
			);
		}

		parent.dispose();
		new GameSelection();
	}

	// when the user exits the game
	// for RPS, display CongratulationDialog to show the score
	// dispose of the game (parent)
	private void exitTriggered(ActionEvent event) {
		if (gameMode == 1) {
			// if RPS, display congratulations dialog before exiting
			CongratulationsDialog cd = new CongratulationsDialog(
					new JFrame(), parent, gameMode
			);
		}

		parent.dispose();
	}

	// when the user selects new game
	// scores will be reset whent
	// dispose of current game (parent)
	private void newGameTriggered(ActionEvent event) {
		Shared.setBotScore(0);
		Shared.setPlayerScore(0);
		parent.dispose();

		switch (gameMode) {
			case 0:
				// this is GTN
				new GTN();
				break;

			case 1:
				// this is RPS
				new CongratulationsDialog(new JFrame(), parent, 1);
				new RPS();
				break;

			default:
				Log.i(getClass(), "No such game found");
		}
	}

	// </editor-fold>

	public GamesMenuBar(JFrame parent, int gameMode) {
		this.parent = parent;
		this.gameMode = gameMode;

		Log.i(getClass(), "gameMode: " + gameMode);

		menuBar = new JMenuBar();

		separator = new JSeparator(JSeparator.HORIZONTAL);

		file = new JMenu("File");
		exit = new JMenuItem("Exit");
		exit.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK)
		);
		changeGame = new JMenuItem("Change Game");
		changeGame.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK)
		);
		newGame = new JMenuItem("New Game");
		newGame.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)
		);

		file.add(newGame);
		file.add(changeGame);
		file.add(separator);
		file.add(exit);

		menuBar.add(file);

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		changeGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				changeGameTriggered(e);
			}
		});

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				newGameTriggered(e);
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				exitTriggered(e);
			}
		});

		// </editor-fold>

		parent.setJMenuBar(menuBar);
	}
}
