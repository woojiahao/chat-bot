package main_window.brain;

import custom.Log;
import global.Const;
import global.Shared;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class ProcessInput {
	private static String usrInput;
	private static boolean isChatting = false;

	// check if input contains a keyword
	private static boolean containsKeyword (int indexInArray, boolean contains) {
		// contains decides whether to use equals() or contains()
		// loop through COMMANDS array
		for (int i = 0; i < Const.COMMANDS[indexInArray].length; i++) {
			// check if input.contains or input.equals COMMANDS[i]
			if (contains) {
				if (usrInput.contains(Const.COMMANDS[indexInArray][i])) {
					return true;
				}
			} else {
				if (usrInput.equals(Const.COMMANDS[indexInArray][i])) {
					return true;
				}
			}
		}

		return false;
	}

	// check if the bot had previously asked a question
	private static boolean wasQuestion (int index) {
		for (int i = 0; i < Const.QUESTIONS.length; i++) {
			if (Commands.getChatQuestion().equals(Const.QUESTIONS[index])) {
				return true;
			}
		}
		return false;
	}

	// this is the "brain"
	public static void processingInput (String input) {
		Log.i(ProcessInput.class, "Processing: " + input);
		usrInput = input;

		if (isChatting) {
			if (wasQuestion(0)) {
				// hobbies
				Shared.botMessage("Ohhh, interesting! My hobbies are swimming and playing rock-paper-scissors");
			} else if (wasQuestion(1)) {
				// food
				Shared.botMessage("I really love chicken rice!");
			} else if (wasQuestion(2)) {
				// subject
				Shared.botMessage("I can help you in Java if you need help!");
			} else if (wasQuestion(3)) {
				// vacation
				Shared.botMessage("Ohhh, I have always wanted to visit there someday, maybe you can bring me along next time!");
			} else {
				Shared.botMessage("Good to hear!");
			}

			isChatting = false;
		} else {
			if (containsKeyword(0, true)) {
				// greetings
				Commands.greetingsCommand();
			} else if (containsKeyword(1, true)) {
				// farewell
				Commands.farewellCommand();
			} else if (containsKeyword(2, true)) {
				// gratitude
				Commands.gratitudeCommand();
			} else if (containsKeyword(3, true)) {
				// game
				Commands.gameCommand();
			} else if (containsKeyword(4, true)) {
				// helper
				Commands.helperCommand();
			} else if (containsKeyword(5, true)) {
				// time
				Commands.timeCommand();
			} else if (containsKeyword(6, true)) {
				// dice
				Commands.diceCommand();
			} else if (containsKeyword(7, true)) {
				// quote
				Commands.quoteCommand();
			} else if (containsKeyword(8, true)) {
				// joke
				Commands.jokeCommand();
			} else if (containsKeyword(9, true)) {
				// compliment
				Commands.complimentCommand();
			} else if (containsKeyword(10, false)) {
				// how are you
				Commands.howAreYouCommand();
				Shared.botMessage("And you?");
				isChatting = true;
			} else if (containsKeyword(11, false)) {
				// chat
				Commands.letsChatCommand();
				isChatting = true;
			} else {
				Shared.botMessage("Sorry, I do not understand");
			}
		}
	}
}
