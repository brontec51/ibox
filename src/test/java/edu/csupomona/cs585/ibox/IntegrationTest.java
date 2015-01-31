package edu.csupomona.cs585.ibox;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class IntegrationTest {

	private GoogleDriveFileSyncManager gdfsm;

	private WatchDir watchDir;

	private java.io.File dir;

	public IntegrationTest() throws IOException, GeneralSecurityException {

		Drive drive = initGoogleDriveServices();

		gdfsm = new GoogleDriveFileSyncManager(drive);

		dir = new java.io.File("res-folder/dir");
	}

	private Drive initGoogleDriveServices() throws IOException,
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
	public void testProcessEvent() throws IOException, InterruptedException {

		java.io.File file = new java.io.File(dir.getAbsolutePath()
				+ "/testFile");

		for (int i = 0; i < dir.listFiles().length; i++)
			if (file.getName().equals(dir.listFiles()[i].getName())) {
				dir.listFiles()[i].delete();
				break;
			}

		watchDir = new WatchDir(Paths.get(dir.getAbsolutePath()), gdfsm);

		WatchDirThread wdThread = new WatchDirThread();

		int time = 10;
		int n1 = 0;
		int n2 = 0;

		wdThread.start();
		TimeUnit.SECONDS.sleep(time);

		n1 = gdfsm.service.files().list().execute().getItems().size();

		file.createNewFile();
		TimeUnit.SECONDS.sleep(time);

		n2 = gdfsm.service.files().list().execute().getItems().size();
		Assert.assertTrue("Adding a file has failed.", n1 < n2);

		file.delete();
	}

	private class WatchDirThread extends Thread {

		public void run() {
			watchDir.processEvents();
		}
	}
}
