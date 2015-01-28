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

	public GoogleDriveFileSyncManagerTest() throws IOException {

		Drive mDrive = mock(Drive.class);

		gdfsm = new GoogleDriveFileSyncManager(mDrive);

		Files mFiles = mock(Files.class);

		when(mDrive.files()).thenReturn(mFiles);

		Insert mInsert = mock(Insert.class);

		File file = new File().setTitle(mock(java.io.File.class).getName());

		AbstractInputStreamContent aisc = mock(AbstractInputStreamContent.class);

		when(mFiles.insert(file, aisc)).thenReturn(mInsert);

	}

	@Test
	public void testAddFile() throws IOException {

		// File body = new File();
		// body.setTitle(localFile.getName());
		// FileContent mediaContent = new FileContent("*/*", localFile);
		// service.files().insert(body, mediaContent).execute();
		// File file = service.files().insert(body, mediaContent).execute();
		// System.out.println("File ID: " + file.getId());

		gdfsm.addFile(mock(java.io.File.class));
	}

	@Test
	public void testUpdateFile() {
	}

	@Test
	public void testDeleteFile() {

	}

}
