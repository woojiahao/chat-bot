package main_window.add.response;

import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class AddFarewell extends TemplateResponse {
	public AddFarewell (String title, String label1Text) {
		super(title, label1Text);
	}

	@Override
	public void addTriggered (ActionEvent event) {
		// open farewell.txt with a PrintWriter
		try (PrintWriter pw = new PrintWriter(
				new FileWriter(Const.FAREWELL_FILENAME, true))) {
			// append set to true as we want to add onto the file
			// not replace the contents
			// check is input is empty
			if (!isEmpty()) {
				// if it is not empty, add the input to the file
				pw.print("\n" + TemplateResponse.getText());
			} else {
				// if it is empty, prompt the user to enter something
				JOptionPane.showMessageDialog(
						null, "Please enter a farewell",
						"Error", JOptionPane.ERROR_MESSAGE
				);
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
			return;
		}

		// clear the input area for next input
		TemplateResponse.resetText();
	}
}