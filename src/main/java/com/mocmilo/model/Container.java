package com.mocmilo.model;

import java.util.List;
import java.util.Set;

public class Container {

    private List<Person> peopleList;

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public Container(List<Person> peopleList) {
        this.peopleList = peopleList;
    }
}
