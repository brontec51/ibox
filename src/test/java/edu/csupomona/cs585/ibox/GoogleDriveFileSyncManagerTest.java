package edu.csupomona.cs585.ibox;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.Drive.Files.Insert;
import com.google.api.services.drive.Drive.Files.List;
import com.google.api.services.drive.Drive.Files.Update;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

@RunWith(MockitoJUnitRunner.class)
public class GoogleDriveFileSyncManagerTest {

	private Drive mDrive = mock(Drive.class);
	private Files mFiles = mock(Files.class);
	private List mList = mock(List.class);
	private Insert mInsert = mock(Insert.class);
	private Update mUpdate = mock(Update.class);
	private Delete mDelete = mock(Delete.class);

	private java.io.File file = new java.io.File("test");
	private FileList fileList = new FileList();
	private java.util.List<File> itemList = new ArrayList<File>();

	private GoogleDriveFileSyncManager gdfsm;

	public GoogleDriveFileSyncManagerTest() throws IOException {

		gdfsm = new GoogleDriveFileSyncManager(mDrive);

		// initialization for testAddFile()
		when(mDrive.files()).thenReturn(mFiles);
		when(mFiles.insert(isA(File.class), isA(FileContent.class)))
				.thenReturn(mInsert);
		when(mInsert.execute()).thenReturn(new File());

		// initialization for testUpdateFile()
		when(mFiles.list()).thenReturn(mList);
		when(mList.execute()).thenReturn(fileList);
		when(
				mFiles.update(isA(String.class), isA(File.class),
						isA(FileContent.class))).thenReturn(mUpdate);
		when(mUpdate.execute()).thenReturn(new File());

		// initialization for testDeleteFile()
		when(mFiles.delete(isA(String.class))).thenReturn(mDelete);
	}

	@Test
	public void testAddFile() throws IOException {

		gdfsm.addFile(file);

		verify(mDrive).files();
		verify(mFiles).insert(isA(File.class), isA(FileContent.class));
		verify(mInsert).execute();
	}

	@Test
	public void testUpdateFile() throws IOException {

		// test when updateFile() cannot find a file to update
		fileList.setItems(itemList);

		gdfsm.updateFile(file);

		verify(mFiles).list();
		verify(mList).execute();

		// test when updateFile() successfully update a file
		itemList.add(new File().setTitle(file.getName()).setId("0"));

		gdfsm.updateFile(file);

		verify(mFiles).update(isA(String.class), isA(File.class),
				isA(FileContent.class));
		verify(mUpdate).execute();
	}

	@Test
	public void testDeleteFile() throws IOException {

		boolean flag = false;

		// test when deleteFile() cannot find a file to delete
		fileList.setItems(itemList);

		try {
			gdfsm.deleteFile(file);
		} catch (FileNotFoundException fnfe) {
			flag = true;
		}

		verify(mFiles).list();
		verify(mList).execute();

		Assert.assertTrue("deleteFile() should not be able to delete a file.",
				flag);

		// test when deleteFile() successfully delete a file
		itemList.add(new File().setTitle(file.getName()).setId("0"));

		gdfsm.deleteFile(file);

		verify(mFiles).delete(isA(String.class));
	}
}
