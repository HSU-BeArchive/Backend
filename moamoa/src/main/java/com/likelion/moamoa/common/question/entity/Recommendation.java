package com.likelion.moamoa.common.question.entity;

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

    private String question;

    @OneToOne
    @JoinColumn(name = "reference_id")
    private Reference reference;

    public Recommendation(String question, Reference reference) {
        this.question = question;
        this.reference = reference;
    }

}
