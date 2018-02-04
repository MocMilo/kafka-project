package com.mocmilo;

import com.mocmilo.builders.ContainerBuilder;
import com.mocmilo.kafka.KafkaProperties;

import com.mocmilo.model.Person;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.List;

public class App {
    private static final String TOPIC = "ExampleTopic";
    private static final String KEY = "key";

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("-p")) {
            produceMessage();
        } else if (args.length > 0 && args[0].equals("-c")) {
            receiveMessages();
        } else {
            System.out.println("\n*** Welcome to Kafka-project ***" +
                    "\nNo parameters: \n-p producer mode\n-c client mode");
        }
    }

    private static void receiveMessages() {
        try (Consumer<String, Person> consumer = new KafkaConsumer<>(KafkaProperties.get())) {
            consumer.subscribe(Arrays.asList("ExampleTopic"));
            final int giveUp = 100;
            int noRecordsCount = 0;

            while (true) {
                final ConsumerRecords<String, Person> consumerRecords =
                        consumer.poll(1000);

                if (consumerRecords.count() == 0) {
                    noRecordsCount++;
                    if (noRecordsCount > giveUp) break;
                    else continue;
                }
                consumerRecords.forEach(record -> {
                    System.out.printf("Consumer Record:(%s, %s, %s, %s, %d, %d)\n",
                            record.key(),
                            record.value().getName(),
                            record.value().getAge(),
                            record.value().getGender().toString(),
                            record.partition(), record.offset());
                });
                consumer.commitAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void produceMessage() {
        try (Producer<String, Person> producer = new KafkaProducer<>(KafkaProperties.get())) {

            List<Person> people = new ContainerBuilder()
                    .build()
                    .getPeopleList();

            int i = 0;
            for (Person person : people) {
                String key = KEY + i++;

                ProducerRecord<String, Person> record = new ProducerRecord<>(TOPIC, key, person);
                producer.send(record);
                System.out.println("record send: " + person.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

