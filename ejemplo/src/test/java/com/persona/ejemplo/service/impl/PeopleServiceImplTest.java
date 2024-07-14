package com.persona.ejemplo.service.impl;


import com.persona.ejemplo.entity.PeopleEntity;
import com.persona.ejemplo.repository.PeopleRepository;
import com.persona.ejemplo.specification.PeopleSpecification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class PeopleServiceImplTest {

  public PeopleEntity peopleEntity, peopleEntitySave, peopleEntity2;
  public List<PeopleEntity> listPeopleEntity = new ArrayList<>();

  private final Sort sort = Sort.by(Sort.Order.asc("id"));

  @Mock
  public PeopleRepository peopleRepository;
  @InjectMocks
  public PeopleServiceImpl peopleServiceImpl;

  @BeforeEach
  public void init(){
    peopleEntitySave = new PeopleEntity(null , "Sebastian", "Munoz", 24);
    peopleEntity = new PeopleEntity(1L, "Nicolas", "Munoz", 24);
    peopleEntity2 = new PeopleEntity(2L, "Sebastian", "Munoz", 24);
    listPeopleEntity.add(peopleEntity);
    listPeopleEntity.add(peopleEntity2);



  }

  @Test
  public void findByIdPeopleTest() {
    Long id = 1L;
    Mockito.when(peopleRepository.findById(id)).thenReturn(Optional.of(peopleEntity));

    Optional<PeopleEntity> response =  peopleServiceImpl.findByIdPeople(id);
    log.info("response  {}", response);
    Assertions.assertTrue(response.isPresent());
    Assertions.assertEquals(peopleEntity, response.get());
    Mockito.verify(peopleRepository).findById(id);
  }


  @Test
  public void createPeopleTest(){
    PeopleEntity peopleEntitySaved = peopleEntitySave;
    peopleEntitySaved.setId(2L);
    Mockito.when(peopleRepository.save(peopleEntitySave)).thenReturn(peopleEntitySaved);

    PeopleEntity  response = peopleServiceImpl.createPeople(peopleEntitySave);

    Assertions.assertTrue( response != null);
    Assertions.assertEquals(peopleEntitySaved , response);

    Mockito.verify(peopleRepository, Mockito.times(1)).save(peopleEntitySave);

  }


  @Test
  public void listPeopleEntitiesTest(){
    Mockito.when(peopleRepository.findAll(sort)).thenReturn(listPeopleEntity);

    List<PeopleEntity> responseList = peopleServiceImpl.listPeopleEntities();

    Assertions.assertTrue(responseList.size()>0);
    Assertions.assertEquals(responseList, listPeopleEntity);
    Mockito.verify(peopleRepository, Mockito.times(1)).findAll(sort);
  }

  @Test
  public void listByText() {
    int page = 0;
    int size = 10;
    String searchText = "mu";
    Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
    Page<PeopleEntity> peopleEntityPage = new  PageImpl <>(listPeopleEntity, pageable, listPeopleEntity.size()-1);
    Mockito.lenient().when(peopleRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
            .thenReturn(peopleEntityPage);
    Page<PeopleEntity> response = peopleServiceImpl.listByText(searchText, page,size);
    if(response !=  null){
      Assertions.assertEquals(peopleEntityPage.getContent().size(), response.getContent().size());
    }

    Mockito.verify(peopleRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class));


  }

}
