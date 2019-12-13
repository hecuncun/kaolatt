package com.jxbn.kaolatt.base;

/**
 * Created by hecuncun on 2019/5/13
 */
public class BaseBean<T> {

    private static final String SUCCESSED_CODE="10001";//请求成功 code=10001;

    private String code;
    private String message;
    private T data;

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

    /**
     * 判断请求是否成功
     *
     */
    public boolean isSuccessed(){
        return SUCCESSED_CODE.equals(code);
    }
}
