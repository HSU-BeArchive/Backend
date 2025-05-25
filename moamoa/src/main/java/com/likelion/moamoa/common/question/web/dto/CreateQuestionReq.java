package com.likelion.moamoa.common.question.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CreateQuestionReq {
    @NotNull
    private Long referenceId;
}
