package com.likelion.moamoa.common.question.entity;

import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.reference.entity.Reference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;

    @Column(columnDefinition = "TEXT")
    private String question;

    @OneToOne
    @JoinColumn(name = "reference_id")
    private Reference reference;

    @OneToOne
    @JoinColumn(name = "User_id")
    private User user;

    public Recommendation(String question, Reference reference) {
        this.question = question;
        this.reference = reference;
    }

}
