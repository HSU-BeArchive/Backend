package com.likelion.moamoa.domain.recommendation.web.controller;

import com.likelion.moamoa.domain.recommendation.service.RecommendationService;
import com.likelion.moamoa.domain.recommendation.web.dto.CreateRecommendationReq;
import com.likelion.moamoa.domain.recommendation.web.dto.CreateRecommendationRes;
import com.likelion.moamoa.domain.recommendation.web.dto.RecommendationDetailRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createRecommendation(@RequestBody CreateRecommendationReq createRecommendationReq) {

        CreateRecommendationRes createRecommendationRes = recommendationService.createRecommendation(createRecommendationReq.getReferenceId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createRecommendationRes));
    }


    @GetMapping("/{referenceId}")
    public ResponseEntity<SuccessResponse<?>> getRecommendation(@PathVariable Long referenceId) {
        RecommendationDetailRes recommendationDetailRes = recommendationService.getRecommendation(referenceId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(recommendationDetailRes));
    }
}
