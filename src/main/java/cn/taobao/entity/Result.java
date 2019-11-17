package cn.taobao.entity;

/**
 * Created by hzz on 2019/1/24
 */
public class Result {
    public enum CODE {
        SUCCESS(0), FAIL(-1);
        private final int code;

        private CODE(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    private Integer code;
    private String message;
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
