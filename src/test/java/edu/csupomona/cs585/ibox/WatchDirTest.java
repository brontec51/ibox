package edu.csupomona.cs585.ibox;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchService;

import org.junit.Test;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;

public class WatchDirTest {

	private Path mPath = mock(Path.class);

	private FileSyncManager mfsm = mock(FileSyncManager.class);

	private WatchDir wd;

	@Test
	public void testWatchDir() throws IOException {

		wd = new WatchDir(mPath, mfsm);

		verify(mPath).register(isA(WatchService.class), ENTRY_CREATE,
				ENTRY_DELETE, ENTRY_MODIFY);
	}

	@Test
	public void testProcessEvent() {
		wd.processEvents();
	}
}
