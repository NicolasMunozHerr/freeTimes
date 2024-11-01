package com.batch.steps;

import com.batch.entities.Person;
import java.nio.charset.StandardCharsets;
import lombok.Builder.Default;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class PersonItemReader extends FlatFileItemReader<Person> {

  public PersonItemReader() {
    setName("readPersons");
    setResource(new ClassPathResource("ejemplo/persons.csv"));
    setLinesToSkip(1);
    setEncoding(StandardCharsets.UTF_8.name());
    setLineMapper(getLineMapper());
  }

  public LineMapper<Person> getLineMapper(){
    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();

    // marcar nombres de columnas
    String[] columns = new String[]{"name", "lastName", "age"};
    delimitedLineTokenizer.setNames(columns);
    //Marcar indices
    int[] indexFields = new int[]{0,1,2};
    delimitedLineTokenizer.setIncludedFields(indexFields);

    delimitedLineTokenizer.setDelimiter(",");

    //Marcar al target para convertir
    BeanWrapperFieldSetMapper<Person> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Person.class);

    lineMapper.setLineTokenizer(delimitedLineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

}
