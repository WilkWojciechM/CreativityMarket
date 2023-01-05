package com.wilkwm.pracainz.domain.commission;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommissionRepository extends CrudRepository<Commission, Long> {

    List<Commission> findAllByField_NameIgnoreCase(String field);
    List<Commission> findAllByUser_Name(String name);

}
