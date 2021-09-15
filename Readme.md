# OpenLiberty Uber JAR

## Overview
This is a sample OpenLiberty Uber JAR

## Running Locally
```
cd openliberty-uber-jar-war
mvn liberty:run -P local
```

## Building
Project Uber .jar file is built via Maven:
```
mvn clean package
```

## Running (via Uber .jar)
Project Uber .jar file is run just like any other runnable .jar:
```
java -jar ./openliberty-uber-jar-jar/target/openliberty-uber-jar-jar.jar
```
