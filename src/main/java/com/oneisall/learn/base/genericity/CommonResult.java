package com.oneisall.learn.base.genericity;

/**
 * 通用的易使用返回结果
 * 用于controller与service,以及Http返回通用的结果
 * <p>example:</p>
 * <blockquote><pre>
 *      UserInfo user = new UserInfo();
 *       CommonResult<UserInfo> result = CommonResult.success("aaa", user);
 *       UserInfo data = result.getData();
 *       assert user == data;
 *  </pre></blockquote>
 * @author oneisall
 * @version v1 2019/4/12 18:02
 */
public class CommonResult<T> {

    /**
     * 状态码,标志请求成功与否,与业务无关,备用字段
     * 200,请求成功,标志是请求成功了
     * 500,服务器错误...
     * 304,权限等
     * 注意:此值与业务逻辑[无关],与权限验证等操作等[相关]
     */
    private int code = 200;

    /**
     * 响应业务状态
     * false:处理成功
     * true:处理失败,处理失败应该返回响应的消息
     */
    private boolean status;

    /**
     * 响应消息
     * 业务返回消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private T data;

    @SuppressWarnings("unused")
    private CommonResult() {

    }

    /**
     * 使之失败
     *
     * @return 原对象
     */
    public CommonResult failed() {
        this.status = false;
        return this;
    }

    /**
     * 使之成功
     *
     * @return 原对象
     */
    public CommonResult success() {
        this.status = true;
        return this;
    }

    /**
     * 使之异常
     *
     * @return 原对象
     */
    public CommonResult error() {
        return error(500);
    }

    /**
     * 使之异常
     *
     * @return 原对象
     */
    @SuppressWarnings("all")
    public CommonResult error(int code) {
        this.status = false;
        this.code = code;
        return this;
    }

    private CommonResult(boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @SuppressWarnings("all")
    public static <T> CommonResult<T> build(boolean status, String msg, T data) {
        return new CommonResult<>(status, msg, data);
    }

    //success

    public static <T> CommonResult<T> success(String msg, T data) {
        return build(true, msg, data);
    }

    public static <T> CommonResult<T> success(String msg) {
        return build(true, msg, null);
    }

    //failed

    public static <T> CommonResult<T> failed(String msg, T data) {
        return build(false, msg, data);
    }

    public static <T> CommonResult<T> failed(String msg) {
        return build(false, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
