package org.baade.otter.server;

import java.io.IOException;

import org.baade.otter.core.disposer.IEventDisposer;
import org.baade.otter.core.session.ISession;

public class Test {

	public static void main(String[] args) throws IOException {
		ServerAccepter sa = new ServerAccepter();
		Test.CommDisposer cd = new CommDisposer();
		
		sa.setDisposer(cd);
		
		sa.bind(9090);
	}
	
	public static class CommDisposer implements IEventDisposer{
		@Override
		public void onCreate(ISession session) {
			System.out.println("onCreate " + session);
		}

		@Override
		public void onClose(ISession session) {
			System.out.println("onClose " + session);
		}

		@Override
		public void onException(ISession session, Exception exception) {
			exception.printStackTrace();
			System.out.println("onException " + session + " -- " + exception);
		}

		@Override
		public void onSent(ISession session, Object obj) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onReceived(ISession session, Object obj) {
			System.out.println("onReceived " + session + " -- " + obj);
		}
		
	}
}
