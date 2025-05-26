package com.likelion.moamoa.common.question.repository;

import com.likelion.moamoa.common.question.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {
    // 레퍼런스가 있는지 없는지 확인
    boolean existsByReference_ReferenceId(Long referenceId);

    List<Recommendation> findAllByFolder_FolderId(Long folderId);
}
