package com.batch.steps;


import com.batch.entities.Person;
import com.batch.service.impl.PersonServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonItemWriter implements ItemWriter<Person> {

  @Autowired
  private PersonServiceImpl personService;
  @Override
  public void write(Chunk<? extends Person> chunk) throws Exception {
    chunk.forEach(person -> log.info(person.toString()));
    List<Person> listPerson = new ArrayList<>();
    chunk.forEach(listPerson::add);
    personService.saveAll(listPerson);
  }
}
