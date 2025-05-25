package com.likelion.moamoa.domain.reference.repository;

import com.likelion.moamoa.domain.reference.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    // 파일명 중복되는지 안되는지
    boolean existsByName(String name);

    // folderId로 폴더 안에 속한 래퍼런스들 조회
    List<Reference> findAllByFolder_FolderId(Long folderId);

    // folderId로 폴더 안에 속한 래퍼런스의 개수 반환
    Long countReferenceByFolder_FolderId(Long folderId);
}
