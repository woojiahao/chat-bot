package main_window.remove;

import custom.Log;
import global.Const;

import java.awt.event.ActionEvent;
import java.io.*;

/*	Created by: Woo Jia Hao	Date: 29/07/2017 */

public class RemoveJoke extends TemplateRemove {
	public RemoveJoke (String label1Text, String title) {
		super(label1Text, title);
		fillContent();
	}

	// populating comboBox to display the contents of the file
	@Override
	public void fillContent () {
		String joke = "";

		// open jokes.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.JOKES_FILENAME))) {
			// read each line
			while ((joke = br.readLine()) != null) {
				// add to the combo box
				TemplateRemove.addToComboBox(joke);
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
		}
	}

	// remove the joke
	@Override
	public void removeTriggered (ActionEvent event) {
		String jokeToRemove = TemplateRemove.getComboBoxSelection();
		int lineCount = 1;
		String j = "", p = "";
		File originalJokes = new File(Const.JOKES_FILENAME);
		File originalJokesAns = new File(Const.JOKES_ANS_FILENAME);
		File tempJokes = new File("assets/text_files/jokes/tempJokes.txt");
		File tempJokesAns = new File("assets/text_files/jokes/tempJokesAns.txt");

		// read file from stream
		try (BufferedReader jokes = new BufferedReader(
				new FileReader(originalJokes));
			 BufferedReader punchline = new BufferedReader(
					 new FileReader(originalJokesAns));
			 PrintWriter jokesOut = new PrintWriter(
					 new FileWriter(tempJokes, true));
			 PrintWriter punchlineOut = new PrintWriter(
					 new FileWriter(tempJokesAns, true))) {
			// write to new file
			// append set to true to prevent the content of the file from being overridden
			while (((j = jokes.readLine()) != null) && ((p = punchline.readLine()) != null)) {
				if (j.equals(jokeToRemove)) {
					// skip the currentLine which is the quote
					continue;
				}

				if (lineCount == 1) {
					// if it is the first line, just add the text
					jokesOut.print(j);
					punchlineOut.print(p);
				} else {
					// else add a newline before the text
					jokesOut.print("\n" + j);
					punchlineOut.print("\n" + p);
				}

				lineCount++;
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
			return; // do nothing
		}

		// remove old file
		if (!(originalJokes.delete() && originalJokesAns.delete())) {
			Log.i(getClass(), "Cannot delete files");
			return;
		}

		// rename temp file to original file
		if (!(tempJokes.renameTo(originalJokes) && tempJokesAns.renameTo(originalJokesAns))) {
			Log.i(getClass(), "Cannot rename files");
			return;
		}

		// after every removal refresh comboBox
		TemplateRemove.resetComboBox();
		fillContent();
	}
}
