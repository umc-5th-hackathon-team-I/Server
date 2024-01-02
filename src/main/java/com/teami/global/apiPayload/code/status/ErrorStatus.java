package com.teami.global.apiPayload.code.status;

import com.teami.global.apiPayload.code.BaseErrorCode;
import com.teami.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    MEMBER_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "이미 존재하는 아이디 입니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    NO_AUTHORIZATION(HttpStatus.BAD_REQUEST, "MEMBER4003", "권한이 없습니다."),

    // 캘린더 관련 에러
    CALENDAR_NOT_FINISHED(HttpStatus.BAD_REQUEST, "CALENDAR4001", "이미 수행중인 캘린더가 있습니다."),


    // 미션 관련 에러
    CREATE_MISSION_FAILED(HttpStatus.BAD_REQUEST, "MISSION4001", "미션 생성에 실패햇습니다."),
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4002", "미션을 찾지 못했습니다."),
    MISSION_DEADLINE_EXCEEDED(HttpStatus.BAD_REQUEST, "MISSION4003", "미션은 당일에만 성공으로 변경 가능합니다.-"),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4004", "이미 성공한 미션입니다."),
    NICKNAME_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 존재하는 닉네임 입니다."),
    PASSWORD_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4004", "비밀번호가 일치하지 않습니다."),

    // 친구 확인 에러
    FRIEND_NOT_FOUND(HttpStatus.BAD_REQUEST, "FRIEND4001", "해당하는 친구가 없습니다"),
    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "음식이이 없습니다."),


    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4000", "가게가 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
