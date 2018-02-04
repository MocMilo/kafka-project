package com.mocmilo;

import com.mocmilo.kafka.KafkaProperties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;

public class App {
    private static final String TOPIC = "ExampleTopic";
    private static final String KEY = "key";
    private static final String MESSAGE = "Message form Java App, number: ";
    private static final String START_MESSAGE = "START of message Producer";
    private static final String END_MESSAGE = "END of sending messages.";

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("-p")) {
            produceMessage();

        } else if (args.length > 0 && args[0].equals("-c")) {
            System.out.println("consumer not implemented");
            receaveMessages();

        } else {
            System.out.println("\n*** Welcome to Kafka-project ***" +
                    "\nNo parameters: \n-p producer mode\n-c client mode");
        }
    }


    private static void receaveMessages() {
        System.out.println("Waiting for messages.");

        try (Consumer<String, String> consumer = new KafkaConsumer<>(KafkaProperties.get())) {
            consumer.subscribe(Arrays.asList("ExampleTopic"));
            final int giveUp = 100;
            int noRecordsCount = 0;

            while (true) {
                final ConsumerRecords<String, String> consumerRecords =
                        consumer.poll(1000);

                if (consumerRecords.count() == 0) {
                    noRecordsCount++;
                    if (noRecordsCount > giveUp) break;
                    else continue;
                }
                consumerRecords.forEach(record -> {
                    System.out.printf("Consumer Record:(%s, %s, %d, %d)\n",
                            record.key(), record.value(),
                            record.partition(), record.offset());
                });
                consumer.commitAsync();
                consumer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void produceMessage() {

        System.out.println(START_MESSAGE);

        try (Producer<String, String> producer = new KafkaProducer<>(KafkaProperties.get())) {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(1000);

                String key = KEY + i;
                String value = MESSAGE + i;

                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, value);
                producer.send(record);
            }
            System.out.println(END_MESSAGE);
            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

