package com.likelion.moamoa.domain.reference.web.dto;

import java.util.List;

public record ReferenceSummaryRes(
        Long folderId,
        List<ReferenceSummary> referenceSummaryList
) {
    public record ReferenceSummary(
            Long referenceId,
            String referenceName,
            Long referenceOrder,
            String referenceImgUrl
    ) {
    }
}
