package com.likelion.moamoa.common.question.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionReq {
    @NotNull
    private Long referenceId;
}
