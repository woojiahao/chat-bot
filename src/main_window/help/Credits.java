package main_window.help;

import custom.CustomFrame;
import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */


public class Credits {
	private JTextArea output;
	private JFrame frame;
	private JPanel mainPanel;
	private GridBagConstraints gbc;

	public Credits () {
		initComponents();
	}

	// fill output
	private void populateOutput () {
		String currentLine = "";

		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.CREDITS_FILENAME))) {
			// read every line from file
			// add every line to the text area
			while ((currentLine = br.readLine()) != null) {
				output.append(currentLine + "\n");
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
		}
	}

	// initialize window
	private void initComponents () {
		// initialize frame
		frame = new CustomFrame(
				"Credits", false, JFrame.DISPOSE_ON_CLOSE
		);

		// intialize all components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING); // this is window padding
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		// output
		output = new JTextArea();
		output.setFont(Const.TEXT_FONT);
		output.setEditable(false);
		output.setBackground(frame.getBackground()); // keep the background consistent
		populateOutput();

		mainPanel.add(output);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
