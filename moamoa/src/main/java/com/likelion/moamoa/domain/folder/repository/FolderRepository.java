package com.likelion.moamoa.domain.folder.repository;

import com.likelion.moamoa.domain.folder.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository< Folder, Long> {
    // userId로 폴더 조회
    List<Folder> findAllByUser_UserId(Long userId);
}
