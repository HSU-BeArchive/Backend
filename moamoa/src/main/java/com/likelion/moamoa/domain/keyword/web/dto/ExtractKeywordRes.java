package com.likelion.moamoa.domain.keyword.web.dto;

import java.util.List;

public record ExtractKeywordRes(
        Long folderId,
        List<ExtractKeyword> keywordList
) {
    public record ExtractKeyword(
            Long keywordId,
            String keywordName,
            Long keywordCount){

    }
}
