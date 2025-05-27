package com.likelion.moamoa.domain.keyword.service;

import com.likelion.moamoa.domain.keyword.web.dto.ExtractKeywordReq;
import com.likelion.moamoa.domain.keyword.web.dto.ExtractKeywordRes;
import com.likelion.moamoa.domain.keyword.web.dto.KeywordSummaryRes;

import java.util.List;

public interface KeywordService {
    // 키워드 추출
    ExtractKeywordRes extractKeyword(ExtractKeywordReq extractKeywordReq);

    // 키워드 조회
    KeywordSummaryRes getKeywords(Long folderId);
}
