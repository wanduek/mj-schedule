package com.sparta.scheduleserver.model.entity.error;

public enum ErrorCode {

    // User 관련 예외 메시지
    USER_NOT_FOUND("U001", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("U002", "이미 존재하는 사용자입니다."),
    EMAIL_ALREADY_EXISTS("U003", "이미 존재하는 이메일입니다."),
    INVALID_PASSWORD("U004", "비밀번호가 일치하지 않습니다."),

    // Schedule 관련 예외 메시지
    SCHEDULE_NOT_FOUND("S001", "일정을 찾을 수 없습니다."),
    SCHEDULE_CREATION_FAILED("S002", "일정 생성에 실패했습니다."),
    SCHEDULE_UPDATE_FAILED("S003", "일정 수정에 실패했습니다."),

    // JWT 관련 예외 메시지
    INVALID_TOKEN("J001", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED("J002", "토큰이 만료되었습니다."),
    TOKEN_CREATION_FAILED("J003", "토큰 생성에 실패했습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
