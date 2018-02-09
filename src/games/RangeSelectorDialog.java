package games;

import custom.Log;
import custom.NumberTextField;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */


// used by GTN to let user select range from 0 - selection
public class RangeSelectorDialog extends JDialog {
	private JLabel text;
	private JButton set;
	private NumberTextField input;
	private JPanel mainPanel;
	private GridBagConstraints gbc;

	private static int range = 20; // default value 20

	public static int getRange () {
		return range;
	}

	// <editor-fold defaultstate="collapsed" desc="Event Listeners">

	// when set button is pressed
	private void setPressed (ActionEvent event) {
		Log.i(getClass(), "Set Pressed");
		String inputText = "";

		// checking for valid number
		inputText = input.getText();

		// check for invalid input
		// if there is invalid input, then the default of 20 will be used
		if (input.containsLetter() || inputText.trim().equals("")) {
			JOptionPane.showMessageDialog(
					null, "Invalid input\nUsing 20 as range!",
					"Invalid input", JOptionPane.ERROR_MESSAGE
			);
		}

		// this prevents invalid input from being parsed
		try {
			range = Integer.parseInt(inputText);
		} catch (NumberFormatException ob) {
			Log.i(getClass(), "Invalid input");
			return;
		}

		Log.i(getClass(), "Range: " + String.valueOf(range));
	}

	// </editor-fold>

	public RangeSelectorDialog (JFrame parent, String title) {
		super(parent, title, true);
		if (parent != null) {
			setLocationRelativeTo(null);
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);

		// initialize all components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING);
		mainPanel.setLayout(new GridBagLayout());
		getContentPane().add(mainPanel);

		// text
		text = new JLabel("Set the range:");
		text.setFont(Const.TEXT_FONT);

		// input
		input = new NumberTextField();
		input.setFont(Const.TEXT_FONT);
		input.setText("20"); // default range -> 0-20

		// set
		set = new JButton("Set");
		set.setFont(Const.BUTTON_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// adding text
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(text, gbc);

		// adding input
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(input, gbc);

		// adding set
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		mainPanel.add(set, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		set.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				setPressed(e);
				// dispose of the dialog even when invalid input is detected
				// this allows the bot to use the default value
				dispose();
			}
		});

		// </editor-fold>

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
