package com.batch.service;

import java.util.List;

import com.batch.entities.Person;

public interface PersonService {
    Iterable<Person> saveAll(List<Person> personList);
}
