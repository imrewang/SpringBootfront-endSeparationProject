package cn.lanqiao.springboot3.common;

import java.io.Serializable;

/**
 * @author
 */
public class Result<T> implements Serializable {//T - Type（Java 类）

    private static final long serialVersionUID = 1L;
    //我们在编码时，无法保证一个类的结构是永久不变的，凡是 implements serializable 的类，最好都显式的定义一个 serialVersionUID
    private int resultCode;
    private String message;
    private T data;

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
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


    public Result failure(String code) {
        return new Result(500, "服务错误");
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
