# Purpose

Register BC as Preferred Provider Oxalis Module for java 8 via

```
Security.insertProviderAt(new BouncyCastleProvider(), 1);
```

without changing the code of a project, e.g. Oxalis.

All the code is a couple of lines:

https://github.com/dladlk/oxalis-bc-preferred-provider/blob/aaf4761c678f6e14d30fc840778991cd4f23fb35/src/main/java/com/mercell/nemhandel/as4/module/oxalis/bc/BCPreferredProviderModule.java#L17-L33

# Installation

Drop jar file into classpath of the Oxalis and add to oxalis.conf or reference.conf next line to activate the module:

```
oxalis.module.as4.forcebc = {
    class = com.mercell.nemhandel.as4.module.oxalis.bc.BCPreferredProviderModule
}
```

To disable the module, change it to 

```
oxalis.module.as4.forcebc = {
    class = com.mercell.nemhandel.as4.module.oxalis.bc.BCPreferredProviderModule
    enabled = false
}
```

If it is activated properly, logs should include:

```
INFO  c.m.n.a.m.o.b.BCPreferredProviderModule - Make sure that BouncyCastle is the preferred security provider
INFO  c.m.n.a.m.o.b.BCPreferredProviderModule - Found BC provider at position 11
INFO  c.m.n.a.m.o.b.BCPreferredProviderModule - Found already registered BC provider, remove it to insert at position 1
INFO  c.m.n.a.m.o.b.BCPreferredProviderModule - Registered BouncyCastle as preferred Java security provider - at position 1 of providers list
```

# Important - PFX keystores

Switching to BouncyCastle can lead to issues with reading P12/PFX keystores. Possible solution - convert the keystore to JKS format, e.g. with the help of amazing [Keystore Explorer](https://keystore-explorer.org/)

# Important - only for Java 8

This module is intended only for java 8 - as starting from java 9 there is a security property `jdk.security.provider.preferred` which allows more granular configuration.

Either via code as:

```
java.security.Security.setProperty("jdk.security.provider.preferred", "AES/GCM/NoPadding:BC");
```

or via `JDK_HOME\conf\security\java.security` file:

```
jdk.security.provider.preferred=AES/GCM/NoPadding:BC
```

Second approach is more stable, as setting property not always help because Security providers could be configured on some earlier stage than the property is set, and it is read only once.

# Important - Java 21 JCE performs BETTER than BouncyCastle 1.70

See time measurement on different sizes of random bytes on different java versions here: [SunJCE vs BC performance](https://github.com/dladlk/sunjce-vs-bc-performance/blob/main/README.md)
