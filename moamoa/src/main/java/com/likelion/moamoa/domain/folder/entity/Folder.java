package com.likelion.moamoa.domain.folder.entity;

import com.likelion.moamoa.domain.reference.entity.Reference;
import jakarta.persistence.*;
import lombok.*;
import com.likelion.moamoa.domain.auth.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Folder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLDER_ID")
    private Long folderId;

    @Column(name = "FOLDER_NAME")
    private String folderName;

    // 폴더 순서(드래그 기능 구현시 사용)
    @Column(name = "FOLDER_ORDER")
    private Long folderOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reference> references = new ArrayList<>();

    // 편의 메서드 -> 폴더가 삭제 될 때, 폴더 안에 사진들을 삭제 하기 위함
//    public void removeReference(Reference reference) {
//        references.remove(reference);
//        reference.setFolder(null);
//    }
}
