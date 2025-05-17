package com.likelion.moamoa.global.response.code;

public interface BaseResponseCode {
    // 에러 코드 인터페이스 : 코드, HPPT 상태, 메시지 정의
    String getCode();
    int getHttpStatus();
    String getMessage();
}