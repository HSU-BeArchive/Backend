package com.likelion.moamoa.domain.keyword.web.dto;

import java.util.List;

public record KeywordSummaryRes(
        Long folderId,
        List<KeywordSummary> keywordList
) {
    public record KeywordSummary(
            Long keywordId,
            String keywordName,
            Long keywordCount) {

    }}
