package org.baade.otter.core.disposer;

import org.baade.otter.core.session.ISession;

public interface IEventDisposer {

	public void onCreate(ISession session);
	
	public void onClose(ISession session);
	
	public void onException(ISession session, Exception exception);
	
	public void onSent(ISession session, Object obj);
	
	public void onReceived(ISession session, Object obj);
}
