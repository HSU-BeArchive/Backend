package com.likelion.moamoa.common.question.entity;

import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.reference.entity.Reference;
import jakarta.persistence.*;
import lombok.*;

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

}
