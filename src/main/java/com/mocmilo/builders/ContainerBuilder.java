package com.mocmilo.builders;

import com.mocmilo.model.Container;
import com.mocmilo.model.Person;

import java.util.Arrays;

import static com.mocmilo.model.Gender.M;
import static com.mocmilo.model.Gender.W;

public class ContainerBuilder {

    public Container build() {
        Person person1 = new Person("Tom", 27, M);
        Person person2 = new Person("Alice", 24, W);
        Person person3 = new Person("Dominica", 28, W);

        return new Container(Arrays.asList(person1, person2, person3));
    }
}
