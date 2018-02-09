package main_window.add.response;

import custom.CustomFrame;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// this is used as a template for the other GUI components that will add to 1 file
// this acts as a superclass
abstract class TemplateResponse {
	private JButton add, cancel;
	private JLabel label1;
	private static JTextField input;
	private JPanel mainPanel, inputPanel, buttonPanel;
	private JFrame frame;
	private GridBagConstraints gbc;

	private String title, label1Text;
	private static String text;

	public TemplateResponse (String title, String label1Text) {
		this.title = title;
		this.label1Text = label1Text;
		initComponents();
	}

	// returns text
	public static String getText () {
		return text;
	}

	// reset input text
	public static void resetText () {
		input.setText("");
	}

	// <editor-fold defaultstate="collapsed" desc="Event Handlers">

	// made abstract to allow each class to modify what this will do
	abstract public void addTriggered (ActionEvent event);

	// dispose of the current frame
	private void cancelTriggered (ActionEvent event) {
		frame.dispose();
	}

	// </editor-fold>

	// checks if input is empty
	// if it is, return true
	protected final boolean isEmpty () {
		text = input.getText();

		if (text.trim().equals("")) {
			return true;
		}

		return false;
	}

	// initialize window
	private void initComponents () {
		// intialize frame
		frame = new CustomFrame(
				title, Const.ADD_RESPONSE_WIDTH, Const.ADD_RESPONSE_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// intializing all components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING);
		// use BoxLayout as we want the top and bottom panel to be spaced evenly
		// on top of another
		mainPanel.setLayout(new BoxLayout(
				mainPanel, BoxLayout.Y_AXIS
		));
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to the frame

		// inputPanel
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		mainPanel.add(inputPanel);

		// buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		mainPanel.add(buttonPanel);

		// label1
		label1 = new JLabel(label1Text + ": ");
		label1.setFont(Const.TEXT_FONT);

		// input
		input = new JTextField();
		input.setFont(Const.TEXT_FONT);

		// add
		add = new JButton("Add");
		add.setFont(Const.BUTTON_FONT);

		// cancel
		cancel = new JButton("Cancel");
		cancel.setFont(Const.BUTTON_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = Const.BOTTOM_RIGHT_MARGIN;
		inputPanel.add(label1, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		inputPanel.add(input, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_END;
		buttonPanel.add(add, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonPanel.add(cancel, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addTriggered(e);
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				cancelTriggered(e);
			}
		});

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
