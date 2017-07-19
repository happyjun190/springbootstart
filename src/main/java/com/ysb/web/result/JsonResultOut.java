package com.ysb.web.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wushenjun on 2017/4/19.
 */
@ApiModel
public class JsonResultOut<T> {

    @ApiModelProperty(value = "状态码", example="40001", required = true, position=-2)
    private String code;
    @ApiModelProperty(value = "返回消息", example="恭喜，成功！", required = true, position=-1)
    private String message;

    @ApiModelProperty(value = "具体数据", required = true)
    private T data;


    public static JsonResultOut success(){
        return new JsonResultOut(ReturnCode.SUCCESS,"操作成功！",null);
    }

    public static JsonResultOut success(Object data){
        return new JsonResultOut(ReturnCode.SUCCESS,"操作成功！",data);
    }

    public JsonResultOut(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResultOut(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResultOut() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}