package org.baade.otter.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerAccepter implements IServerAccepter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerAccepter.class);

	private ServerSocketChannel serverSocketChannel;

	private boolean isBlock = false;

	// private ServerSocket serverSocket;

	private Selector selector;

	public ServerAccepter() throws IOException {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(isBlock);
		// serverSocket = serverSocketChannel.socket();
	}

	@Override
	public void bind(InetSocketAddress inetSocketAddr) throws IOException {
		serverSocketChannel.bind(inetSocketAddr);
	}

	@Override
	public void bind(int port) throws IOException {
		serverSocketChannel.bind(new InetSocketAddress(port));
	}

	@Override
	public void bind(String hostname, int port) throws IOException {
		serverSocketChannel.bind(new InetSocketAddress(hostname, port));
	}

	@Override
	public void start() throws IOException {
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		LOGGER.info("ServerAccepter starting and bloking is [{}]. ", this.isBlock);
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
					
				} else if (key.isAcceptable()) {

				} else if (key.isReadable()) {

				} else if (key.isWritable()) {

				}
			}

		}
	}

	@Override
	public void configureBlocking(boolean isBlock) throws IOException {
		this.isBlock = isBlock;
		serverSocketChannel.configureBlocking(isBlock);
	}

	public static void main(String[] args) throws IOException {
		ServerAccepter sa = new ServerAccepter();
		sa.bind("127.0.0.1", 9090);
		sa.start();
	}
}
