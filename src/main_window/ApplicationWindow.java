package main_window;

import custom.CustomFrame;
import custom.Log;
import global.Const;
import global.Shared;
import main_window.add.AddJoke;
import main_window.add.AddQuote;
import main_window.add.response.AddCompliments;
import main_window.add.response.AddFarewell;
import main_window.add.response.AddGratitude;
import main_window.add.response.AddGreetings;
import main_window.brain.ProcessInput;
import main_window.help.About;
import main_window.help.Commands;
import main_window.help.Credits;
import main_window.remove.RemoveJoke;
import main_window.remove.RemoveQuote;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

public class ApplicationWindow {
	private static JTextArea output;
	private JScrollPane scrollPane;
	private JTextField input;
	private JButton send;
	private GridBagConstraints gbc;
	private JFrame frame;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JMenu file, settings, help, modify;
	private JMenuItem clearChat, exit, changeUserName, changeBotName, about, commands, credits,
			addJoke, addQuote, addCompliment, addGreeting, addGratitude,
			addFarewell, removeJoke, removeQuote;
	private JSeparator separator;

	private Dimension inputSize, sendSize;
	private Border border;
	private CompoundBorder compound;

	private String userInput = "";

	public ApplicationWindow () {
		initComponents();
	}

	// get the user's name
	// this will check if the user has entered a valid input
	private static void setUserName () {
		try {
			do {
				Shared.setUserName(JOptionPane.showInputDialog(
						null,
						"What is your name?",
						"Set your name",
						JOptionPane.QUESTION_MESSAGE
				));

				if (Shared.getUserName().trim().equals("")) {
					JOptionPane.showMessageDialog(
							null,
							"Please enter your name!",
							"Invalid name",
							JOptionPane.ERROR_MESSAGE
					);
				}
			} while (Shared.getUserName().trim().equals(""));
		} catch (NullPointerException ob) {
			// if the window is closed suddenly, NullPointerException will be generated
			Log.i(ApplicationWindow.class, "Window closed abruptly. Closing application");
		}

		Log.i(ApplicationWindow.class, "Username: " + Shared.getUserName());
	}

	// get the bot's name
	// this will keep checking for valid input
	private static void setBotName () {
		try {
			do {
				Shared.setBotName(JOptionPane.showInputDialog(
						null,
						"What is my name?",
						"Set bot's name",
						JOptionPane.QUESTION_MESSAGE
				));

				if (Shared.getBotName().trim().equals("")) {
					JOptionPane.showMessageDialog(
							null,
							"Please enter my name!",
							"Invalid name",
							JOptionPane.ERROR_MESSAGE
					);
				}
			} while (Shared.getBotName().trim().equals(""));

		} catch (NullPointerException ob) {
			// if the window isclose suddenly, NullPointerException will be thown
			// this will print
			Log.i(ApplicationWindow.class, "Window closed abruptly. Closing application");
		}

		Log.i(ApplicationWindow.class, "Bot name: " + Shared.getBotName());
	}

	// get reference to output
	public static JTextArea getOutput () {
		return output;
	}

	// remove punctuation, whitespace and capitalization
	// replace all possible punctuation and spacing between words to ""
	// sample: This! is a    test? -> thisisatest
	private String modify (String str) {
		return str.toLowerCase().replaceAll("[ , . ! \' ? \\s \" ]", "").trim();
	}

	// this merely appends the user's name to the front of their input
	private void userMessage (String str) {
		output.append(Shared.getUserName() + ": " + str + "\n");
	}

	// send user input to brain
	private void sendInput () {
		// set userInput to formatted string
		userInput = input.getText();

		// check if input is empty
		// if it is empty, the text will not be sent to the brain
		if (!input.getText().trim().equals("")) {
			// append user input into output
			userMessage(userInput);

			// empty input
			input.setText("");

			// pass userInput to brain
			ProcessInput.processingInput(modify(userInput));
		}
	}

	// <editor-fold defaultstate="collapsed" desc="Event Handlers">

