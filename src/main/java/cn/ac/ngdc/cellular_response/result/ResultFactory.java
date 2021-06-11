package cn.ac.ngdc.cellular_response.result;

public class ResultFactory {

    public static Result buildSuccessResult(Object data,Meta meta) {
        return buildResult(ResultCode.SUCCESS, "success", data,meta);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null,null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data, Meta meta) {
        return buildResult(resultCode.status, message, data,meta);
    }

    public static Result buildResult(Integer resultCode, String message, Object data, Meta meta) {
        return new Result(resultCode, message, data,meta);
    }
}
