package com.likelion.moamoa.domain.reference.repository;

import com.likelion.moamoa.domain.reference.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
