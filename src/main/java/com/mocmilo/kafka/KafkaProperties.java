package com.mocmilo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public final class KafkaProperties {
    private static final String SERVER_PORT = "192.168.1.101:9092";
    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    private static final String VALUE_SERIALIZER = "value.serializer";
    private static final String KEY_DESERIALIZER = "key.deserializer";
    private static final String VALUE_DESERIALIZER = "value.deserializer";

    public static Properties get() {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS, SERVER_PORT);
        props.put(KEY_SERIALIZER, STRING_SERIALIZER);
        props.put(VALUE_SERIALIZER, "com.mocmilo.serializers.PersonSerializer");
        props.put(VALUE_DESERIALIZER, "com.mocmilo.serializers.PersonDeserializer");
        props.put(KEY_DESERIALIZER, STRING_DESERIALIZER);

        props.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaExampleConsumerGroup");
        return props;
    }
}
