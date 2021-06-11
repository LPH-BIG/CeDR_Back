package cn.ac.ngdc.cellular_response.result;

import lombok.Data;

import java.util.Map;


@Data
public class Result {
    private Integer status;
    private String message;
    private Object data;
    private Meta meta;

    Result(Integer status, String message, Object data,Meta meta) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.meta = meta;
    }
}
