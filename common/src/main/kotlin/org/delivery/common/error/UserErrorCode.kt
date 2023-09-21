package org.delivery.common.error

enum class UserErrorCode(
    private val httpStatusCode: Int,
    private val errorCode: Int,
    private val description: String,
) : ErrorCodeIfs {

/*    OK(200, 200, "success"),
    BAD_REQUEST(400, 500, "bad request"),
    SERVER_ERROR(500, 500, "server error"),
    NULL_POINT(500, 512, "null point"),*/
    USER_NOT_FOUND(400, 400, "사용자를 찾을 수 없음.")

    ;

    override fun getHttpStatusCode(): Int {
        return this.httpStatusCode
    }

    override fun getErrorCode(): Int {
        return this.errorCode
    }

    override fun getDescription(): String {
        return this.description
    }


}