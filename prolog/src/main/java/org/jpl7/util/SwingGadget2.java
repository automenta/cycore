package org.jpl7.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SwingGadget2 extends JFrame {
	private static final long serialVersionUID = 1L;
	private Qev head;
	private Qev tail;
	public final JPanel pane;

	public ActionListener al = e -> inc(e);

	public SwingGadget2(String caption) {
		super(caption); // call the JFrame contructor
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		pane = new JPanel();
		getContentPane().add(pane, BorderLayout.CENTER);

		// JButton button1 = new JButton("click me");
		// JButton button2 = new JButton("...or me");

		// button1.addActionListener( al);
		// button2.addActionListener( al);

		// pane.add(button1);
		// pane.add(button2);

		pack();
		setVisible(true);
	}

	public synchronized void inc(Object e) {
		if (head == null) {
			head = new Qev(e);
			head.next = null;
			tail = head;
		} else {
			tail.next = new Qev(e);
			tail = tail.next;
		}
		notifyAll();
	}

	public synchronized Object dec() {
		Qev t;

		try {
			while (head == null) {
				wait();
			}
			t = head;
			head = head.next;
			return t.ev;
		} catch (InterruptedException e) {
			return null;
		}
	}

	public static class Qev {
		public final Object ev;
		public Qev next;

		public Qev(Object e) {
			ev = e;
		}
	}
}
