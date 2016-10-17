package org.baade.otter.server;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface IServerAccepter {

	/**
	 * 绑定 IP 套接字地址（IP 地址 + 端口号）
	 * 
	 * @param InetSocketAddr
	 */
	public void bind(InetSocketAddress inetSocketAddr) throws IOException;

	/**
	 * 绑定 IP 套接字地址
	 * @param port 端口
	 */
	public void bind(int port) throws IOException ;

	/**
	 * 绑定 IP 套接字地址
	 * @param hostname 主机名
	 * @param port 端口
	 */
	public void bind(String hostname, int port) throws IOException ;
	
	/**
	 * 开始运行服务端
	 */
	public void start() throws IOException ;
	
	/**
	 * 是否阻塞
	 * @param isBlock 是否阻塞
	 */
	public void configureBlocking(boolean isBlock) throws IOException ;
}
