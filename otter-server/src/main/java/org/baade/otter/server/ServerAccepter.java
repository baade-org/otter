package org.baade.otter.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

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

	// private ServerSocket serverSocket;
	
	private ISessionRecycle sessionRecycle;

	private Selector selector;

	public ServerAccepter() throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(isBlock);
		// serverSocket = serverSocketChannel.socket();
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
				if (key.isConnectable()) {
					System.out.println("key.isConnectable()");
				} else if (key.isAcceptable()) {
					 System.out.println("key.isAcceptable()");
					ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
					System.out.println(ssc);
					SocketChannel sc = ssc.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
//					sc.register(selector, SelectionKey.OP_WRITE);
					
					ISession session = new Session(sc);
					
					sessionRecycle.put(session);
					
				} else if (key.isReadable()) {
//					System.out.println("key.isReadable()");
					SocketChannel sc = (SocketChannel)key.channel();
					ByteBuffer buff = ByteBuffer.allocate(256);
					if(sc.read(buff) != -1){
						System.out.println(new String(buff.array()));
					}
					
				} else if (key.isWritable()) {
					System.out.println("key.isWritable()");
				}
			}

		}
	}
}
