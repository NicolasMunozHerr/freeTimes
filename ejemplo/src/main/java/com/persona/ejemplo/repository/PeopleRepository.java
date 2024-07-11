package com.persona.ejemplo.repository;


import com.persona.ejemplo.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository  extends JpaRepository<PeopleEntity, Long> , JpaSpecificationExecutor<PeopleEntity> {


   List<PeopleEntity> findByFirstNameContainingOrLastNameContaining(String searchText, String searchText2);

}
