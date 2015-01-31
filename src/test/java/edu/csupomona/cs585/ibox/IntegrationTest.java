package edu.csupomona.cs585.ibox;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

public class IntegrationTest {

	private Drive drive;

	private GoogleDriveFileSyncManager gdfsm;

	private WatchDir watchDir;

	private java.io.File file;

	public IntegrationTest() throws IOException, GeneralSecurityException {

		drive = initGoogleDriveServices();

		gdfsm = new GoogleDriveFileSyncManager(drive);

		watchDir = new WatchDir(Paths.get("res-folder/dir"), gdfsm);

		file = new java.io.File("res-folder/file/Chrysanthemum.jpg");
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

		int time = 3;

		WatchDirThread wdThread = new WatchDirThread();

		wdThread.start();

		TimeUnit.SECONDS.sleep(time);

		gdfsm.addFile(file);

		TimeUnit.SECONDS.sleep(time);

		gdfsm.updateFile(file);

		TimeUnit.SECONDS.sleep(time);

		gdfsm.deleteFile(file);

		TimeUnit.SECONDS.sleep(time);
	}

	private class WatchDirThread extends Thread {

		public void run() {
			watchDir.processEvents();
		}
	}
}
