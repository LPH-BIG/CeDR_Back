package cn.ac.ngdc.cellular_response.result;

public class ResultFactory {

    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "success", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.status, message, data);
    }

    public static Result buildResult(Integer resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
