package main_window.add;

import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class AddJoke extends TemplateAdd {
	public AddJoke (String label1Text, String label2Text, String title) {
		super(label1Text, label2Text, title);
	}

	@Override
	void addTriggered (ActionEvent event) {
		// open up jokes.txt and jokes_ans.txt
		try (PrintWriter joke = new PrintWriter(
				new FileWriter(Const.JOKES_FILENAME, true));
			 PrintWriter punchline = new PrintWriter(
					 new FileWriter(Const.JOKES_ANS_FILENAME, true))) {
			// check if textfields are empty
			// append set to true to prevent the contents of the file from being overriden
			if (!isEmpty()) {
				// add a new line before appending the text
				joke.print("\n" + TemplateAdd.getText1());
				punchline.print("\n" + TemplateAdd.getText2());
			} else {
				JOptionPane.showMessageDialog(
						null, "Please enter the joke or punchline!",
						"Error", JOptionPane.ERROR_MESSAGE
				);
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to load file");
			return; // do nothing
		}

		// clear the textfields
		resetText();
	}
}
