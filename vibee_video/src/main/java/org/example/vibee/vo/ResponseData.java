package org.example.vibee.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应JSON数据结构
 */
@Data
public class ResponseData implements Serializable {
    /**
     * 等于200:正常;其它:失败
     */
    private int code = 200;
    /**
     * 错误信息:当code不等于200时存在
     */
    private String message;
    /**
     * 请求成功时,返回请求相关的数据信息,仅当code==200时存在
     */
    private Object data;

    public ResponseData() {
        super();
    }


    public ResponseData(Object data) {
        super();
        this.data = data;
    }

    public ResponseData(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public  static ResponseData success(Object data) {
        return new ResponseData(data);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public  static ResponseData fail(int code, String message)  {
        if (200 == code) {
            throw new IllegalArgumentException("错误业务状态码不可以为200");
        }
        return new ResponseData(code, message);
    }

    /**
     * 创建业务失败
     *
     * @param message
     * @return
     */
    public  static ResponseData createFail(String message) {
        return new ResponseData(100901, message);
    }

    /**
     * 修改业务失败
     *
     * @param message
     * @return
     */
    public  static ResponseData modifyFail(String message) {
        return new ResponseData(100902, message);
    }


    /**
     * 通用错误状态-缺少请求参数
     */
    public  static ResponseData missParam(String message) {
        return ResponseData.fail(100400, "缺少请求参数【" + message + "】");
    }
}
