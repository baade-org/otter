package org.baade.otter.core.session;

public interface ISessionLifecycle {

	public long getLastReadTime();

	public void setLastReadTime(long time);

	public long getLastWriteTime();

	public void setLastWriteTime(long time);

	public long setLastUpdateTime();

	public void getLastUpdateTime(long time);

}
