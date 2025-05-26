package com.likelion.moamoa.common.keyword.web.dto;

public record ExtractKeywordRes(
        Long folderId,
        Long keywordId,
        String keywordName,
        Long keywordCount
) {
}
