package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
* Token은  2000번대 에러
* */


@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), 2000, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST.value(), 2001, "만료된 토큰"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST.value(), 2002, "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 2003, "인증 에러 토큰 없음"),
    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;
}
