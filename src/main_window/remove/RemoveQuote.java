package main_window.remove;

import custom.Log;
import global.Const;

import java.awt.event.ActionEvent;
import java.io.*;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class RemoveQuote extends TemplateRemove {
	public RemoveQuote (String label1Text, String title) {
		super(label1Text, title);
		fillContent();
	}

	// fills the content of comboBox
	@Override
	public void fillContent () {
		String quote = "";

		// open jokes.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.QUOTE_FILENAME))) {
			// read each line
			while ((quote = br.readLine()) != null) {
				// add to the combo box
				TemplateRemove.addToComboBox(quote);
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
		}
	}

	// remove the quote
	@Override
	public void removeTriggered (ActionEvent event) {
		String quoteToRemove = TemplateRemove.getComboBoxSelection();
		Log.i(getClass(), "quoteToRemove: " + quoteToRemove);
		int lineCount = 1;
		String j = "", p = "";
		File originalQuote = new File(Const.QUOTE_FILENAME);
		File originalQuoter = new File(Const.QUOTER_FILENAME);
		File tempQuote = new File("assets/text_files/quotes/tempQuotes.txt");
		File tempQuoter = new File("assets/text_files/quotes/tempQuoter.txt");

		// read file from stream
		try (BufferedReader quote = new BufferedReader(
				new FileReader(originalQuote));
			 BufferedReader quoter = new BufferedReader(
					 new FileReader(originalQuoter));
			 PrintWriter quoteOut = new PrintWriter(
					 new FileWriter(tempQuote, true));
			 PrintWriter quoterOut = new PrintWriter(
					 new FileWriter(tempQuoter, true))) {
			// write to new file
			// append set to true to prevent the contents of the file from being overriden
			while (((j = quote.readLine()) != null) && ((p = quoter.readLine()) != null)) {
				if (j.equals(quoteToRemove)) {
					// skip the currentLine which is the quote
					continue;
				}

				if (lineCount == 1) {
					// if it is the first line, just add the text
					quoteOut.print(j);
					quoterOut.print(p);
				} else {
					// else add a newline before the text
					quoteOut.print("\n" + j);
					quoterOut.print("\n" + p);
				}

				lineCount++;
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
			return; // do nothing
		}

		// remove old file
		if (!(originalQuote.delete() && originalQuoter.delete())) {
			Log.i(getClass(), "Cannot delete files");
			return;
		}

		// rename temp files to original file name
		if (!(tempQuote.renameTo(originalQuote) && tempQuoter.renameTo(originalQuoter))) {
			Log.i(getClass(), "Cannot rename files");
			return;
		}

		// after every removal, refresh comboBox
		TemplateRemove.resetComboBox();
		fillContent();
	}
}
