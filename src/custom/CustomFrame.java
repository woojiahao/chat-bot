package custom;

import javax.swing.*;
import java.awt.*;

/*	Created by: Woo Jia Hao	 	Date: 29/07/2017 */

// used to save time initializing all settings for the JFrame
public class CustomFrame extends JFrame {
	private int width, height, closeOperation;
	private boolean resizable;

	public CustomFrame (String title, int width, int height, boolean resizable,
						int closeOperation) throws HeadlessException {
		super(title);

		this.width = width;
		this.height = height;
		this.resizable = resizable;
		this.closeOperation = closeOperation;

		setDefaultCloseOperation(this.closeOperation);
		// setMinimumSize() used as pack() is called, since we want everything to fit
		setMinimumSize(new Dimension(this.width, this.height));
		setResizable(this.resizable);
	}

	public CustomFrame (String title, boolean resizable,
						int closeOperation) throws HeadlessException {
		super(title);

		this.resizable = resizable;
		this.closeOperation = closeOperation;

		setDefaultCloseOperation(this.closeOperation);
		setResizable(this.resizable);
	}
}
