package org.baade.otter.client;

import java.io.IOException;

public class Test {
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		IServerConnector sc = new ServerConnector();
		
//		sc.connect("127.0.0.1", 9090);
		sc.connect("192.168.1.140", 9090);
	}

}
