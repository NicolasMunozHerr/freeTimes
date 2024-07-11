package com.persona.ejemplo.controller;


import com.persona.ejemplo.entity.PeopleEntity;
import com.persona.ejemplo.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PeopleController {

    private final PeopleService peopleService;

    private final PagedResourcesAssembler<PeopleEntity> pagedResourcesAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PeopleEntity> createPeople(@RequestBody PeopleEntity peopleEntity){
        return ResponseEntity.ok(peopleService.createPeople(peopleEntity));
    }


    @GetMapping("/{id}")
    public ResponseEntity< PeopleEntity> finbyId(@PathVariable Long id ){
        if(id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PeopleEntity());
        }else{
            return ResponseEntity.of(peopleService.findByIdPeople(id));

        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PeopleEntity>> listAllPeople(){
        return ResponseEntity.ok(peopleService.listPeopleEntities());
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<PeopleEntity>> listPeople(
            @RequestParam String search,
            @RequestParam(required = false, defaultValue = "0") int page ,
            @RequestParam(required = false, defaultValue = "10") int size
    ){
        return ResponseEntity.ok(peopleService.listByText(search, page, size));
    }

}
