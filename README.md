<p align="center">
  <img src="https://github.com/pavog/Multicraft-api/raw/master/multicraft.jpg" width="577" height="118"/>
</p>
<h1 align="center">Multicraft API client for Java</h1>

[![Build Status](https://travis-ci.com/pavog/Multicraft-api.svg?branch=master)](https://travis-ci.com/pavog/Multicraft-api)
[![Coverage Status](https://coveralls.io/repos/github/pavog/Multicraft-api/badge.svg?branch=master)](https://coveralls.io/github/pavog/Multicraft-api?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6c5402a78da849229b86d7588e5d83bf)](https://www.codacy.com/app/pavog/Multicraft-api?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=pavog/Multicraft-api&amp;utm_campaign=Badge_Grade)

## Requirements ##
To use the Multicraft API client, the following things are required:

  + Get a hosted [Multicraft instance](https://www.multicraft.org/site/page/download) 
  + Create an account on your Multicraft panel.
  + Check that your Multicraft panel is reachable from your API (in the same network).
  + Make sure that the API of the Multicraft instance is enabled.
    + Settings -> Panel configuration
    + Check if via http://yourpanel.com/api.php
  + Get your API key (Profile -> API key)
  + Now you're ready to use the Multicraft API client.
  + Java >= 8

## Maven Installation ##

By far the easiest way to install the Multicraft Java API client is to include it in your pom.xml within your [Maven project](https://maven.apache.org/guides/getting-started/).

To do so add this repository to your pom.xml:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
  <!-- Other repositories... -->
</repositories>
```

And this dependeny:
```xml
<dependencies>
  <dependency>
	  <groupId>com.github.pavog</groupId>
	  <artifactId>multicraft-api</artifactId>
	  <version>1.0.0-SNAPSHOT</version>
	</dependency>
  <!-- Other dependencies ... -->
</dependencies>
```

Please change the ``<version>1.0.0-SNAPSHOT</version>`` to the newest version. You can find the newest version on the [github releases](https://github.com/pavog/Multicraft-api/releases/latest).

## Manual Installation ##
If you're not familiar with using maven we've added a JAR file to the releases which you can add manually to your project.

  + Download the ``multicraftapi-X.Y.Z-SNAPSHOT.jar`` from the [latest release page](https://github.com/pavog/Multicraft-api/releases/latest).
  + Include it in your project in the IDE's settings.
    + [IntelliJ](https://www.jetbrains.com/help/idea/library.html)
    + [Eclipse](https://wiki.eclipse.org/FAQ_How_do_I_add_an_extra_library_to_my_project%27s_classpath%3F)

## Getting started ##

Initializing the Multicraft API client, and setting your panel url, username and API key.

```java
String testApiUrl = "https://panel.testurl.com/api.php";
String testApiUsername = "username";
String testApiKey = "api-key";
MulticraftAPI api = new MulticraftAPI(testApiUrl, testApiUsername, testApiKey);
``` 

## API documentation ##
If you wish to learn more about the API, please visit the [Multicraft Developer Portal](https://www.multicraft.org/site/docs/api). API Documentation is available in English and only for the PHP methods.

## Contribute ##

Want to help us make our API client even better? We take [pull requests](https://github.com/pavog/Multicraft-api/pulls) and [issue reports](https://github.com/pavog/Multicraft-api/issues).

## License ##
[MIT License](https://opensource.org/licenses/mit-license.php).
Made by Paul Vogel
