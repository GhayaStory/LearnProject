package ghaya.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 通用返回响应
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CommonResp<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResp(ResultCode resultCode) {
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
    }

    public CommonResp(ResultCode resultCode, T data) {
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
        this.data = data;
    }

    public CommonResp(Integer code,String message) {
        this.code=code;
        this.message=message;
    }

    public static <T> CommonResp create(ResultCode resultCode) {
        return new CommonResp( resultCode);
    }


    public static <T> CommonResp getErrorResult(String message) {
        return new CommonResp(-1,message);
    }

    public static <T> CommonResp create(ResultCode resultCode, T data) {
        return new CommonResp( resultCode,data);
    }
}