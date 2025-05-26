package com.likelion.moamoa.domain.reference.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyReferenceReq {
    // 둘 다 NULL 가능
    private String referenceName;
    private String referenceDescription;
}
