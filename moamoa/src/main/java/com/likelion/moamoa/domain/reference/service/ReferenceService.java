package com.likelion.moamoa.domain.reference.service;

import com.likelion.moamoa.domain.reference.web.dto.ReferenceSummaryRes;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceReq;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceRes;

public interface ReferenceService {
    // 래퍼런스 저장
    SaveReferenceRes saveReference(Long folderId, SaveReferenceReq saveReferenceReq);
    // 래퍼런스 모두 조회
    ReferenceSummaryRes getAllReference(Long folderId);
}
