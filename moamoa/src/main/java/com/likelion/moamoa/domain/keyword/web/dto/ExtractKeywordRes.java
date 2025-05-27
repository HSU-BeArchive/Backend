package com.likelion.moamoa.domain.keyword.web.dto;

public record ExtractKeywordRes(
        Long folderId,
        Long keywordId,
        String keywordName,
        Long keywordCount
) {
}
