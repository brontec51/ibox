package edu.csupomona.cs585.ibox;

public class WatchDirThread extends Thread {

	WatchDir watchDir;

	public void start(WatchDir watchDir) {

		this.watchDir = watchDir;

		this.run();
	}

	public void run() {
		watchDir.processEvents();
	}
}
