package com.likelion.moamoa.domain.reference.web.dto;

public record ModifyReferenceRes(
       Long referenceId,
       String referenceNameBefore,
       String referneceNameAfter,
       String referenceDescriptionBefore,
       String referenceDescriptionAfter
) {
}
