package com.mocmilo.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocmilo.model.Person;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PersonDeserializer implements Deserializer {

     @Override
    public void configure(Map map, boolean b) {
    }

    @Override
    public Person deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Person person = null;
        try {
            person = mapper.readValue(bytes, Person.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void close() {
    }
}
