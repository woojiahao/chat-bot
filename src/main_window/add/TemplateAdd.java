package main_window.add;

import custom.CustomFrame;
import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// this acts a template for the classes that want to add to 2 files
abstract class TemplateAdd {
	private JLabel label1, label2;
	private static JTextField input1, input2;
	private JButton addBtn, cancelBtn;
	private JFrame frame;
	private JPanel mainPanel, inputPanel, buttonPanel;
	private GridBagConstraints gbc;

	private static String text1, text2;
	private String label1Text, label2Text, title;

	protected TemplateAdd (String label1Text, String label2Text, String title) {
		this.label1Text = label1Text;
		this.label2Text = label2Text;
		this.title = title;
		initComponents();
	}

	// returns text1
	protected static String getText1() {
		return text1;
	}

	// returns text2
	protected static String getText2() {
		return text2;
	}

	// check that both textfields are not empty
	protected final boolean isEmpty() {
		text1 = input1.getText();
		text2 = input2.getText();

		if (text1.trim().equals("") || text2.trim().equals("")) {
			return true;
		}

		return false;
	}

	// reset both input
	// clears the input for future input
	protected static void resetText() {
		input1.setText("");
		input2.setText("");
	}

	// <editor-fold defaultstate="collapsed" desc="Event Handlers"

	// made abstract so each subclass can modify it to work in their scenario
	abstract void addTriggered(ActionEvent event);

	// when the user presses cancel
	// dispose of the current frame
	private void cancelTriggered(ActionEvent event) {
		frame.dispose();
	}

	// </editor-fold>

	// intialize window
	private void initComponents() {
		// initialize frame
		frame = new CustomFrame(
				title, Const.ADD_WIDTH, Const.ADD_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// initialize all components
		// mainPanel
		mainPanel = new JPanel();
		// use BoxLayout to ensure top and bottom panels are evenly spaced
		mainPanel.setLayout(new BoxLayout(
				mainPanel, BoxLayout.Y_AXIS
		));
		mainPanel.setBorder(Const.WINDOW_PADDING); // for window padding
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

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

		// label2
		label2 = new JLabel(label2Text + ": ");
		label2.setFont(Const.TEXT_FONT);

		// input1
		input1 = new JTextField();
		input1.setFont(Const.TEXT_FONT);

		// input2
		input2 = new JTextField();
		input2.setFont(Const.TEXT_FONT);

		// addBtn
		addBtn = new JButton("Add");
		addBtn.setFont(Const.BUTTON_FONT);

		// cancelBtn
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(Const.BUTTON_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// adding label1 to inputPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = Const.BOTTOM_RIGHT_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_START;
		inputPanel.add(label1, gbc);

		// adding input1 to inputPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		inputPanel.add(input1, gbc);

		// adding label2 to inputPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = Const.BOTTOM_RIGHT_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_START;
		inputPanel.add(label2, gbc);

		// adding input2 to inputPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		inputPanel.add(input2, gbc);

		// adding addBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_END;
		buttonPanel.add(addBtn, gbc);

		// adding cancelBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonPanel.add(cancelBtn, gbc);
		gbc.anchor = GridBagConstraints.LINE_END;

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addTriggered(e);
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
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
