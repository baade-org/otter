package org.baade.otter.core.session;

import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public interface ISessionRecycle {

	
	public void put(ISession session);

	public ISession get(SocketChannel socketChannel);
	
	public ISession get(SocketAddress socketAddress);
	
	public void remove(ISession session);
}
