package com.muxingzhe.check.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用返回响应体，返回JSON，返回格式为
 *  {
 *     code：200
 *     msg：SUCCESS
 *     data：{
 *        JSONKey：JSONValue
 *     }
 *     total:pageNum
 *  }
 * @author kampf
 * @date 2019/7/19 11:55
 */
@ApiModel(description = "自定义返回响应类")
@Data
public class Result {

    @ApiModelProperty(value = "返回状态码")
    private int code;

    @ApiModelProperty(value = "返回信息")
    private String msg;

    @ApiModelProperty(value = "返回值")
    private Object data;

    @ApiModelProperty(value = "判断用校验")
    private boolean status;

    @ApiModelProperty(value = "总条数")
    private int total;

    /**
     * 打印JSON字符串
     * @return
     */
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"code\":").append(code);
        sb.append(", \"msg\":").append(msg);
        sb.append(", \"data\":").append(data);
        sb.append(", \"status\":").append(status);
        sb.append(", \"total\":").append(total);
        sb.append("}");

        return sb.toString();
    }

    /**
     * 返回成功的基本构造函数
     * @param data
     */
    private Result(Object data, boolean status, int total, String msg){
        this.code = 200;
        this.msg = msg;
        this.data = data;
        this.status = status;
        this.total = total;
    }

    /**
     * 返回失败的基本构造函数
     * @param code
     * @param msg
     */
    private Result(int code, String msg){
        this.code = code;
        this.msg = msg;
        this.status = Boolean.FALSE;
        this.total = 0;
    }

    /**
     * 返回成功时调用函数
     * @param data
     * @param msg
     * @return
     */
    public static Result success(Object data, boolean status, int total, String msg){
        return new Result(data, status, total, msg);
    }

    /**
     * <p>快速仅有data的返回对象</p>
     * @description:
     * @param: data
     * @return: com.muxingzhe.check.utils.Result
     * @author kampf
     * @date: 2024/6/12 11:02
     */
    public static Result success(Object data){
        return new Result(data, Boolean.TRUE, 0,  "请求成功");
    }

    public static Result success(){
        return new Result("", Boolean.TRUE, 0,  "请求成功");
    }

    /**
     * 返回失败时调用函数
     * @param result
     * @param args
     * @return
     */
    public static Result error(Result result, Object... args){
        String msg = String.format(result.getMsg(), args);
        return new Result(result.getCode(), msg);
    }

    /**服务端基础异常枚举*/
    public static Result SERVER_ERROR = new Result(500,"系统异常： %s");
    public static Result BIND_ERROR = new Result(501,"(绑定异常)参数校验异常： %s");
    public static Result FILE_UPLOAD_ERROR = new Result(502,"文件上传异常： %s");
    public static Result TOKEN_ERROR = new Result(503,"TOKEN异常: %s");
    public static Result REQUEST_ERROR = new Result(504,"非法请求: %s");
    public static Result REQUEST_OVER_LIMIT = new Result(505,"请求次数过多: %s");

}
