package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;

public class WatchDirTest {

	private Path mPath = mock(Path.class);

	private FileSyncManager mfsm = mock(FileSyncManager.class);

	private WatchDir wd;

	public WatchDirTest() throws IOException {

		wd = new WatchDir(mPath, mfsm);
	}

	@Test
	public void testProcessEvent() {
		// wd.processEvents();
	}
}
