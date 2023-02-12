# EventLog
Flag the events that are longer than a threshold

## Usage

EventLog requires Apache Maven 3.x & OpenJDK Java JDK 11 to run.

```sh
java -version
openjdk version "11.0.18" 2023-01-17
OpenJDK Runtime Environment Temurin-11.0.18+10 (build 11.0.18+10)
OpenJDK 64-Bit Server VM Temurin-11.0.18+10 (build 11.0.18+10, mixed mode)

./mvnw -version
Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63)

### Run instructions
### A sample log file has been provided under eventlog/logs folder

cd eventlog
./mvnw clean install
java -jar target/eventlog-0.0.1-SNAPSHOT.jar ./logs/log.txt
```

