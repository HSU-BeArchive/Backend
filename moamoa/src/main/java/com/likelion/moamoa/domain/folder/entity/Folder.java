package com.likelion.moamoa.domain.folder.entity;

import jakarta.persistence.*;
import lombok.*;
import com.likelion.moamoa.domain.auth.entity.User;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLDER_ID")
    private Long folderId;

    @Column(name = "FOLDER_NAME")
    private String folderName;

    @Column(name = "FOLDER_COLOR")
    private String folderColor;

    // 폴더 순서(드래그 기능 구현시 사용)
    @Column(name = "FOLDER_ORDER")
    private Long folderOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
