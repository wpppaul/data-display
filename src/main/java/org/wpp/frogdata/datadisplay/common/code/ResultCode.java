package org.wpp.frogdata.datadisplay.common.code;

/**
 * @Auther: erhu
 * @Date: 2019/1/09 15:06
 * @Description:接口返回状态码
 */
public enum ResultCode {
    REQUEST_FAIL(99, "请求失败"),
    REQUEST_SUCCESS(100, "请求成功"),
    RESULT_SUCCESS(199, "结果成功"),
    FILE_READ_FAIL(131, "文件读取失败"),
    RESULT_FAIL(299, "结果失败");

    //状态码
    private int code;
    //状态信息
    private String message;

    ResultCode() {
    }

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultCode getByCode(int code) {
        ResultCode[] resultCodes = ResultCode.values();
        for (ResultCode resultCode : resultCodes
        ) {
            if (resultCode.code() == code) {
                return resultCode;
            }
        }
        return null;
    }
}
