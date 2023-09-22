package org.delivery.common.api

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.ErrorCodeIfs


data class Result(
    val resultCode: Int?=null,
    val resultMessage: String?=null,
    val resultDescription: String?=null,
) {

    // java 로 컴파일 시 static과 동일
    companion object {
        @JvmStatic
        fun OK(): Result {
            return Result(
                resultCode = ErrorCode.OK.getErrorCode(),
                resultMessage = ErrorCode.OK.getDescription(),
                resultDescription = "Success!!"
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = "Error!!"
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs, tx: Throwable): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = tx.localizedMessage
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs, description: String): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = description
            )
        }
    }


}