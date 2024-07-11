package com.persona.ejemplo.specification;

import com.persona.ejemplo.entity.PeopleEntity;
import org.springframework.data.jpa.domain.Specification;

public class PeopleSpecification {

    public static Specification<PeopleEntity> firstNameOrLastNameContainsIgnoreCase(String searchText){
        return (root, query, criteriaBuilder) -> {
            String searchTextLowerCase = searchText.toLowerCase();
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + searchTextLowerCase + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + searchTextLowerCase + "%")
            );
        };
    }
}


//SELECT * FROM people p where LOWER(firstname) like "%nicolas%" or LOWER(lastname) like "%lastname%"
