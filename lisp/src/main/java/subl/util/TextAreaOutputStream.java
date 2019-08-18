/* For LarKC */
package subl.util;

import subl.Errors;
import subl.SubLThread;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class TextAreaOutputStream extends OutputStream {
	public TextAreaOutputStream(JTextArea textArea, JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
		this.textArea = textArea;
	}

	public static void main(String[] args) {
	}

	private JTextArea textArea;
	private JScrollPane scrollPane;
	private boolean isScolledToBottom;

	private boolean isScolledToBottom() {
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		int currentValue = sb.getValue();
		int maxValue = sb.getMaximum();
		int extent = sb.getVisibleAmount();
		boolean isAtBottom = currentValue >= maxValue - extent;
		//if (!isAtBottom)int i = 0;
		return isAtBottom;
	}

	private void scrollTextAreaToBottom() {
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setValue(sb.getMaximum());
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		isScolledToBottom = true;
		try {
			SwingUtilities.invokeAndWait(new SafeRunnable() {
				@Override
				public void safeRun() {
					isScolledToBottom = TextAreaOutputStream.this.isScolledToBottom();
				}
			});
		} catch (InterruptedException ie) {
			SubLThread.currentThread().interrupt();
		} catch (Exception e) {
			Errors.handleError("Error while scrolling GUI read/eval loop panel.", e);
		}
		if (b == null)
			throw new NullPointerException();
		if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0)
			throw new IndexOutOfBoundsException();
		if (len == 0)
			return;
		textArea.append(new String(b, off, len));
		if (isScolledToBottom)
			SwingUtilities.invokeLater(new SafeRunnable() {
				@Override
				public void safeRun() {
					TextAreaOutputStream.this.scrollTextAreaToBottom();
				}
			});
	}

	@Override
	public void write(int b) throws IOException {
		textArea.append(String.valueOf((char) b));
	}
}
