package main_window.brain;

import custom.Log;
import games.GameSelection;
import global.Const;
import global.Shared;
import helper.Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

class Commands {
	private static Random r = new Random();
	private static String chatQuestion = "", currentLine = "";

	// returns chatQuestion
	public static String getChatQuestion() {
		return chatQuestion;
	}

	// prints a random greeting from greetings.txt
	protected static void greetingsCommand() {
		int numOfLines = 0;
		int lineNum = 1; // current line #

		// get numOfLines
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.GREETINGS_FILENAME))) {
			while (br.readLine() != null) {
				numOfLines++;
			}

			Log.i(Commands.class, "numOfLines: " + numOfLines);
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open greetings.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.GREETINGS_FILENAME))) {
			// generate a random number to choose the greeting
			int rand = r.nextInt(numOfLines) + 1;

			// keep reading until the end
			while ((currentLine = br.readLine()) != null) {
				if (lineNum == rand) {
					// only print the greeting on the same line as rand
					Shared.botMessage(currentLine);
				}
				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
		}
	}

	// prints random farewell from farewell.txt
	protected static void farewellCommand() {
		int numOfLines = 0;
		int lineNum = 1; // current line #

		// get numOfLines
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.FAREWELL_FILENAME))) {
			while (br.readLine() != null) {
				numOfLines++;
			}

			Log.i(Commands.class, "numOfLines: " + numOfLines);
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open farewell.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.FAREWELL_FILENAME))) {
			// generate a random number from start to end of the file
			int rand = r.nextInt(numOfLines) + 1;

			// keep reading till the end
			while ((currentLine = br.readLine()) != null) {
				if (lineNum == rand) {
					Shared.botMessage(currentLine);
				}
				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
		}
	}

	// print random gratitude from gratitude.txt
	protected static void gratitudeCommand() {
		int numOfLines = 0;
		int lineNum = 1;

		// get numOfLines
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.GRATITUDE_FILENAME))) {
			while (br.readLine() != null) {
				numOfLines++;
			}
			Log.i(Commands.class, "numOfLines: " + numOfLines);
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open gratitude.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.GRATITUDE_FILENAME))) {
			int rand = r.nextInt(numOfLines) + 1;

			while ((currentLine = br.readLine()) != null) {
				if (lineNum == rand) {
					Shared.botMessage(currentLine);
				}

				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");

		}
	}

	// when the user wants to play
	// displays the game selection component
	protected static void gameCommand() {
		GameSelection gs = new GameSelection();
	}

	// when the user needs help
	// creates an object of class Helper
	protected static void helperCommand() {
		Helper h = new Helper();
	}

	// when the user asks for the time
	// prints only the hours and minutes
	protected static void timeCommand() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		Calendar c = Calendar.getInstance();
		Shared.botMessage("The time now is " + df.format(c.getTime()));
	}

	// when the user asked to roll a dice
	protected static void diceCommand() {
		int rand = r.nextInt(6) +  1; // 1-6
		Shared.botMessage("Rolling...");
		Shared.botMessage("I rolled a " + rand);
	}

	// tell a joke from jokes.txt and jokes_ans.txt
	protected static void jokeCommand() {
		String qn = "", ans = "";
		int lineNum = 1; // .txt files start with line 1
		int numOfLines = 0;

		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.JOKES_FILENAME))) {
			// get the num of lines in the jokes.txt
			while ((br.readLine()) != null) {
				numOfLines++;
			}
			Log.i(Commands.class, "numOfLines: " + numOfLines);
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open up jokes.txt and jokes_ans.txt
		try (BufferedReader question = new BufferedReader(
				new FileReader(Const.JOKES_FILENAME));
				BufferedReader answer = new BufferedReader(
						new FileReader(Const.JOKES_ANS_FILENAME))) {
			// generate rand num frm 1 to end of jokes.txt length
			// rand num will be the line number
			int rand = r.nextInt(numOfLines) + 1;

				// read from the line num
			while (((qn = question.readLine()) != null) && ((ans = answer.readLine()) != null)) {
				if (lineNum == rand) {
					// display both to the user
					Shared.botMessage(qn);
					Shared.botMessage(ans);
					break;
				}
				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
		}
	}

	// says a quote from quotes.txt and quoters.txt
	protected static void quoteCommand() {
		String q = "", qr = "";
		int lineNum = 1; // .txt files start with line 1
		int numOfLines = 0;

		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.QUOTE_FILENAME))){
			// get the num of lines in the quotes.txt
			while ((br.readLine()) != null) {
				numOfLines++;
			}
			Log.i(Commands.class, "numOfLines: " + numOfLines);
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open up quote.txt and quoter.txt
		try (BufferedReader quote = new BufferedReader(
				new FileReader(Const.QUOTE_FILENAME));
			 BufferedReader quoter = new BufferedReader(
					 new FileReader(Const.QUOTER_FILENAME))) {

			// rand num will be the line number
			int rand = r.nextInt(numOfLines) + 1;

			// read from the line num
			while (((q = quote.readLine()) != null) && ((qr = quoter.readLine()) != null)) {
				if (lineNum == rand) {
					// display both to the user
					Shared.message(qr + " - " + q);
					break;
				}
				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
		}
	}

	// gives compliment from compliments.txt
	protected static void complimentCommand() {
		int lineNum = 1; // .txt files start with line 1
		int numOfLines = 0;

		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.COMPLIMENTS_FILENAME))){
			// get the num of lines in the compliment.txt
			while ((br.readLine()) != null) {
				numOfLines++;
			}
			Log.i(Commands.class, "numOfLines: " + numOfLines);

		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open up compliment.txt
		try (BufferedReader compliment = new BufferedReader(
				new FileReader(Const.COMPLIMENTS_FILENAME))) {
			// generate rand num frm 1 to end of compliments.txt length
			// rand num will be the line number
			int rand = r.nextInt(numOfLines) + 1;

			// read from the line num
			while ((currentLine = compliment.readLine()) != null) {
				if (lineNum == rand) {
					// display both to the user
					Shared.botMessage(currentLine);
					break;
				}
				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
		}
	}

	// replies when the user asks how are you
	// bot asks the user how they are
	protected static void howAreYouCommand() {
		int lineNum = 1; // .txt files start with line 1
		int numOfLines = 0;

		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.HOW_ARE_YOU_FILENAME))){
			// get the num of lines
			while ((br.readLine()) != null) {
				numOfLines++;
			}
			Log.i(Commands.class, "numOfLines: " + numOfLines);

		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
			return; // do nothing
		}

		// open up how_are_you.txt
		try (BufferedReader quote = new BufferedReader(
				new FileReader(Const.HOW_ARE_YOU_FILENAME))) {
			// rand num will be the line number
			int rand = r.nextInt(numOfLines) + 1;

			// read from the line num
			while ((currentLine = quote.readLine()) != null) {
				if (lineNum == rand) {
					// display both to the user
					Shared.botMessage(currentLine);
					break;
				}
				lineNum++;
			}
		} catch (IOException ob) {
			Log.i(Commands.class, "Unable to locate file");
		}
	}

	// when the user states let's chat
	// asks a random question
	protected static void letsChatCommand() {
		int rand = r.nextInt(Const.QUESTIONS.length);
		chatQuestion = Const.QUESTIONS[rand];
		Shared.botMessage(chatQuestion);
	}
}
