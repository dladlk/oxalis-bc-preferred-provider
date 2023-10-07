package com.mercell.nemhandel.as4.module.oxalis.bc;

import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;

public class BCPreferredProviderModule extends AbstractModule {

	private static final Logger log = LoggerFactory.getLogger(BCPreferredProviderModule.class);

	@Override
	protected void configure() {
		log.info("Make sure that BouncyCastle is the preferred security provider");
		final Provider[] providers = Security.getProviders();
		if (providers != null && providers.length > 0) {
			if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null) {
				for (int i = 0; i < providers.length; i++) {
					Provider provider = providers[i];
					if (BouncyCastleProvider.PROVIDER_NAME.equals(provider.getName())) {
						log.info("Found BC provider at position {}", i + 1);
					}
				}
				log.info("Found already registered BC provider, remove it to insert at position 1");
				Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
			}
		}
		Security.insertProviderAt(new BouncyCastleProvider(), 1);
		log.info("Registered BouncyCastle as preferred Java security provider - at position 1 of providers list");
	}

}
