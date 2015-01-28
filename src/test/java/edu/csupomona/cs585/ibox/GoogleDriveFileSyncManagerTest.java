package edu.csupomona.cs585.ibox;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.model.File;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class GoogleDriveFileSyncManagerTest {

	private GoogleDriveFileSyncManager gdfsm;

	Drive mDrive = mock(Drive.class);
	Files mFiles = mock(Files.class);
	// mock(Insert.class);
	File file = new File();
	java.io.File mFile = mock(java.io.File.class);

	AbstractInputStreamContent fileContent = new FileContent("*/*", mFile);

	Insert mInsert = mock(Insert.class);

	public GoogleDriveFileSyncManagerTest() throws IOException {

		gdfsm = new GoogleDriveFileSyncManager(mDrive);

		when(mDrive.files()).thenReturn(mFiles);
		when(mFiles.insert(file, fileContent)).thenReturn(mInsert);
		// when(mInsert.execute()).thenReturn(file);

		// File body = new File();
		// body.setTitle(localFile.getName());
		// FileContent mediaContent = new FileContent("*/*", localFile);
		// service.files().insert(body, mediaContent).execute();
	}

	@Test
	public void testAddFile() throws IOException {
		gdfsm.addFile(mock(java.io.File.class));
	}

	@Test
	public void testUpdateFile() {
	}

	@Test
	public void testDeleteFile() {

	}

}
