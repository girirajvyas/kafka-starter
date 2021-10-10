# Kafka 

The best place of getting started with kafka is its [official documentation](https://kafka.apache.org/documentation/#gettingStarted).  
You will get the basics right for each terminology that is being used in the Kafka.

Best is to do handson of the quickstart guide and then explore the different options afterwards

# Kafka Quick Start:
Followed what is already stated at https://kafka.apache.org/quickstart along with [Apache Kafka Tutorial](https://www.youtube.com/watch?v=hyJZP-rgooc)

## Step 1:  Get Kafka
 - Download .tgz from https://kafka.apache.org/downloads. 
 - Make sure you download binary for running (you can get the src along with bin for sure)
 - After selecting you will be redirected to another page with different mirrors, I used HTTP one [Example](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.8.0/kafka_2.13-2.8.0.tgz)
 - Approx 69Mb file is being downloaded as of 7th July 2021
 - Unzip the file using tools like 7zip
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
```cmd
bin/zookeeper-server-start.sh config/zookeeper.properties
```

```cmd
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```


Note: 
1. if you are on windows machine you will have to use GitBash to see the logs. if you use the same command via cmd/powershell it will open Git bash command and it will close automatically the same
2. ZooKeeper will no longer be required by Apache Kafka soon. Read feature logs for 2.8.0 version which states "Early access of replace ZooKeeper with a self-managed quorum" and read more [here](https://kafka.apache.org/downloads)

### 2.2 Start Kafka "broker" Server
```cmd
bin/kafka-server-start.sh config/server.properties
```

```cmd
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
## Step 3: Create `topic` to store events

```cmd
bin/kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092
```

```cmd
.\bin\windows\kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092
```

## Step 4: Write events to the topic

```cmd
bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
```

```cmd
.\bin\windows\kafka-console-producer.bat --topic quickstart-events --bootstrap-server localhost:9092
```

## Step 5: Read the events

```cmd
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```

```cmd
.\bin\windows\kafka-console-consumer.bat --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```

Note: `--from-beginning` will give you all the events till date, if you remove the same then will give you only the events that are published after the last read

## Step 6: Kafka connect

Official documentation: https://kafka.apache.org/documentation/#connect


## Step 7: Kafka streams

Official documentation: https://kafka.apache.org/documentation/#connect_overview

# Spring Boot with Kafka

## Pre-requisites

 - Above kafka setup
 - Basic spring boot application
 - Maven dependency:
  ```cmd
    <dependency>
      <groupId>org.springframework.kafka</groupId>
      <artifactId>spring-kafka</artifactId>
      <version>2.7.3</version>
    </dependency>
  ```
 - You can get the latest version of above dependency from [Maven Central](https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka)
 - Also, above is a managed dependency and it will give you a warning message `overriding managed dependency` if you specify the version explicitly
 
## Topics creation

If you see in basic kafka setup, we have commands to create the topics.  
We can achieve the same through Spring programatically.  
To achieve this we have `KafkaAdmin` and `NewTopic` class using which we can create the topics

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




## References

 - [Spring kafka official guide](https://spring.io/projects/spring-kafka#overview)
