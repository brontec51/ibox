package edu.csupomona.cs585.ibox;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.Test;

public class WatchDirTest {

	private WatchDir wd;

	public WatchDirTest() throws IOException {

		Path dir = Paths.get("res-folder/dir");

		WatchService service = FileSystems.getDefault().newWatchService();

		WatchKey key = dir.register(service, ENTRY_CREATE, ENTRY_DELETE,
				ENTRY_MODIFY);

		wd = new WatchDir(dir, new FileSyncManagerImp());
	}

	@Test
	public void testProcessEvent() throws IOException {

		wd.processEvents();
	}
}
