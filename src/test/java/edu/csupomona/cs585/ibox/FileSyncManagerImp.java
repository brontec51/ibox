package edu.csupomona.cs585.ibox;

import java.io.File;
import java.io.IOException;

import edu.csupomona.cs585.ibox.sync.FileSyncManager;

public class FileSyncManagerImp implements FileSyncManager {

	@Override
	public void addFile(File localFile) throws IOException {

		System.out.println("File added");

	}

	@Override
	public void updateFile(File localFile) throws IOException {

		System.out.println("File updated");
	}

	@Override
	public void deleteFile(File localFile) throws IOException {

		System.out.println("File deleted");

	}
}
