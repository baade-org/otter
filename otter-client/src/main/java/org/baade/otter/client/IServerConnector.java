package org.baade.otter.client;

import java.io.IOException;
import java.net.SocketAddress;

public interface IServerConnector {

	public void connect(String remoteAddress, int remotePort) throws IOException;

	public void connect(SocketAddress remote) throws IOException;
}
