package main_window.add;

import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */


public class AddQuote extends TemplateAdd {
	public AddQuote (String label1Text, String label2Text, String title) {
		super(label1Text, label2Text, title);
	}

	@Override
	void addTriggered (ActionEvent event) {
		// open up quotes.txt and quoters.txt
		try (PrintWriter quote = new PrintWriter(
				new FileWriter(Const.QUOTE_FILENAME, true));
			 PrintWriter quoter = new PrintWriter(
					 new FileWriter(Const.QUOTER_FILENAME, true))) {
			// append set to true to prevent the contents of the file from being overriden
			// check if textfields are empty
			if (!isEmpty()) {
				// add a new line before appending the text
				quote.print("\n" + TemplateAdd.getText1());
				quoter.print("\n" + TemplateAdd.getText2());
			} else {
				JOptionPane.showMessageDialog(
						null, "Please enter the quote or quoter!",
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
