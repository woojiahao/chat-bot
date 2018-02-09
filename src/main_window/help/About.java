package main_window.help;

import custom.CustomFrame;
import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class About {
	private JTextArea aboutText;
	private JButton okBtn;
	private JPanel mainPanel;
	private JFrame frame;
	private GridBagConstraints gbc;

	public About () {
		initComponents();
	}

	// populate the output
	private void fillText () {
		String currentLine = "";

		// open about_text.txt
		try (BufferedReader br = new BufferedReader(
				new FileReader(Const.ABOUT_TEXT_FILENAME))) {
			// read every line from file
			// add every line to the text area
			while ((currentLine = br.readLine()) != null) {
				aboutText.append(currentLine + "\n");
			}
		} catch (IOException ob) {
			Log.i(getClass(), "Unable to locate file");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="Event Handlers">

	// closes the about screen
	private void okTriggered (ActionEvent event) {
		frame.dispose();
	}

	// </editor-fold>

	// initializing window
	private void initComponents () {
		// initialize frame
		frame = new CustomFrame(
				"About", Const.ABOUT_WIDTH, Const.ABOUT_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// intialize other components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING);
		mainPanel.setLayout(new GridBagLayout());
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		// aboutText
		aboutText = new JTextArea();
		aboutText.setFont(Const.TEXT_FONT);
		aboutText.setLineWrap(true);
		aboutText.setWrapStyleWord(true);
		aboutText.setEditable(false);
		aboutText.setBackground(frame.getBackground()); // keep the background consistent
		// set the text
		fillText();

		// okBtn
		okBtn = new JButton("OK");
		okBtn.setFont(Const.BUTTON_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints"

		// add aboutText to mainPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		mainPanel.add(aboutText, gbc);

		// add okBtn to mainPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		mainPanel.add(okBtn, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				okTriggered(e);
			}
		});

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
