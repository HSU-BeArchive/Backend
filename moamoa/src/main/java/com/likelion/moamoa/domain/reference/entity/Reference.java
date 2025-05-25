package com.likelion.moamoa.domain.reference.entity;

import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.domain.folder.entity.Folder;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Reference {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFERENCE_ID")
    private Long referenceId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "REFERENCE_ORDER")
    private Long referenceOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLDER_ID", nullable = false)
    private Folder folder;

    @OneToOne(mappedBy = "reference", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Recommendation recommendation;

}
