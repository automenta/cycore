package org.jpl7.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingGadget extends JFrame {
	private static final long serialVersionUID = 1L;

	public int numClicks = 0; // can be changed e.g. from Prolog

	private static final String labelPrefix = "Number of button clicks: ";
	final JLabel label = new JLabel(labelPrefix + "0    ");

	public SwingGadget(String caption) {
		super(caption); // call the JFrame contructor

		JButton button = new JButton("I'm a Swing button!");

		button.addActionListener(e -> inc());

		label.setLabelFor(button);

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		pane.setLayout(new GridLayout(0, 1));
		pane.add(button);
		pane.add(label);

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}

		getContentPane().add(pane, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				setVisible(false);
			}
		});
		pack();
		setVisible(true);
	}

	public synchronized void inc() {
		numClicks++;
		label.setText(labelPrefix + numClicks);
		notifyAll();
	}

	public synchronized boolean dec() {
		try {
			while (numClicks <= 0) {
				wait();
			}
			numClicks--;
			label.setText(labelPrefix + numClicks);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}
}
