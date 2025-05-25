package com.likelion.moamoa.domain.folder.repository;

import com.likelion.moamoa.domain.folder.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    // 폴더 중복
    boolean existsByFolderNameAndUser_UserId(String folderName, Long userUserId);
    // userId로 폴더 조회
    List<Folder> findAllByUser_UserId(Long userId);
    // userId에 몇개의 폴더가 있는지 확인
    Long countFolderByUser_UserId(Long userId);
}
