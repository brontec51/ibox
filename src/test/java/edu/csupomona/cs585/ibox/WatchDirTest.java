package edu.csupomona.cs585.ibox;

import java.io.File;
import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.json.MockJsonFactory;
import com.google.api.services.drive.Drive;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WatchDirTest {

	private GoogleDriveFileSyncManager g = new GoogleDriveFileSyncManager(
			new Drive(new MockHttpTransport(), new MockJsonFactory(),
					new HttpRequestInitializer()));

	@Test
	public void test1AddFile() throws IOException {
		g.addFile(new File("test.jpg"));
	}
}
