package helper;

import custom.CustomFrame;
import custom.Log;
import global.Const;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*	Created by: Woo Jia Hao	 	 	Date: 29/07/2017 */

public class Helper {
	private JTree selection;
	private JTextArea output;
	private JMenuBar menuBar;
	private JScrollPane scrollPane;
	private JMenu file;
	private JMenuItem exit;
	private JFrame frame;
	private JPanel mainPanel;
	private GridBagConstraints gbc;

	private Dimension scrollPaneSize;

	private String fileName;

	public Helper () {
		initComponents();
	}

	// populating selection with the options
	// add a "header" node for each category
	// under each of the "header", add the individual nodes
	private void createNodes (DefaultMutableTreeNode top) {
		DefaultMutableTreeNode selection_option = null;
		DefaultMutableTreeNode selection_statements = null;
		DefaultMutableTreeNode loop_statements = null;
		DefaultMutableTreeNode others = null;

		// selection statments
		selection_statements = new DefaultMutableTreeNode("Selection statements");
		top.add(selection_statements);
		// if statement
		selection_option = new DefaultMutableTreeNode("if statement");
		selection_statements.add(selection_option);
		// switch statement
		selection_option = new DefaultMutableTreeNode("switch statement");
		selection_statements.add(selection_option);


		// loop statements
		loop_statements = new DefaultMutableTreeNode("Loop statements");
		top.add(loop_statements);
		// for loop
		selection_option = new DefaultMutableTreeNode("for loop");
		loop_statements.add(selection_option);
		// do-while loop
		selection_option = new DefaultMutableTreeNode("do-while loop");
		loop_statements.add(selection_option);
		// while loop
		selection_option = new DefaultMutableTreeNode("while loop");
		loop_statements.add(selection_option);

		// Others
		others = new DefaultMutableTreeNode("Others");
		top.add(others);

		selection_option = new DefaultMutableTreeNode("Arrays");
		others.add(selection_option);

		selection_option = new DefaultMutableTreeNode("Boolean Expressions");
		others.add(selection_option);

		selection_option = new DefaultMutableTreeNode("Casting");
		others.add(selection_option);

		selection_option = new DefaultMutableTreeNode("Classes");
		others.add(selection_option);

		selection_option = new DefaultMutableTreeNode("Methods");
		others.add(selection_option);

		selection_option = new DefaultMutableTreeNode("Variables");
		others.add(selection_option);
	}

	// <editor-fold defaultstate="collpased" desc="Event Handlers">

	// when the user presses ctrl+e or exit menu option
	// dispose the frame, leave ApplicationWindow open
	private void exitTriggered (ActionEvent event) {
		frame.dispose();
	}

	// handles when the user selects a particular node
	// gets the current selected node
	// uses the name of the leaf to find the file within the assets folder
	private void nodeTriggered (TreeSelectionEvent event) {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
				selection.getLastSelectedPathComponent();
		String currentLine = "";

		// if selectedNode == null, do nothing
		if (selectedNode == null) {
			return;
		}

		// only respond when selectedNode is a leaf
		// file names correspond to the node name for easier reference
		if (selectedNode.isLeaf()) {
			Log.i(getClass(), "Selection: " + selectedNode);
			fileName = "assets/text_files/helper/" + selectedNode.toString() + ".txt";

			// clear output for new text
			output.setText("");

			// open the file
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				// read from file
				while ((currentLine = br.readLine()) != null) {
					// display contents of file to output
					output.append(currentLine + "\n");
				}
			} catch (IOException ob) {
				Log.i(getClass(), "File could not be found");
			}
		}
	}

	// </editor-fold>

	// initializing the window
	private void initComponents () {
		// initialize frame
		frame = new CustomFrame(
				"Java Helper", Const.HELPER_WIDTH, Const.HELPER_HEIGHT,
				false, JFrame.DISPOSE_ON_CLOSE
		);

		// initialize all components
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBorder(Const.WINDOW_PADDING);
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		Border border = new LineBorder(Color.BLACK, 1, false);
		CompoundBorder compound = new CompoundBorder(
				border,
				new EmptyBorder(10, 10, 10, 10));

		// selection
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Java Help");
		createNodes(top);
		selection = new JTree(top);
		selection.setRootVisible(false); // only needs to view the header for each category
		selection.setBorder(compound);
		selection.setFont(Const.TEXT_FONT);
		// allow the user to only select one node at a time
		selection.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION
		);

		// output
		output = new JTextArea();
		output.setEditable(false);
		output.setFont(Const.CODE_FONT);
		output.setBorder(compound);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);

		// prevent the scrollpane from scrolling to the bottom automatically
		// keeps the view on the top of the text area
		DefaultCaret caret = (DefaultCaret) output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

		// scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(output);

		// files
		file = new JMenu("File");
		exit = new JMenuItem("Exit");
		exit.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_E, InputEvent.CTRL_MASK)
		);
		file.add(exit);

		// menuBar
		menuBar = new JMenuBar();
		menuBar.add(file);
		frame.setJMenuBar(menuBar);

		// resizing components
		// this is done to prevent the scrollPane from dynamically auto resizing
		// when nodes are being selected and unselected
		scrollPaneSize = scrollPane.getPreferredSize();
		scrollPaneSize.width = 300;
		scrollPane.setPreferredSize(
				new Dimension(scrollPaneSize.width, scrollPaneSize.height)
		);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints">

		// adding selection
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = Const.RIGHT_MARGIN;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(selection, gbc);

		// adding output
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 3;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LINE_END;
		mainPanel.add(scrollPane, gbc);

		// </editor-fold>

		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				exitTriggered(e);
			}
		});

		selection.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged (TreeSelectionEvent e) {
				nodeTriggered(e);
			}
		});

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
