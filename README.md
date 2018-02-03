# kafka-project
 
## running  kafka in docker-compose

## 1. set up docker-compose-single-broker file:

* host IP (eg. 192.168.1.101)
* topic   (eg. "ExampleTopic"

## 2. run kafka server using command:

'''bash
$ docker-compose -f docker/docker-compose-single-broker.yml up
'''


kafka docker files origin:

* https://github.com/wurstmeister/kafka-docker<br>
