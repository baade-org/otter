package org.baade.otter.core.session;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class SessionRecycle implements ISessionRecycle{
	
	private Map<SocketAddress, ISession> allSessions;
	
	public SessionRecycle(){
		allSessions = new HashMap<SocketAddress, ISession>();
	}
	

	@Override
	public void put(ISession session) {
		if(session.isConnected()){
			SocketAddress remote = session.getRemote();
			if(remote != null){
				allSessions.put(remote, session);
			}else{
				//提示
			}
		}else{
			//已经断开连接
		}
	}

}
