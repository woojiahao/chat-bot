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

public class Commands {
	private JTextArea output;
	private JScrollPane scrollPane;
	private JFrame frame;
	private JPanel mainPanel;
	private GridBagConstraints gbc;

	public Commands () {
		initComponents();
	}

	// fill output
	private void populateOutput () {
		String command = "";

		// open commands.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.COMMANDS_FILENAME))) {
			// read every line from file
			// add every line to the text area
			while ((command = br.readLine()) != null) {
				output.append(command + "\n");
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
		}
	}

	// initialize window
	private void initComponents () {
		// intialize frame
		frame = new CustomFrame(
				"Available commands", Const.COMMANDS_WIDTH, Const.COMMANDS_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// intialize all components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING); // this is window padding
		mainPanel.setLayout(new GridBagLayout());
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		// output
		output = new JTextArea();
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		output.setFont(Const.TEXT_FONT);
		output.setBackground(frame.getBackground()); // keep the background consistent
		// fill output
		populateOutput();

		// scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(output);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints"

		// adding scrollPane
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		mainPanel.add(output, gbc);

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
