package com.mocmilo;

import com.mocmilo.kafka.ProducerProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class App {
    private static final String TOPIC = "ExampleTopic";
    private static final String KEY = "key";
    private static final String MESSAGE = "Message form Java App, number: ";
    private static final String START_MESSAGE = "START of message Producer";
    private static final String END_MESSAGE = "END of sending messages.";

    public static void main(String[] args) {
        System.out.println(START_MESSAGE);

        Producer<String, String> producer = new KafkaProducer<>(ProducerProperties.get());
        try {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(1000);

                String key = KEY + i;
                String value = MESSAGE + i;

                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, value);
                producer.send(record);
            }
            System.out.println(END_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
