package org.baade.otter.core.session;

import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

import org.baade.otter.core.filter.IFilterChain;

public class Session implements ISession {

	private SocketChannel socketChannel;
	
	private IFilterChain filterChain;
	
	public Session(SocketChannel socketChannel){
		this.socketChannel = socketChannel;
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String attrKey, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAttribute(String attrKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SocketChannel getChannel() {
		return this.socketChannel;
	}

	@Override
	public Socket getSocket() {
		Socket socket = null;
		SocketChannel sc = getChannel();
		if(sc != null){
			socket = sc.socket();
		}
		return socket;
	}


	@Override
	public boolean isConnected() {
		boolean isConnected = false;
		SocketChannel sc = getChannel();
		if(sc != null){
			isConnected = sc.isConnected();
		}
		return isConnected;
	}

	@Override
	public SocketAddress getLocal() {
		SocketAddress local = null;
		Socket socket = getSocket();
		if(socket != null){
			local = socket.getLocalSocketAddress();
		}
		return local;
	}

	@Override
	public SocketAddress getRemote() {
		SocketAddress remote = null;
		Socket socket = getSocket();
		if(socket != null){
			remote = socket.getRemoteSocketAddress();
		}
		return remote;
	}
	
}
