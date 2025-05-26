package com.likelion.moamoa.common.chat.repository;

import com.likelion.moamoa.common.chat.entitiy.Chat;
import com.likelion.moamoa.common.keyword.entity.Keyword;
import com.likelion.moamoa.common.question.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByRecommendation_RecommendationIdOrderByCreatedAtAsc(Long recommendationId);
}
