package com.onebucket.domain.univInfoManage.dao;

import com.onebucket.domain.univInfoManage.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    Optional<University> findByName(String name);
    boolean existsByName(String name);
}
