package org.baade.otter.server;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		ServerAccepter sa = new ServerAccepter();
		sa.bind(9090);
	}
}
