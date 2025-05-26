package com.likelion.moamoa.common.keyword.repository;

import com.likelion.moamoa.common.keyword.entity.Keyword;
import com.likelion.moamoa.domain.folder.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword ,Long> {
    //특정 폴더에 해당하는 엔티티들을 keywordCount 기준으로 내림차순으로 정렬
    List<Keyword> findByFolder_FolderId(Long folderId);
}
