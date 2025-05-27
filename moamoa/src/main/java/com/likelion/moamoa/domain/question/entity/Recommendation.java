package com.likelion.moamoa.domain.question.entity;

import com.likelion.moamoa.domain.chat.entitiy.Chat;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.reference.entity.Reference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Recommendation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECOMMENDATION_ID")
    private Long recommendationId;

    @Column(columnDefinition = "TEXT")
    private String question;

    @OneToOne
    @JoinColumn(name = "REFERENCE_ID") // FK 가짐
    private Reference reference;

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Chat> chatList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FOLDER_ID")
    private Folder folder;

}
