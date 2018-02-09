package custom;

import javax.swing.*;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// used for TextFields that can only accept numbers
public class NumberTextField extends JTextField {
	private String input;
	private int choice;

	public NumberTextField () {
		super();
		input = "";
	}

	// checking to see if input contains letters
	// tries to parse the input
	// if there are letters, input cannot be parsed to int
	public boolean containsLetter () {
		input = getText();

		try {
			choice = Integer.parseInt(input);
		} catch (Exception ob) {
			Log.i(getClass(), "Invalid input");
			return true;
		}

		return false;
	}
}
