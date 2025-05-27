package com.likelion.moamoa.domain.keyword.repository;

import com.likelion.moamoa.domain.keyword.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword ,Long> {
    // folderId로 찾은 키워드 List로 저장
    List<Keyword> findByFolder_FolderId(Long folderId);
}
