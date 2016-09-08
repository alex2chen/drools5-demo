package com.xfboy.simple;

/**
 * HelloWord
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/5
 */
public class HelloWord {
    public static final int SAYHELLO = 0;
    public static final int SAYBYE = 1;
    private int sayType;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSayType() {
        return sayType;
    }

    public void setSayType(int sayType) {
        this.sayType = sayType;
    }

    @Override
    public String toString() {
        return "HelloWord{" +
                "sayType=" + sayType +
                ", message='" + message + '\'' +
                '}';
    }
}
