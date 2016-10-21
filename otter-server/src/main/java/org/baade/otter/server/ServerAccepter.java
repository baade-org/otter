package org.baade.otter.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.baade.otter.core.disposer.IEventDisposer;
import org.baade.otter.core.session.ISession;
import org.baade.otter.core.session.ISessionRecycle;
import org.baade.otter.core.session.Session;
import org.baade.otter.core.session.SessionRecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerAccepter implements IServerAccepter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerAccepter.class);

	private ServerSocketChannel serverSocketChannel;

	private boolean isBlock = false;
	
	private ISessionRecycle sessionRecycle;
	
	private IEventDisposer eventDisposer;

	private Selector selector;

	public ServerAccepter() throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(isBlock);
		sessionRecycle = new SessionRecycle();
	}

	@Override
	public void bind(int port) throws IOException {
		bind(new InetSocketAddress(port));
	}

	@Override
	public void bind(InetAddress localInetAddress, int port) throws IOException {
		SocketAddress localSocketAddress = new InetSocketAddress(localInetAddress, port);
		bind(localSocketAddress);
	}

	@Override
	public void bind(SocketAddress localSocketAddress) throws IOException {
		if(localSocketAddress == null){
			throw new IllegalArgumentException("Local SocketAddress is null.");
		}
		if(this.eventDisposer == null){
			throw new IllegalArgumentException("Event Disposer is not set.");
		}
		
		selector = Selector.open();
		serverSocketChannel.bind(localSocketAddress);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		LOGGER.info("ServerAccepter starting and lisntened on [{}] bloking is [{}]. ", localSocketAddress.toString(),
				this.isBlock);
		while (true) {
			keysHandler();
		}
	}

	private void keysHandler() throws IOException {
		selector.select();
		Set<SelectionKey> keys = selector.selectedKeys();
		Iterator<SelectionKey> itor = keys.iterator();

		while (itor.hasNext()) {
			SelectionKey key = itor.next();
			itor.remove();
			if (key.isValid() == false) {
				continue;
			} else {
				if (key.isAcceptable()) {
					ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
					SocketChannel socketChannel = ssc.accept();
					ISession session = newSession(socketChannel);
					sessionRecycle.put(session);
				} else if (key.isReadable()) {
					SocketChannel socketChannel = (SocketChannel)key.channel();
					ISession session = sessionRecycle.get(socketChannel);
					if(session != null){
						session.read();
					}
				}
			}

		}
	}
	
	private ISession newSession(SocketChannel socketChannel) throws IOException{
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
		return new Session(socketChannel, this.eventDisposer, sessionRecycle);
	}
	

	@Override
	public void setDisposer(IEventDisposer disposer) {
		this.eventDisposer = disposer;
	}
}
