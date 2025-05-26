package com.likelion.moamoa.common.keyword.service;

import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordReq;
import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordRes;

import java.util.List;

public interface KeywordService {
    List<ExtractKeywordRes> extractKeyword(ExtractKeywordReq extractKeywordReq);
}
