package org.baade.otter.core.session;

import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public interface ISession {

	public String getId();
	
	public void setAttribute(String attrKey, Object value);
	
	public Object getAttribute(String attrKey);
	
	
	public SocketChannel getChannel();
	
	public Socket getSocket();
	
	public boolean isConnected();
	
	public SocketAddress getLocal();
	
	public SocketAddress getRemote();
	
}
