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

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    // 폴더 순서(드래그 기능 구현시 사용)
    private Long folderOrder;
}
