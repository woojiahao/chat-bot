package main_window.add.response;

import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class AddCompliments extends TemplateResponse {
	public AddCompliments (String title, String label1Text) {
		super(title, label1Text);
	}

	@Override
	public void addTriggered (ActionEvent event) {
		// opens compliments.txt using a PrintWriter
		try (PrintWriter pw = new PrintWriter(
				new FileWriter(Const.COMPLIMENTS_FILENAME, true))) {
			// append is set to true as we want to add on to the file
			// not replace the file
			// check whether the input area is empty
			if (!isEmpty()) {
				// if not empty, add the input to compliments.txt
				pw.print("\n" + TemplateResponse.getText());
			} else {
				// if empty, prompt the user to add something
				JOptionPane.showMessageDialog(
						null, "Please enter a compliment",
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
