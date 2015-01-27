package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WatchDirTest {

	private Drive mockDrive = mock(Drive.class, Mockito.RETURNS_DEEP_STUBS);

	private GoogleDriveFileSyncManager g = new GoogleDriveFileSyncManager(
			mockDrive);

	@Test
	public void test1AddFile() throws IOException {

		java.io.File file = new java.io.File("/res-folder/.jpg");

		// File body = new File();
		// body.setTitle(file.getName());

		// when(mockDrive.files().insert(new File(), mediaContent).execute());
		g.addFile(file);

	}
}
