package org.baade.otter.core.disposer;

import org.baade.otter.core.session.ISession;

public abstract class AbstractEventDisposer implements IEventDisposer {
	

	@Override
	public void onCreate(ISession session) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClose(ISession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(ISession session, Exception exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSent(ISession session, Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceived(ISession session, Object obj) {
		// TODO Auto-generated method stub

	}

}