	// handles when the user presses a user key when focus is on input
	// ENTER -> triggers sendInput()
	// UP -> show previously typed word
	private void inputTriggered (KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			sendInput();
		} else if (event.getKeyCode() == KeyEvent.VK_UP) {
			input.setText(userInput);
		}
	}

	// handles when the user presses send
	private void sendPressed (ActionEvent event) {
		sendInput();
	}

	// handles when the user presses ctrl + e or uses the menu option
	// uses System.exit(0) to ensure that the frame is closed
	private void exitTriggered (ActionEvent event) {
		// dispose this frame
		frame.dispose();
		System.exit(0);
	}

	// clearing the chat
	private void clearChatTriggered (ActionEvent event) {
		output.setText("");
	}

	// changing the user name
	private void changeUserNameTriggered (ActionEvent event) {
		setUserName();
	}

	// changing the bot name
	private void changeBotNameTriggered (ActionEvent event) {
		setBotName();
	}

	// adding to jokes.txt and jokes_ans.txt
	// creating an object of type AddJoke
	// causes the AddJoke GUI to appear
	private void addJokeTriggered (ActionEvent event) {
		new AddJoke("Joke", "Punchline", "Add Joke");
	}

	// adding to quotes.txt and quoters.txt
	// creating an object of type AddQuote
	// causes the AddQuote GUI to appear
	private void addQuoteTriggered (ActionEvent event) {
		new AddQuote("Quote", "Quoter", "Add Quote");
	}

	// adding to greetings.txt
	// creates an object of type AddGreetings
	// causes the AddGreetings GUI to appear
	private void addGreetingTriggered (ActionEvent event) {
		new AddGreetings("Add greetings", "Greetings");
	}

	// adding to farewell.txt
	// creates an object of type AddGreetings
	// causes the AddGreetings GUI to appear
	private void addFarewellTriggered (ActionEvent event) {
		new AddFarewell("Add farewells", "Farewell");
	}

	// adding to gratitude.txt
	// creates an object of type AddGratitude
	// causes the AddGratitude GUI to appear
	private void addGratitudeTriggered (ActionEvent event) {
		new AddGratitude("Add gratitudes", "Gratitude");
	}

	// adding to compliemnts.txt
	// create an object of type AddCompliments
	// causes the AddCompliements GUI to appear
	private void addComplimentTriggered (ActionEvent event) {
		new AddCompliments("Add compliments", "Compliments");
	}

	// removing from jokes.txt and jokes_ans.txt
	private void removeJokeTriggered (ActionEvent event) {
		new RemoveJoke("Joke", "Remove Joke");
	}

	// removing from quotes.txt and quoters.txt
	private void removeQuoteTriggered (ActionEvent event) {
		new RemoveQuote("Quote", "Remove Quote");
	}

	// shows the list of available commands
	private void commandsTriggered (ActionEvent event) {
		new Commands();
	}

	// shows the about screen
	private void aboutTriggered (ActionEvent event) {
		new About();
	}

	// shows the credits screen
	private void creditsTriggered (ActionEvent event) {
		new Credits();
	}

	// </editor-fold>

	// creating the window
	private void initComponents () {
		// initialize JFrame using CustomFrame constructor
		frame = new CustomFrame(
				"Chat bot", Const.APPLICATION_WIDTH, Const.APPLICATION_HEIGHT,
				true, JFrame.EXIT_ON_CLOSE
		);

		// initialize components
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBorder(Const.WINDOW_PADDING); //this is the window padding
		// add mainPanel to frame
		Container c = frame.getContentPane();
		c.add(mainPanel); // adding mainPanel to frame

		border = new LineBorder(Color.BLACK, 1, false);
		compound = new CompoundBorder(
				border,
				new EmptyBorder(10, 10, 10, 10)
		);

		// output settings
		output = new JTextArea();
		output.setFont(Const.TEXT_FONT);
		output.setEditable(false); // do not allow the user to edit the text displayed on the screen
		output.setLineWrap(true);
		output.setFocusable(false); // prevents the focus from shifting away from input
		output.setWrapStyleWord(true);
		// ensure that the scrollPane always scrolls down to the bottom
		// when the text exceeds the viewable limit
		DefaultCaret caret = (DefaultCaret) output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		output.setBorder(new EmptyBorder(
				10, 10, 10, 10
		));

		// input settings
		input = new JTextField();
		input.setFont(Const.TEXT_FONT);
		input.setMargin(Const.INPUT_MARGIN); // add some internal padding for readability

		// send settings
		send = new JButton("Send");
		send.setFocusable(false); // prevents the focus from shifting away from input
		send.setFont(Const.BUTTON_FONT);

		// scrollPane settings
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(output);
		scrollPane.setBorder(new LineBorder(
				Color.BLACK, 1, false
		));

		// file settings
		file = new JMenu("File");
		clearChat = new JMenuItem("Clear Chat");
		// setAccelerator -> adds a keyboard shortvut that can trigger the menu option
		clearChat.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK)
		);
		exit = new JMenuItem("Exit");
		exit.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_E, InputEvent.CTRL_MASK)
		);

		separator = new JSeparator(JSeparator.HORIZONTAL);

		file.add(clearChat);
		file.add(separator);
		file.add(exit);

		// settings settings
		settings = new JMenu("Settings");
		changeUserName = new JMenuItem("Change your name");
		changeBotName = new JMenuItem("Change my name");

		settings.add(changeUserName);
		settings.add(changeBotName);

		// help settings
		help = new JMenu("Help");
		about = new JMenuItem("About");
		about.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK)
		);

		commands = new JMenuItem("Commands");
		commands.setAccelerator(
				KeyStroke.getKeyStroke(
						KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.ALT_MASK)
		);

		credits = new JMenuItem("Credits");

		help.add(about);
		help.add(commands);
		help.add(credits);

		// modify settings
		modify = new JMenu("Modify");
		addJoke = new JMenuItem("Add joke");
		addQuote = new JMenuItem("Add quote");
		addCompliment = new JMenuItem("Add compliment");
		addFarewell = new JMenuItem("Add farewell");
		addGratitude = new JMenuItem("Add gratitude");
		addGreeting = new JMenuItem("Add greeting");

		removeJoke = new JMenuItem("Remove joke");
		removeQuote = new JMenuItem("Remove quote");

		separator = new JSeparator(JSeparator.HORIZONTAL);

		modify.add(addJoke);
		modify.add(addQuote);
		modify.add(addGreeting);
		modify.add(addFarewell);
		modify.add(addGratitude);
		modify.add(addCompliment);
		modify.add(separator);
		modify.add(removeJoke);
		modify.add(removeQuote);

		// menuBar settings
		menuBar = new JMenuBar();
		menuBar.add(file);
		menuBar.add(settings);
		menuBar.add(modify);
		menuBar.add(help);
		frame.setJMenuBar(menuBar);

		// <editor-fold defaultstate="collapsed" desc="GridBagConstraints"

		// adding output
		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(0, 0, 5, 0);
		mainPanel.add(scrollPane, gbc);

		// adding input
		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 0, 0, 10);
		mainPanel.add(input, gbc);

		// adding send
		gbc = new GridBagConstraints();
		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 0, 0, 0);
		mainPanel.add(send, gbc);

		// </editor-fold>

		// resizing components
		inputSize = input.getPreferredSize();
		sendSize = send.getPreferredSize();
		inputSize.height = sendSize.height;
		input.setPreferredSize(
				new Dimension(inputSize.width, inputSize.height)
		);
		send.setPreferredSize(
				new Dimension(sendSize.width, sendSize.height)
		);


		// <editor-fold defaultstate="collapsed" desc="Event Listeners">

		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed (KeyEvent e) {
				inputTriggered(e);
			}
		});

		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				sendPressed(e);
			}
		});

		// file menu options
		clearChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				clearChatTriggered(e);
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				exitTriggered(e);
			}
		});

		// settings menu options
		changeUserName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				changeUserNameTriggered(e);
			}
		});

		changeBotName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				changeBotNameTriggered(e);
			}
		});

		// modify menu options
		addJoke.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addJokeTriggered(e);
			}
		});

		addQuote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addQuoteTriggered(e);
			}
		});

		addGreeting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addGreetingTriggered(e);
			}
		});

		addFarewell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addFarewellTriggered(e);
			}
		});

		addGratitude.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addGratitudeTriggered(e);
			}
		});

		addCompliment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				addComplimentTriggered(e);
			}
		});

		removeJoke.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				removeJokeTriggered(e);
			}
		});

		removeQuote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				removeQuoteTriggered(e);
			}
		});

		// help menu options
		commands.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				commandsTriggered(e);
			}
		});

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				aboutTriggered(e);
			}
		});

		credits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				creditsTriggered(e);
			}
		});

		// </editor-fold>

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main (String[] args) {
		Shared.setTheme();

		setUserName();
		setBotName();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run () {
				new ApplicationWindow();
			}
		});
	}
}
