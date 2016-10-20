package org.baade.otter.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * 
 * @author <a href="http://otter.baade.org">Baade Otter Project</a>
 *
 */
public interface IServerAccepter {
	
	/**
	 * 绑定 IP 套接字地址
	 * 
	 * @param port 端口号
	 */
	public void bind(int port) throws IOException;
	
	/**
	 * 绑定 IP 套接字地址（IP 地址 + 端口号）
	 * 
	 * @param localInetAddress
	 * @param port 端口号
	 */
	public void bind(InetAddress localInetAddress, int port) throws IOException;

	/**
	 * 绑定 IP 套接字地址（IP 地址 + 端口号）
	 * 
	 * @param localSocketAddress
	 */
	public void bind(SocketAddress localSocketAddress) throws IOException;
	
	
	
	

	
}
