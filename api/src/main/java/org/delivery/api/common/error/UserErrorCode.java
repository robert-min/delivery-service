package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 400, "Wrong request")
    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;
    ;
}
