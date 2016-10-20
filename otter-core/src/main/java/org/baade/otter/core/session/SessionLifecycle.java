package org.baade.otter.core.session;

public class SessionLifecycle implements ISessionLifecycle {
	
	private long lastReadTime;
	private long lastWriteTime;
	private long lastUpdateTime;
	
	public SessionLifecycle(){
		long currentTime = System.currentTimeMillis();
		this.lastReadTime = currentTime;
		this.lastWriteTime = currentTime;
		this.lastUpdateTime = currentTime;
	}
	

	@Override
	public long getLastReadTime() {
		return this.lastReadTime;
	}

	@Override
	public long getLastWriteTime() {
		return this.lastWriteTime;
	}

	@Override
	public void setLastReadTime(long time) {
		this.lastReadTime = time;
	}

	@Override
	public void setLastWriteTime(long time) {
		this.lastWriteTime = time;
	}

	@Override
	public long setLastUpdateTime() {
		return this.lastUpdateTime;
	}

	@Override
	public void getLastUpdateTime(long time) {
		this.lastUpdateTime = time;
	}

}
