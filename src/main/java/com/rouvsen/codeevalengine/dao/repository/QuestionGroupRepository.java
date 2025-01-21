package com.rouvsen.codeevalengine.dao.repository;

import com.rouvsen.codeevalengine.dao.entity.QuestionGroupEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionGroupRepository extends JpaRepository<QuestionGroupEntity, Long> {

    Optional<QuestionGroupEntity> findByName(String name); /* lazy initialize for submissions */

    @Query("SELECT qg FROM QuestionGroupEntity qg LEFT JOIN FETCH qg.codeSubmissions WHERE qg.name = :name")
    Optional<QuestionGroupEntity> findByNameWithSubmissions(@Param("name") String name);
}
