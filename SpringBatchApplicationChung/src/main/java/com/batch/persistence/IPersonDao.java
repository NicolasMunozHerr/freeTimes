package com.batch.persistence;

import org.springframework.data.repository.CrudRepository;

import com.batch.entities.Person;

public interface IPersonDao extends CrudRepository<Person, Long> {

    
}
