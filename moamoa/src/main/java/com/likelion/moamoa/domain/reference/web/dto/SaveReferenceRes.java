package com.likelion.moamoa.domain.reference.web.dto;

public record SaveReferenceRes(
        Long folderId,
        Long referenceId,
        String name,
        String description,
        String imgUrl
) {
}
