package com.likelion.moamoa.domain.reference.web.dto;

public record ReferenceDetailRes(
        Long referenceId,
        String name,
        String description,
        String imgUrl,
        Long referenceOrder,
        Long folderId
) {
}
