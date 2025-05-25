package com.likelion.moamoa.common.chat.entitiy;

import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageRole messageRole;

    @ManyToOne
    @JoinColumn(name = "RECOMMENDATION_ID")
    private Recommendation recommendation;
}
