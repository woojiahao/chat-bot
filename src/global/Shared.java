package global;

import custom.Log;
import main_window.ApplicationWindow;

import javax.swing.*;

/*	Created by: Woo Jia Hao	Date: 29/07/2017 */

// used to contain shared properties and methods
public class Shared {
	// names
	// placed here to allow multiple classes access it
	private static String userName = "";
	private static String botName = "";

	public static void setUserName (String name) {
		userName = name;
	}

	public static void setBotName (String name) {
		botName = name;
	}

	public static String getUserName () {
		return userName;
	}

	public static String getBotName () {
		return botName;
	}

	// games
	// this controls the player's and bot's scores
	private static int playerScore = 0, botScore = 0;

	public static void setPlayerScore (int score) {
		playerScore = score;
	}

	public static int getPlayerScore () {
		return playerScore;
	}

	public static void setBotScore (int score) {
		botScore = score;
	}

	public static int getBotScore () {
		return botScore;
	}

	// talking
	// this is used to display the bot's text to the screen
	public static void botMessage (String str) {
		ApplicationWindow.getOutput().append(Shared.getBotName() + ": " + str + "\n");
	}

	// used if the text to display is not said by the bot
	public static void message (String str) {
		ApplicationWindow.getOutput().append(str + "\n");
	}

	// theming
	// used to set the theme of the GUI
	public static void setTheme () {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Log.i(Shared.class, "Error loading theme");
		}
	}
}
