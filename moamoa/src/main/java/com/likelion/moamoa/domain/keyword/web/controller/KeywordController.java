package com.likelion.moamoa.domain.keyword.web.controller;

import com.likelion.moamoa.domain.keyword.service.KeywordService;
import com.likelion.moamoa.domain.keyword.web.dto.ExtractKeywordReq;
import com.likelion.moamoa.domain.keyword.web.dto.ExtractKeywordRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keywords")
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
    @GetMapping("/folder/{folderId}")
    public ResponseEntity<SuccessResponse<?>> getKeywords(@PathVariable Long folderId){

        List<ExtractKeywordRes> keywords = keywordService.getKeywords(folderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(keywords));
    }


}
