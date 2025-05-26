package com.likelion.moamoa.common.keyword.web.controller;

import com.likelion.moamoa.common.keyword.service.KeywordService;
import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordReq;
import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keyword")
@RequiredArgsConstructor

public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping("/extract")
    public ResponseEntity<SuccessResponse<?>> extractKeyword(
            @RequestBody @Valid ExtractKeywordReq extractKeywordReq) {
        List<ExtractKeywordRes> keywords = keywordService.extractKeyword(extractKeywordReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(keywords));
    }


}
