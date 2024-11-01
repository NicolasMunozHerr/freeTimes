package com.batch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch.entities.Person;
import com.batch.persistence.IPersonDao;
import com.batch.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private IPersonDao personDao;
    
    @Override
    public Iterable<Person> saveAll(List<Person> personList) {
        return personDao.saveAll(personList);
    }

}
