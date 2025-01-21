package com.rouvsen.codeevalengine.dao.repository;

import com.rouvsen.codeevalengine.dao.entity.CodeSubmissionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeSubmissionRepository extends JpaRepository<CodeSubmissionEntity, Long> {

    Optional<CodeSubmissionEntity> findCodeSubmissionEntityById(Long id);
}
