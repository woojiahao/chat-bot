package global;

import javax.swing.border.EmptyBorder;
import java.awt.*;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// used to contain commonly used constants
public class Const {
	// int values
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 500;
	public static final int HELPER_WIDTH = 1200;
	public static final int HELPER_HEIGHT = 900;
	public static final int RPS_WIDTH = 700;
	public static final int RPS_HEIGHT = 300;
	public static final int GTN_WIDTH = 500;
	public static final int GTN_HEIGHT = 225;
	public static final int ADD_WIDTH = 600;
	public static final int ADD_HEIGHT = 165;
	public static final int REMOVE_WIDTH = 600;
	public static final int REMOVE_HEIGHT = 130;
	public static final int COMMANDS_WIDTH = 500;
	public static final int COMMANDS_HEIGHT = 100;
	public static final int ABOUT_WIDTH = 500;
	public static final int ABOUT_HEIGHT = 100;
	public static final int ADD_RESPONSE_WIDTH = 600;
	public static final int ADD_RESPONSE_HEIGHT = 100;

	// String values
	public static final String JOKES_FILENAME = "assets/text_files/jokes/jokes.txt";
	public static final String JOKES_ANS_FILENAME = "assets/text_files/jokes/jokes_ans.txt";
	public static final String QUOTE_FILENAME = "assets/text_files/quotes/quotes.txt";
	public static final String QUOTER_FILENAME = "assets/text_files/quotes/quoters.txt";
	public static final String COMPLIMENTS_FILENAME = "assets/text_files/compliments/compliments.txt";
	public static final String ABOUT_TEXT_FILENAME = "assets/text_files/about_text.txt";
	public static final String COMMANDS_FILENAME = "assets/text_files/commands.txt";
	public static final String FAREWELL_FILENAME = "assets/text_files/salutations/farewell.txt";
	public static final String GRATITUDE_FILENAME = "assets/text_files/salutations/gratitude.txt";
	public static final String GREETINGS_FILENAME = "assets/text_files/salutations/greetings.txt";
	public static final String HOW_ARE_YOU_FILENAME = "assets/text_files/salutations/how_are_you.txt";
	public static final String CREDITS_FILENAME = "assets/text_files/credits.txt";

	// Font values
	public static final Font TEXT_FONT = new Font("Calibri", 0, 19);
	public static final Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 17);
	public static final Font CODE_FONT = new Font("Consolas", 0, 19);
	public static final Font GAME_FONT = new Font("Calibri", Font.BOLD, 40);
	public static final Font GAME_BUTTON_FONT = new Font("Calibri", Font.BOLD, 25);

	// Border values
	public static final EmptyBorder WINDOW_PADDING = new EmptyBorder(15, 15, 15, 15);

	// Insets values
	public static final Insets INPUT_MARGIN = new Insets(0, 10, 0, 10);
	public static final Insets RIGHT_MARGIN = new Insets(0, 0, 0, 10);
	public static final Insets BOTTOM_MARGIN = new Insets(0, 0, 10, 0);
	public static final Insets BOTTOM_RIGHT_MARGIN = new Insets(0, 0, 10, 10);

	// String[]
	public static final String[] SELECTION = {
			"Guess the number",
			"Rock-Paper-Scissors"
	};

	public static final String[][] COMMANDS = {
			{"hello", "hi", "greetings", "hey"},
			{"seeyou", "bye"},
			{"thankyou", "arigato", "thanks"},
			{"letsplay", "playgame", "bored"},
			{"helpme", "javahelp"},
			{"time"},
			{"roll", "dice", "feelinglucky"},
			{"quote", "knowledge"},
			{"joke", "laugh"},
			{"compliment"},
			{"howareyou"},
			{"letschat"}
	};

	public static final String[] QUESTIONS = {
			"What hobbies do you have?",
			"What is your favourite food?",
			"What is/was your favorite subject in school?",
			"Have been on a vacation recently?"
	};
}
