package cn.cvte.dto;

import cn.cvte.enums.ResultCode;

import java.io.Serializable;


public class ResponseDto implements Serializable{
    private int code;
    private String msg;
    //private Map<String, Object> data;
    private Object data;

    public ResponseDto() {
    }

    public ResponseDto(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseDto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseDto success(Object data) {
        return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static ResponseDto success() {
        return new ResponseDto(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static ResponseDto fail() {
        return new ResponseDto(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg());
    }

    public static ResponseDto fali(String msg) {
        return new ResponseDto(ResultCode.FAIL.getCode(), msg);
    }

    public static ResponseDto erro() {
        return new ResponseDto(ResultCode.ERRO.getCode(), ResultCode.ERRO.getMsg());
    }

    public static ResponseDto erro(String msg) {
        return new ResponseDto(ResultCode.ERRO.getCode(), msg);
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
