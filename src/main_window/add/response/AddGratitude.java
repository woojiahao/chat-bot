package main_window.add.response;

import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */


public class AddGratitude extends TemplateResponse {
	public AddGratitude (String title, String label1Text) {
		super(title, label1Text);
	}

	@Override
	public void addTriggered (ActionEvent event) {
		// open gratitude.txt with PrintWriter
		try (PrintWriter pw = new PrintWriter(
				new FileWriter(Const.GRATITUDE_FILENAME, true))) {
			// append is set to true as we want to add on to the file
			// not replace the contents
			// check if input is empty
			if (!isEmpty()) {
				// if it is not empty, add text to file
				pw.print("\n" + TemplateResponse.getText());
			} else {
				// else prompt the user to enter something
				JOptionPane.showMessageDialog(
						null, "Please enter a gratitude",
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