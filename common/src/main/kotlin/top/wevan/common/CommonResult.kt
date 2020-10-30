package top.wevan.common


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

class CommonResult(var code: Long, var message: String, var data: Any?) {

    protected fun CommonResult() {}

    companion object {
        /**
         * 成功返回结果
         *
         * @param data 获取的数据
         */
        fun success(data: Any): CommonResult {
            return CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data)
        }

        /**
         * 成功返回结果
         *
         * @param data 获取的数据
         * @param  message 提示信息
         */
        fun success(data: Any, message: String): CommonResult {
            return CommonResult(ResultCode.SUCCESS.getCode(), message, data)
        }

        /**
         * 失败返回结果
         * @param errorCode 错误码
         */
        fun failed(errorCode: IErrorCode): CommonResult {
            return CommonResult(errorCode.getCode(), errorCode.getMessage(), null)
        }

        /**
         * 失败返回结果
         * @param errorCode 错误码
         * @param message 错误信息
         */
        fun failed(errorCode: IErrorCode, message: String): CommonResult {
            return CommonResult(errorCode.getCode(), message, null)
        }

        /**
         * 失败返回结果
         * @param message 提示信息
         */
        fun failed(message: String): CommonResult {
            return CommonResult(ResultCode.FAILED.getCode(), message, null)
        }

        /**
         * 失败返回结果
         */
        fun failed(): CommonResult? {
            return failed(ResultCode.FAILED)
        }

        /**
         * 参数验证失败返回结果
         */
        fun validateFailed(): CommonResult? {
            return failed(ResultCode.VALIDATE_FAILED)
        }

        /**
         * 参数验证失败返回结果
         * @param message 提示信息
         */
        fun validateFailed(message: String): CommonResult? {
            return CommonResult(ResultCode.VALIDATE_FAILED.getCode(), message, null)
        }

        /**
         * 未登录返回结果
         */
        fun unauthorized(data: String): CommonResult? {
            return CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data)
        }

        /**
         * 未授权返回结果
         */
        fun forbidden(data: Any): CommonResult? {
            return CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data)
        }

    }

}