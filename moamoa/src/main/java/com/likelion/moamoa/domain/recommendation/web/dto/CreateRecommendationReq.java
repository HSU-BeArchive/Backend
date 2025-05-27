package com.likelion.moamoa.domain.recommendation.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRecommendationReq {
    @NotNull
    private Long referenceId;
}
