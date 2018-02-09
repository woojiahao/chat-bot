package games;

import custom.CustomFrame;
import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class GameSelection {
	private JComboBox<String> dropDown;
	private JButton selectBtn, cancelBtn;
	private JPanel mainPanel;
	private JLabel label;
	private JFrame frame;
	private GridBagConstraints gbc;

	private int selection;

	public GameSelection () {
		initComponents();
	}

	// <editor-fold defaultstate="collapsed" desc="Event Handlers"

	// when the user selects an item
	private void selectTriggered (ActionEvent event) {
		// get the selection from dropDown
		selection = dropDown.getSelectedIndex();
		Log.i(getClass(), "Selected index: " + selection);

		// dispose this frame
		frame.dispose();

		// launch that respective game
		switch (selection) {
			case 0:
				// GTN
				new GTN();
				break;

			case 1:
				// RPS
				new RPS();
				break;

			default:
				Log.i(getClass(), "Game not found");
		}
	}

	// when the user presses cancel
	// dispose of the current frame
	private void cancelTriggered (ActionEvent event) {
		frame.dispose();
	}

	// </editor-fold>

	// intialize the window
	private void initComponents () {
		// initializing frame
		frame = new CustomFrame(
				"Game Selection", false,
				JFrame.DISPOSE_ON_CLOSE
		);

		// initialize all components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING);
		mainPanel.setLayout(new GridBagLayout());
		Container c = frame.getContentPane();
		c.add(mainPanel);

		// dropDown
		dropDown = new JComboBox<>(Const.SELECTION);
		dropDown.setFont(Const.TEXT_FONT);

		// label
		label = new JLabel("Select a game:");
		label.setFont(Const.TEXT_FONT);

		// selectBtn
		selectBtn = new JButton("Select");
		selectBtn.setFont(Const.BUTTON_FONT);

		// cancelBtn
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(Const.BUTTON_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// adding label
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(label, gbc);

		// adding dropDown
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(dropDown, gbc);

		// adding selectBtn
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = Const.RIGHT_MARGIN;
		mainPanel.add(selectBtn, gbc);

		// adding cancelBtn
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 2;
		mainPanel.add(cancelBtn, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listener">

		selectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				selectTriggered(e);
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
