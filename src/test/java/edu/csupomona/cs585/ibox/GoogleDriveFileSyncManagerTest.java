package edu.csupomona.cs585.ibox;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.Test;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class GoogleDriveFileSyncManagerTest {

	private static final File file = new File("/res-folder/Desert.jpg");

	private GoogleDriveFileSyncManager gdfsm;

	private Exception exception;

	private boolean flag;

	public GoogleDriveFileSyncManagerTest() {

		gdfsm = mock(GoogleDriveFileSyncManager.class);
	}

	@Test
	public void testAddFile() {

		try {
			gdfsm.addFile(file);

			exception = null;

			flag = true;
		} catch (Exception e) {
			exception = e;

			flag = false;
		}

		assertTrue("addFile(File) has failed. " + exception, flag);
	}

	@Test
	public void testUpdateFile() {

		try {
			gdfsm.updateFile(file);

			exception = null;

			flag = true;
		} catch (Exception e) {
			exception = e;

			flag = false;
		}

		assertTrue("updateFile(File) has failed. " + exception, flag);
	}

	@Test
	public void testDeleteFile() {

		try {
			gdfsm.deleteFile(file);

			exception = null;

			flag = true;
		} catch (Exception e) {
			exception = e;

			flag = false;
		}

		assertTrue("deleteFile(File) has failed. " + exception, flag);
	}

	@Test
	public void testGetFileId() {

		flag = gdfsm.getFileId(file.getName()) == null;

		assertTrue("getFileId(String) has failed.", flag);
	}
}
