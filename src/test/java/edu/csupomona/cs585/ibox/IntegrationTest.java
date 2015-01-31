package edu.csupomona.cs585.ibox;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.junit.Test;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class IntegrationTest {

	private GoogleDriveFileSyncManager gdfsm;

	public IntegrationTest() throws IOException, GeneralSecurityException {

		Drive drive = initGoogleDriveServices();

		gdfsm = new GoogleDriveFileSyncManager(drive);
	}

	public Drive initGoogleDriveServices() throws IOException,
			GeneralSecurityException {

		NetHttpTransport httpTransport = new NetHttpTransport();

		JacksonFactory jsonFactory = new JacksonFactory();

		java.io.File file = new java.io.File(
				"res-folder/key/ibox-b2ea2d1821b6.p12");

		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(jsonFactory)
				.setServiceAccountId(
						"123212057414-r5a1gb704ar7i9ulhnsbsvqcl8lh1o6j@developer.gserviceaccount.com")
				.setServiceAccountScopes(
						Collections.singleton(DriveScopes.DRIVE))
				.setServiceAccountPrivateKeyFromP12File(file).build();

		return new Drive.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName("ibox").build();
	}

	@Test
	public void testAddFile() throws IOException {

		java.io.File file = new java.io.File(
				"res-folder/file/Chrysanthemum.jpg");

		gdfsm.addFile(file);
	}

	@Test
	public void testUpdateFile() throws IOException {

		java.io.File file = new java.io.File(
				"res-folder/file/Chrysanthemum.jpg");

		gdfsm.updateFile(file);
	}

	@Test
	public void testDeleteFile() throws IOException {

		java.io.File file = new java.io.File(
				"res-folder/file/Chrysanthemum.jpg");

		gdfsm.deleteFile(file);
	}

	@Test
	public void testProcessEvent() throws IOException {

		WatchDir watchDir = new WatchDir(Paths.get("res-folder/dir"), gdfsm);
		System.out.println("dstart");
		//new WatchDirThread().start(watchDir);
		System.out.println("end");
	}
}
