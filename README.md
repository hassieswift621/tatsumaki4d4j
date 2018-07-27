Tatsumaki4D4J [![CircleCI](https://circleci.com/gh/hassieswift621/tatsumaki4d4j/tree/dev.svg?style=svg)](https://circleci.com/gh/hassieswift621/tatsumaki4d4j/tree/dev)
=================

An asynchronous Java API wrapper around one of the most popular bots on Discord, Tatsumaki; made specifically for projects using Discord4J.
If you want a pure one without built in Discord4J support, see https://github.com/hassieswift621/tatsumaki4j

Currently, the library supports Discord4J v2. Support for v3 will be coming soon.

The API wrapper has all endpoints currently available or soon to be available implemented: guilds, users and ping.

When you create the client, by default a number of fixed threads are created, specifically CPUs/CPU cores + 1.
You can customise this using the client builder.

The library is designed to run for the duration of your program, keeping the thread pool alive for quick async execution.

Once you are done with the client, call its shutdown method to shutdown the executor services, to allow your program to shutdown.
Note however, you must create a new instance of the client if you wish to make another API request after shutting down the client.

Note that I am not part of the Tatsumaki Bot development.
If you have any queries about the bot or the API, please visit https://tatsumaki.xyz/

How do I get an API key
-----------------------
To get an API key, run the following command on Tatsumaki: **t!apikey**

Dependencies - Discord4J v2
------------
This library is available on JCenter<sup>SOON<sup>&trade;</sup></sup>. The latest version is ``0.1.0``.

Replace ``{LATEST_VERSION}`` with the latest version.

**Gradle Setup**
```gradle
implementation 'uk.co.hassieswift621.libraries.discord.tatsumaki4d4j:v2:{LATEST_VERSION}'
```

**Maven Setup**
```maven
<dependency>
  <groupId>uk.co.hassieswift621.libraries.discord.tatsumaki4d4j</groupId>
  <artifactId>v2</artifactId>
  <version>{LATEST_VERSION}</version>
  <type>pom</type>
</dependency>
```

Tutorial
--------
**Creating the client**
```java
// Create the client with the default number of threads.
TatsumakiClient tatsumakiClient = new TatsumakiClient("YOUR TATSUMAKI BOT TOKEN");

// Create the client with a custom number of threads.
TatsumakiClient tatsumakiClient = new TatsumakiClient.Builder()
    .setThreadPoolSize(1)
    .setToken("YOUR TATSUMAKI BOT TOKEN")
    .build();
```

**Retrieving user profile data**
```java
// Request for user profile data for a user.
tatsumakiClient.getUser(IDiscordClient, "User ID",
    user -> {
        // Success, output some stuff.
        System.out.println("User's Rank: " + user.getRank());
        System.out.println("User's Reputation: " + user.getReputation());
        
        // Get user as IUser.
        IUser discordUser = user.getUser();
    },
    // Output error message.
    Throwable::printStackTrace
}

// Do other stuff here if required while the above request
// is executed.
```

**Other endpoints**
- GET Guild Leaderboard: ``getGuildLeaderboard(IDiscordClient, IGuild, Response<GuildLeaderboard>, Error)``
- GET Guild User Stats: ``getGuildUserStats(IDiscordClient, IGuild, IUser, Response<GuildUserStats>, Error)``
- GET Ping: ``getPing(Response<Ping>, Error)``
- PUT Guild User Points: ``updateGuildUserPoints(IDiscordClient, IGuild, IUser, updateAction, amount, Response<GuildUserPoints>, Error)``
- PUT Guild User Score: ``updateGuildUserScore(IDiscordClient, IGuild, IUser, updateAction, amount, Response<GuildUserScore>, Error)``

**Exceptions**
- All exceptions are thrown via a single runtime exception ``TatsumakiException``

**Shutting down the client**
```java
tatsumakiClient.shutdown();
```

License
-------
Copyright &copy;2018 HassieSwift621.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
