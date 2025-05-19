package com.likelion.moamoa.domain.folder.repository;

import com.likelion.moamoa.domain.folder.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository< Folder, Long> {
}
