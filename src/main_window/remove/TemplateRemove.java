package main_window.remove;

import custom.CustomFrame;
import custom.Log;
import global.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// used as a template for the remove classes
abstract class TemplateRemove {
	private JLabel label1;
	private static JComboBox<String> comboBox;
	private JButton removeBtn, cancelBtn;
	private JFrame frame;
	private JPanel mainPanel, topPanel, buttonPanel;
	private GridBagConstraints gbc;

	private String label1Text, title;

	public TemplateRemove (String label1Text, String title) {
		this.label1Text = label1Text;
		this.title = title;
		initComponents();
	}

	// fill comboBox
	public static void addToComboBox (String content) {
		comboBox.addItem(content);
	}

	// returns the item selected
	public static String getComboBoxSelection () {
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}

	// removes all items
	public static void resetComboBox () {
		comboBox.removeAllItems();
	}

	// left abstract for subclasses to modify
	abstract public void fillContent ();

	// <editor-fold defaultstate="collapsed" desc="Event Handlers">

	// left abstract for subclass to modify
	abstract public void removeTriggered (ActionEvent event);

	// dispose of current frame
	private void cancelTriggered (ActionEvent event) {
		frame.dispose();
	}

	// </editor-fold>

	private void initComponents () {
		// initialize frame
		frame = new CustomFrame(
				title, Const.REMOVE_WIDTH, Const.REMOVE_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);
		// initialize all components
		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setBorder(Const.WINDOW_PADDING); // this is window padding
		// uses BoxLayout to ensure that top and bottom panel is evenly spaced
		mainPanel.setLayout(new BoxLayout(
				mainPanel, BoxLayout.Y_AXIS
		));
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		// topPanel
		topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		mainPanel.add(topPanel);

		// buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		mainPanel.add(buttonPanel);

		// label1
		label1 = new JLabel(label1Text + ": ");
		label1.setFont(Const.TEXT_FONT);

		// removeBtn
		removeBtn = new JButton("Remove");
		removeBtn.setFont(Const.BUTTON_FONT);

		// cancelBtn
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(Const.BUTTON_FONT);

		// comboBox
		comboBox = new JComboBox<>();
		comboBox.setFont(Const.TEXT_FONT);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// add label1 to topPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = Const.BOTTOM_RIGHT_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_START;
		topPanel.add(label1, gbc);

		// add comboBox to topPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.BOTTOM_MARGIN;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(comboBox, gbc);

		// add removeBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.anchor = GridBagConstraints.LINE_END;
		buttonPanel.add(removeBtn, gbc);

		// add cancelBtn to buttonPanel
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonPanel.add(cancelBtn, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		// event listeners
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				removeTriggered(e);
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
