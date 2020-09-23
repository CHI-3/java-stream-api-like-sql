## name
java-stream-api-like-sql

## Overview
sample code for using data like sql

## Requirement
- Java 11
- Eclipse:2020-09 is recommended
- [docker-mysql-java](https://github.com/CHI-3/docker-mysql-java) with Docker or Docker Toolbox


## Usage
1. Download and unzip this package  or clone this project. Then, move it to the workspace directory in Eclipse directory.

2. Import the project into Eclipse as 'Existing Gradle Project'.

3. Create Docker container with [docker-mysql-java](https://github.com/CHI-3/docker-mysql-java) and start it.

4. Change MySQL connection settings in application.yml to your environment.

5. When it's finished, run as Spring Boot App.

6. Access URL below with your browser or api test tool.

**List of items divided according to categories**  
http://*{hostname}*:*{port number}*/item-category/item

**Item category aggregation**  
http://*{hostname}*:*{port number}*/item-category/aggregation

## Author
CHI-3

## References
- [パッケージ java.util.stream | Java SE 11 & JDK 11](https://docs.oracle.com/javase/jp/11/docs/api/java.base/java/util/stream/package-summary.html)
- [Java Stream | ひしだまＨＰ](http://www.ne.jp/asahi/hishidama/home/tech/java/stream.html)
