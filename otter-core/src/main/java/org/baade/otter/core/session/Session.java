package org.baade.otter.core.session;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.baade.otter.core.disposer.IEventDisposer;
import org.baade.otter.core.filter.IFilterChain;

public class Session implements ISession {

	private SocketChannel socketChannel;
	
	private IFilterChain filterChain;
	
//	private SelectionKey key;
	
	private IEventDisposer disposer;
	
	private ISessionRecycle sessionRecycle;
	
	public Session(SocketChannel socketChannel,
			IEventDisposer disposer, ISessionRecycle sessionRecycle){
		this.socketChannel = socketChannel;
//		this.key = key;
		this.disposer = disposer;
		this.sessionRecycle = sessionRecycle;
		disposer.onCreate(this);
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

	@Override
	public void read(){
		ByteBuffer buff = ByteBuffer.allocate(256);
		try {
			if(this.socketChannel.isConnected() && this.socketChannel.read(buff) != -1){
				String str = new String(buff.array());
				disposer.onReceived(this, str);
			}
		} catch (IOException e) {
			disposer.onException(this, e);
			close();
		}
	}

	@Override
	public void close() {
		disposer.onClose(this);
		sessionRecycle.remove(this);
//		if(this.key != null){
//			key.cancel();
//		}
		if(this.socketChannel != null){
			try {
				this.socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public String toString() {
		return this.socketChannel.toString();
	}

	@Override
	public void write(){
		// TODO Auto-generated method stub
		
	}
}
