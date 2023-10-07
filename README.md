# Purpose

Register BC as Preferred Provider Oxalis Module for java 8

# Installation

Drop jar file into classpath of the Oxalis and add to oxalis.conf or reference conf next code to activate the module:

```
oxalis.module.as4.forcebc = {
    class = com.mercell.nemhandel.as4.module.oxalis.bc.BCPreferredProviderModule
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

Switching to BouncyCastle 

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

Second approach is more stable, as setting property not always help because Security providers could be configured on some ealier stage then the property is set, and it is read only once.
