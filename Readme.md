# Kafka 

The best place of getting started with kafka is its [official documentation](https://kafka.apache.org/documentation/#gettingStarted).  
You will get the basics right for each terminology that is being used in the Kafka.

Best is to do handson of the quickstart guide and then explore the different options afterwards
 - Kafka QuickStart: [official documentation](https://kafka.apache.org/documentation/#gettingStarted).
 - Spring boot with Kafka: https://docs.spring.io/spring-kafka/reference/quick-tour.html

# Kafka Quick Start:
Followed what is already stated at https://kafka.apache.org/quickstart along with [Apache Kafka Tutorial](https://www.youtube.com/watch?v=hyJZP-rgooc)

## Step 1:  Get Kafka
 - Download .tgz from https://kafka.apache.org/downloads. 
 - 3.7.0 is the latest version Released Feb 27, 2024 till 8th june 2024
 - Make sure you download binary for running (you can get the src along with bin for sure)
 - After selecting you will be redirected to another page with different mirrors, I used HTTP one [Example](https://dlcdn.apache.org/kafka/3.7.0/kafka_2.13-3.7.0.tgz)
 - Approx 114Mb file is being downloaded as of 8th June 2024 for 3.7.0 version
 - Unzip the file using tools like 7zip (first you get tar file -> unzip again to get folders)
 - Tip: Keep this files in c:\kafka to avoid long path issue
 - After extracting you will have folders like below:
   - bin
   - config
   - libs
   - licenses
   - site-docs
   - LICENSE file
   - NOTICE file

## Step 2: Start Kafka environment
*Pre-requisite:* Java 8+ must be installed in system

We are supposed to run few services that to in recommended order
 - Zookeper server
 - kafka server

For starting these servers:  
 - `.sh` files are available in the bin folder directly for the UNIX machines
 - You have to navigate to `bin/windows` for the `.bat` files compatible with windows.
 - Also, tried running .sh files via gitbash and ran into issues while starting the kafka server. [Stack overflow thread](https://stackoverflow.com/questions/25037263/apache-kafka-error-on-windows-couldnot-find-or-load-main-class-quorumpeermain)
 - Hence commands with `.sh` are for Unix and that with `.bat` are for Windows 

### 2.1 Start Zookeper server

**Note:** 
1. Commands varies from the OS you are using
2. If you are on windows machine you will have to use GitBash to see the logs. if you use the same command via cmd/powershell it will open Git bash command and it will close automatically the same
2. ZooKeeper will no longer be required by Apache Kafka soon. Read feature logs for 2.8.0 version which states "Early access of replace ZooKeeper with a self-managed quorum" and read more [here](https://kafka.apache.org/downloads)

GitBash/Unix:
```cmd
bin/zookeeper-server-start.sh config/zookeeper.properties
```

```cmd
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

Windows:
https://stackoverflow.com/questions/25037263/apache-zookeeper-error-on-windows-couldnot-find-or-load-main-class-quorumpeerm


#### Error Scenario
```
PS C:\Softwares\kafka_2.13-3.7.0\bin\windows> .\zookeeper-server-start.bat .\config\zookeeper.properties
The input line is too long.
The syntax of the command is incorrect.
```
Solution: https://stackoverflow.com/questions/16821784/input-line-is-too-long-error-in-bat-file

```
$ ./bin/windows/zookeeper-server-start.bat .\config\zookeeper.properties
[2024-06-08 14:05:04,247] INFO Reading configuration from: .configzookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2024-06-08 14:05:04,248] WARN .configzookeeper.properties is relative. Prepend .\ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2024-06-08 14:05:04,249] ERROR Invalid config, exiting abnormally (org.apache.zookeeper.server.quorum.QuorumPeerMain)
org.apache.zookeeper.server.quorum.QuorumPeerConfig$ConfigException: Error processing .configzookeeper.properties
        at org.apache.zookeeper.server.quorum.QuorumPeerConfig.parse(QuorumPeerConfig.java:198)
        at org.apache.zookeeper.server.quorum.QuorumPeerMain.initializeAndRun(QuorumPeerMain.java:125)
        at org.apache.zookeeper.server.quorum.QuorumPeerMain.main(QuorumPeerMain.java:91)
Caused by: java.lang.IllegalArgumentException: .configzookeeper.properties file is missing
        at org.apache.zookeeper.server.util.VerifyingFileFactory.doFailForNonExistingPath(VerifyingFileFactory.java:54)
        at org.apache.zookeeper.server.util.VerifyingFileFactory.validate(VerifyingFileFactory.java:47)
        at org.apache.zookeeper.server.util.VerifyingFileFactory.create(VerifyingFileFactory.java:39)
        at org.apache.zookeeper.server.quorum.QuorumPeerConfig.parse(QuorumPeerConfig.java:183)
        ... 2 more
Invalid config, exiting abnormally
[2024-06-08 14:05:04,251] INFO ZooKeeper audit is disabled. (org.apache.zookeeper.audit.ZKAuditProvider)
[2024-06-08 14:05:04,253] ERROR Exiting JVM with code 2 (org.apache.zookeeper.util.ServiceUtils)
```
Solution: https://stackoverflow.com/questions/44263348/unable-to-start-zookeeper-server-in-apache-kafka

### 2.2 Start Kafka "broker" Server

GitBash/Unix:
```cmd
bin/kafka-server-start.sh config/server.properties
```

Windows:
```cmd
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
## Step 3: Create `topic` to store events

GitBash/Unix:
```cmd
bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
```

Windows: 
```cmd
bin/kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092
```
or
```cmd
.\bin\windows\kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092
```

## Step 4: Write events to the topic
GitBash/Unix:
```cmd
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
```

Windows: 
```cmd
.\bin\windows\kafka-console-producer.bat --topic quickstart-events --bootstrap-server localhost:9092
```

Note:
 - This window will be active
 - Every message you write and hit enter will be shown in the below window of consumer
 - every enter will result in a saperate event being written to topic

## Step 5: Read the events

```cmd
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```

```cmd
.\bin\windows\kafka-console-consumer.bat --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```

Note: 
  - `--from-beginning` will give you all the events till date, if you remove the same then will give you only the events that are published after the last read
  - You can open 2 cmd prompts and run the command given above to check the 2 consumers consuming the data from same producer

## Step 6: Kafka connect

Official documentation: https://kafka.apache.org/documentation/#connect


## Step 7: Kafka streams

Official documentation: https://kafka.apache.org/documentation/#connect_overview

# Spring Boot with Kafka

## Pre-requisites

 - Above kafka setup
 - Basic spring boot application
   - Spring boot version 3.2.0
   - Java 17

## Setup   

### Maven dependency
  ```cmd
    <dependency>
      <groupId>org.springframework.kafka</groupId>
      <artifactId>spring-kafka</artifactId>
    </dependency>
  ```
 - You can get the latest version of above dependency from [Maven Central](https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka)
 - Also, above is a managed dependency and it will give you a warning message `overriding managed dependency` if you specify the version explicitly
 ```cmd
    <dependency>
      <groupId>org.springframework.kafka</groupId>
      <artifactId>spring-kafka</artifactId>
      <version>2.7.3</version>
    </dependency>
  ```

### Application.properties
 - Setup the Consumer and Producer
 ```
   ## Consumer
   spring.kafka.consumer.bootstrap-servers=localhost:9092
   spring.kafka.consumer.key-deserializer= org.apache.kafka.common.serialization.StringDeserializer
   spring.kafka.consumer.value-deserializer= org.apache.kafka.common.serialization.StringDeserializer
   
   spring.kafka.consumer.group-id=exampleGroup
   # Handles when no offsets are available in kafka
   spring.kafka.consumer.auto-offset-reset=earliest
   
   
   ## Producer
   spring.kafka.producer.bootstrap-servers=localhost:9092
   spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
   spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
 ```
  
## Topics creation
 - If you see in basic kafka setup, we have commands to create the topics.  
 - We can achieve the same through Spring programatically.  
 - To achieve this we have `KafkaAdmin` and `NewTopic` class using which we can create the topics

### via command line
We use below command to create a topic "mytopic". (Kept each attribute in single line for better readability)

```cmd
bin/kafka-topics.sh 
  --create
  --zookeeper localhost:2181
  --replication-factor 1 
  --partitions 1
  --topic mytopic
```

### Via bean creation in spring
 - Create NewTopicConfig to setup the topics on stratup
   - You can create bean with `NewTopic` or `TopicBuilder` class
   
```java
@Configuration
public class NewTopicConfig {

	// The NewTopic bean causes the topic to be created on the broker; it is not
	// needed if the topic already exists.

	// Using Builder
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}

	// Using New
	@Bean
	public NewTopic topic2() {
		return new NewTopic("fantatstic", 1, (short) 1);
	}

	// Creating array of topics
	@Bean
	public NewTopics topics() {
		return new NewTopics(
				new NewTopic("myTopic", 1, (short) 1),
				new NewTopic("greeting", 1, (short) 1)
				);
	}

}
```

## Controller to test
 - 
 - Hit: http://localhost:8080/api/v1/kafka/publish?message=hi

# Spring boot with Kafka streams

## Pre-requisite

 - Above Spring boot with Kafka setup
 - Maven dependency
 ```
 <dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-streams</artifactId>
 </dependency> 
 ```


## References

 - [Spring kafka official guide](https://spring.io/projects/spring-kafka#overview)
 - [baeldung: spring-boot-kafka-streams](https://www.baeldung.com/spring-boot-kafka-streams)
