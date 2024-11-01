package com.batch.steps;

import com.batch.entities.Person;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {


  @Override
  public Person process(Person item) throws Exception {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");

    item.setCreatedAT(formatter.format(date));
    return item;
  }
}
