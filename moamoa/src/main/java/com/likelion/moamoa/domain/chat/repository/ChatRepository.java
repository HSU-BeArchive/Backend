package com.likelion.moamoa.domain.chat.repository;

import com.likelion.moamoa.domain.chat.entitiy.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByRecommendation_RecommendationIdOrderByCreatedAtAsc(Long recommendationId);
}
