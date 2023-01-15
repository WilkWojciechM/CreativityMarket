package com.wilkwm.pracainz.domain.commission;

import com.wilkwm.pracainz.domain.project.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommissionRepository extends CrudRepository<Commission, Long> {

    List<Commission> findAllByField_NameIgnoreCase(String field);
    List<Commission> findAllByUser_Name(String name);
    //List<Commission> findAllByAvailabilityIsTrue();
    List<Commission> findAllByAvailabilityIsTrueAndJobOfferIsFalse();
    List<Commission> findAllByAvailabilityIsTrueAndJobOfferIsTrue();

}
