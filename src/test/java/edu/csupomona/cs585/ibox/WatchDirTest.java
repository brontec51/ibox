package edu.csupomona.cs585.ibox;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.nio.file.WatchEvent;

import org.junit.Test;

public class WatchDirTest {

	private WatchDir wd;

	private boolean flag;

	public WatchDirTest() {
		wd = mock(WatchDir.class);
	}

	@Test
	public void testCast() {

		flag = wd.cast(mock(WatchEvent.class)) instanceof WatchEvent;

		assertTrue("cast(WatchEvent<T>) has failed.", flag);
	}
}
