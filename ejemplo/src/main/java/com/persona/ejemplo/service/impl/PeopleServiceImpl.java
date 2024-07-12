package com.persona.ejemplo.service.impl;

import com.persona.ejemplo.entity.PeopleEntity;
import com.persona.ejemplo.repository.PeopleRepository;
import com.persona.ejemplo.service.PeopleService;
import com.persona.ejemplo.specification.PeopleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService{

    @Autowired
    private PeopleRepository peopleRepository;

    private final Sort sort = Sort.by(Sort.Order.asc("id"));


    @Override
    public PeopleEntity createPeople(PeopleEntity peopleEntity) {
        return peopleRepository.save(peopleEntity);
    }

    @Override
    public Optional<PeopleEntity> findByIdPeople(Long id) {
        return peopleRepository.findById(id);
    }

    @Override
    public List<PeopleEntity> listPeopleEntities() {
        return peopleRepository.findAll(sort);
    }

    @Override
    public Page<PeopleEntity> listByText(String searchText, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return peopleRepository.findAll(PeopleSpecification.firstNameOrLastNameContainsIgnoreCase(searchText), pageable);
    }


}
