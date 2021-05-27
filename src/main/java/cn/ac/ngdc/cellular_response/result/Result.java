package cn.ac.ngdc.cellular_response.result;

import lombok.Data;


@Data
public class Result {
    private Integer status;
    private String message;
    private Object result;

    Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.result = data;
    }
}
