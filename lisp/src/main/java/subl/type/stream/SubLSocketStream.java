/* For LarKC */
package subl.type.stream;

import abcl.SocketStream;
import abcl.Stream;
import subl.Errors;

import java.io.IOException;
import java.net.*;

public class SubLSocketStream extends SocketStream implements SubLOutputBinaryStream, SubLInputBinaryStream {

    public SubLSocketStream(Socket socket, Stream in, Stream out)
    {
	super(in, out);
        this.socket = socket;
    }

	SubLSocketStream(Socket socket) {
		super();
		this.socket = socket;
		host = socket.getInetAddress().getCanonicalHostName();
	    setDefaultSocketOptions(socket);
		init();
	}

	SubLSocketStream(String host, int port, int timeout) {
		super();
		if (port < 0 || port >= 65536)
			Errors.error("Got invalid port number: " + port);
		try {
			this.host = host;
			SocketAddress sockAddr = new InetSocketAddress(host, port);
			(socket = new Socket()).connect(sockAddr, timeout);
			setDefaultSocketOptions(socket);
		} catch (UnknownHostException uhe) {
			Errors.error("Got unknown host: " + host, uhe);
		} catch (IOException ioe) {
			Errors.error("Unable to open socket " + host + "@" + port + ".", ioe);
		}
		init();
	}

	private static void setDefaultSocketOptions(Socket socket)
	{
		try
		{
			socket.setSoLinger(true, 50);
			socket.setReceiveBufferSize(4*1024*1024);
			socket.setTcpNoDelay(true);
			socket.setSoTimeout(250);
		} catch (SocketException ioe)
		{
			Errors.error("Unable to setup socket " + socket + ".", ioe);
		}
	}

	public static void main(String[] args) {
	}

	//private Socket socket;
	private String host;

	@Override
	public synchronized void close() {
		if (isClosed())
			return;
		try {
			if (socket != null) {
				try {
					if (!socket.isInputShutdown())
						socket.shutdownInput();
				} catch (Exception ex) {
				}
				try {
					if (!socket.isOutputShutdown())
						socket.shutdownOutput();
				} catch (Exception ex2) {
				}
				socket.close();
				socket = null;
			}
		} catch (IOException ex3) {
		}
		super.close();
	}

	@Override
	public String printObjectImpl() {
		checkUnreadableOk();
		return "#<SOCKET-STREAM " + host + ":" + (socket == null ? "INVALID SOCKET" : "" + socket.getPort())
				+ (isClosed() ? " CLOSED" : " OPEN") + " @ " + superHash() + ">";
	}

	protected void init() {
		try {
			if(inStream==null)inStream = (Stream)SubLStreamFactory.makeInputTextStream(socket.getInputStream());
			if(outStream==null)outStream = (Stream)SubLStreamFactory.makeOutputTextStream(socket.getOutputStream());
			//if(inStream!=null && getStreamElementType()==null) setStreamElementType(inStream.getStreamElementType());
			if(inStream!=null && getElementType()==null) setElementType(inStream.getElementType());
		} catch (IOException ioe) {
			Errors.error("Error trying to establish socket input/output streams.", ioe);
		}
	}
}
