package com.likelion.moamoa.domain.reference.web.dto;

import java.util.List;

public record ReferenceSummaryRes(List<ReferenceSummary> referenceSummaryList) {
    public record ReferenceSummary(
            Long referenceId,
            Long referenceOrder,
            String imgUrl
    ) {
    }
}
