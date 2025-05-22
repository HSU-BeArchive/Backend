package com.likelion.moamoa.common.question.web.controller;

import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.common.question.service.RecommendationService;
import com.likelion.moamoa.common.question.web.dto.CreateQuestionReq;
import com.likelion.moamoa.common.question.web.dto.CreateQuestionRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createRecommendation(@RequestBody CreateQuestionReq createQuestionReq) {

        CreateQuestionRes createQuestionRes = recommendationService.createRecommendation(createQuestionReq.getReferenceId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createQuestionRes));
    }
}
