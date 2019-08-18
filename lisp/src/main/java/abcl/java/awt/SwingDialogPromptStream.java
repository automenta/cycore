package abcl.java.awt;

import abcl.java.DialogPromptStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingDialogPromptStream extends DialogPromptStream {

	JDialog dialog = new JDialog((Frame)null, true);
	private JLabel prompt = new JLabel();
	private JTextField input = new JTextField(32);
	
	public SwingDialogPromptStream() {
		this("Prompt");
	}
	
	public SwingDialogPromptStream(String title) {
		super();
		dialog.setTitle(title);
		JPanel tmpPanel = new JPanel();
		tmpPanel.add(prompt);
		tmpPanel.add(input);
		dialog.add(tmpPanel);
		JButton okBtn = new JButton("Ok");
		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				synchronized(dialog) {
					dialog.dispose();
				}
			}
		});
		tmpPanel = new JPanel(new FlowLayout());
		tmpPanel.add(okBtn);
		dialog.add(tmpPanel, BorderLayout.SOUTH);
	}
	
	@Override
	protected void closeDialog() {
		dialog.dispose();
	}
	
	@Override
	protected String readInputFromModalDialog(String promptText) {
		prompt.setText(promptText);
		dialog.pack();
		dialog.setVisible(true);
		return input.getText();
	}
	
}
