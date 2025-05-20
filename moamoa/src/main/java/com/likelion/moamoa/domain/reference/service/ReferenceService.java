package com.likelion.moamoa.domain.reference.service;

import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceReq;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceRes;

public interface ReferenceService {
    // 래퍼런스 저장
    SaveReferenceRes saveReference(Long folderId, SaveReferenceReq saveReferenceReq);
}
