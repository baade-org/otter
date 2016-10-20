package org.baade.otter.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerConnector implements IServerConnector {

	private SocketChannel socketChannel;

	@Override
	public void connect(String remoteAddress, int remotePort) throws IOException {
		 InetSocketAddress remoteInetAddr = new InetSocketAddress(remoteAddress, remotePort);
		 connect(remoteInetAddr);
	}

	@Override
	public void connect(SocketAddress remote) throws IOException {
		socketChannel = SocketChannel.open(remote);
		ByteBuffer buff = ByteBuffer.wrap("hello Server 你好!".getBytes());
		socketChannel.write(buff);
		
	}

}
