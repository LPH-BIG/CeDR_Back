package cn.ac.ngdc.cellular_response.result;


public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int status;

    ResultCode(Integer status) {
        this.status = status;
    }
}
