## Java Example - [JoeZwet](https://github.com/JoeZwet)
This example uses org.json to get JSON from <https://mcapi.ca>.
You can download the org.json jar file from [here](http://central.maven.org/maven2/org/json/json/20160810/json-20160810.jar).

Maven:
```xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20160810</version>
</dependency>
```
Main.java will print the following in console if the ip is changed:
```
Status: true
Hostname: localhost
Port: 25565
Ping: 35
Players: 1/20 (online/max)
```
