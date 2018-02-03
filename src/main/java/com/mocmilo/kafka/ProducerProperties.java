package com.mocmilo.kafka;

import java.util.Properties;

public final class ProducerProperties {

    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String VALUE = "192.168.1.101:9092";
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String VALUE_SERIALIZER = "value.serializer";

    public static Properties get() {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS, VALUE);
        props.put(KEY_SERIALIZER, STRING_SERIALIZER);
        props.put(VALUE_SERIALIZER, STRING_SERIALIZER);
        return props;
    }
}
