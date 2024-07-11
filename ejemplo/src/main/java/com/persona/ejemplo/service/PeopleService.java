package com.persona.ejemplo.service;


import com.persona.ejemplo.entity.PeopleEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PeopleService {

    PeopleEntity createPeople(PeopleEntity peopleEntity);

    Optional<PeopleEntity> findByIdPeople(Long id);

    List<PeopleEntity> listPeopleEntities();

    Page<PeopleEntity> listByText(String searcText, int page, int size);
}
