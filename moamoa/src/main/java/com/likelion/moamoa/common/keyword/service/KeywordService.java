package com.likelion.moamoa.common.keyword.service;

import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordReq;
import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordRes;

import java.util.List;

public interface KeywordService {
    // 키워드 추출
    List<ExtractKeywordRes> extractKeyword(ExtractKeywordReq extractKeywordReq);

    // 키워드 조회
    List<ExtractKeywordRes> getKeywords(Long folderId);
}
