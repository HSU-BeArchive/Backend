package com.likelion.moamoa.common.question.service;

import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.common.question.web.dto.CreateQuestionReq;
import com.likelion.moamoa.common.question.web.dto.CreateQuestionRes;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderReq;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderRes;

public interface RecommendationService {
    // 추천 질문 생성
    CreateQuestionRes createRecommendation(Long referenceId);
}
