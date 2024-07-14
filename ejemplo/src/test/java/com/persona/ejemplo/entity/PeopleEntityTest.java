package com.persona.ejemplo.entity;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PeopleEntityTest {
    private final  PeopleEntity peopleEntity = new PeopleEntity();
    private String firstname, lastname ;
    private int years;
    private Long id;

    @BeforeEach
    public void init(){
        firstname = "exampleName";
        lastname = "exampleLastName";
        years = (int) Math.random();
        id = (long) Math.random();

    }


    @Test
    public void setFirstNameTest(){
        peopleEntity.setFirstName(firstname);
        Assertions.assertEquals(firstname, peopleEntity.getFirstName());
    };

    @Test
    public void  setLastNameTest(){
        peopleEntity.setLastName(lastname);
        Assertions.assertEquals(lastname, peopleEntity.getLastName());
    }

    @Test
    public void  setYearsTest(){
        peopleEntity.setYears(years);
        Assertions.assertEquals(years, peopleEntity.getYears());
    }


    @Test
    public void setIdTest(){
        peopleEntity.setId(id);
        Assertions.assertEquals(id, peopleEntity.getId());
    }



}
