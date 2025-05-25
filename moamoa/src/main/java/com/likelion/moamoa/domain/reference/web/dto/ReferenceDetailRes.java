package com.likelion.moamoa.domain.reference.web.dto;

public record ReferenceDetailRes(
        Long referenceId,
        String referenceName,
        String referenceDescription,
        String referenceImgUrl,
        Long referenceOrder,
        Long folderId
) {
}
