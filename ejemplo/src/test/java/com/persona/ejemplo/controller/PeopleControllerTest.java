package com.persona.ejemplo.controller;


import com.persona.ejemplo.entity.PeopleEntity;
import com.persona.ejemplo.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PeopleControllerTest {

    @Mock
    private PeopleService peopleService;

    @Mock
    private PagedResourcesAssembler<PeopleEntity> pagedResourcesAssembler;

    @InjectMocks
    private PeopleController peopleController;

    private PeopleEntity peopleEntity;
    private Long id;
    private Optional<PeopleEntity> optionalPeopleEntity;
    private List<PeopleEntity> peopleEntities;
    private int page, size ;
    private String searchText;
    private Page<PeopleEntity> peopleEntityPage;
    @BeforeEach
    public void setUp() {

        peopleEntity = new PeopleEntity(1L, "ejemplo1", "ejemplo2", 30);

        optionalPeopleEntity= Optional.of(new PeopleEntity(10L, "optionalFirstName", "optionalLastName", 30));


        peopleEntities = new ArrayList<>();
        peopleEntities.add(peopleEntity);

        page =0 ;
        size = 10 ;
        searchText = "opt";
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        peopleEntityPage = new PageImpl<>(peopleEntities, pageable, peopleEntities.size());

    }

    @Test
    public void createPeople(){
        PeopleEntity peopleEntityRequest = peopleEntity;
        peopleEntityRequest.setId(null);
        when(peopleService.createPeople(peopleEntityRequest)).thenReturn(peopleEntity);
        ResponseEntity<PeopleEntity> response = peopleController.createPeople(peopleEntity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(peopleEntity, response.getBody());

    }

    @Test
    public void finByIdWithLongTest(){
        Long id = anyLong();
        when(peopleService.findByIdPeople(id)).thenReturn(optionalPeopleEntity);

        ResponseEntity<PeopleEntity> response = peopleController.finbyId(any(Long.class));

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalPeopleEntity.get(), response.getBody());
        verify(peopleService).findByIdPeople(id);
    }

    @Test
    public void finByIdWithOutLongTest(){
        ResponseEntity<PeopleEntity> response = peopleController.finbyId(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().getId());
        assertNull(response.getBody().getLastName());
        assertNull(response.getBody().getFirstName());
        assertEquals(0 , response.getBody().getYears());
    }


    @Test
    public void listAllPeopleTest(){

        when(peopleService.listPeopleEntities()).thenReturn(peopleEntities);
        ResponseEntity<List<PeopleEntity> > response =  peopleController.listAllPeople();


        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(response.getBody().get(0), peopleEntity);
        verify(peopleService).listPeopleEntities();
    }

    @Test
    public void listPeople(){
        when(peopleService.listByText(any(String.class),any(Integer.class), any(Integer.class))).thenReturn(peopleEntityPage);

        ResponseEntity<Page<PeopleEntity>> response = peopleController.listPeople(searchText, page, size);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(response.getBody().getContent(), peopleEntities);

        verify(peopleService).listByText(searchText, page, size);

    }



}
