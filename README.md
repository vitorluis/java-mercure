# Java library for Mercure

The goal of this library is to provide a quick way to publish messages on Mercure. 
If you don't know what Mercure is, take a look here: (https://github.com/dunglas/mercure).

## Installing

You can install via Gradle or Maven:
```groovy
implementation 'com.vitorvillar:mercure:1.0.0'
```

Maven:
```xml
<dependency>
  <groupId>com.vitorvillar</groupId>
  <artifactId>mercure</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## How to use

This library is really simple to use, you just need to create a new `Publisher` and for each message you
want to publish, just create a new `Message` object.

Here is an example:

```java
package com.vitorvillar.test;

import com.vitorvillar.mercure.exceptions.HubNotFoundException;
import com.vitorvillar.mercure.exceptions.PublishRejectedException;
import com.vitorvillar.mercure.exceptions.UnauthorizedPublisherException;

public class Main {

    public static void main(String[] args) throws UnauthorizedPublisherException, PublishRejectedException, HubNotFoundException {
        var token = "your.secret.token";
        var data = "{\"test\": 10}";

        var publisher = new Publisher("http://localhost:3000/.well-known/mercure", token);
        var message = new Message(data, "mytopic");
        var result = publisher.publish(message);
        System.out.println("Message ID: " + result.getId().toString());
    }
}

```

## Credits

Created and maintained by Vitor Villar <vitor.luis98@gmail.com>