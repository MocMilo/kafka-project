## kafka-project
 
### Running  kafka in docker-compose

### 1. set up docker-compose-single-broker file:

* KAFKA_ADVERTISED_HOST_NAME (host IP, eg. 192.168.1.101)
* KAFKA_CREATE_TOPICS (eg. "ExampleTopic")

### 2. run kafka server using command:

````bash 
$ docker-compose -f docker/docker-compose-single-broker.yml up
````


kafka docker files origin:
* https://github.com/wurstmeister/kafka-docker<br>
