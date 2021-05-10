package com.example.caigouapp.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果类
 * @param <T>
 */
@Data
@NoArgsConstructor
public class Result<T> {
    private String code;//状态码
    private String msg;//信息
    private T data;//数据

    /**
     * 泛型数据构造函数
     * @param data
     */
    public Result(T data) {
        this.data = data;
    }

    /**
     * 操作成功不带数据返回结果
     * @return
     */
    public static Result success() {
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    /**
     * 操作成功返回带数据结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode(Constant.SUCCESS);
        result.setMsg("成功");

        return result;
    }

    /**
     * 操作失败返回结果
     * @param code
     * @param msg
     * @return
     */
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
