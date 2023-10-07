package com.mercell.nemhandel.as4.module.oxalis.bc;

import org.junit.Test;

public class BCPreferredProviderModuleTest {

	@Test
	public void testConfigure() {
		BCPreferredProviderModule m = new BCPreferredProviderModule();
		m.configure();
		// Check indempontence
		m.configure();
	}

}
