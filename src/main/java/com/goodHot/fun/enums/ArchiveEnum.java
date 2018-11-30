package com.goodHot.fun.enums;

public class ArchiveEnum {

    public enum Status {
        WAIT(10), REJECT(20), PASS(30);
        public int status;

        Status(int status) {
            this.status = status;
        }

        public static Status find(Integer status) {
            if (status == null) {
                return null;
            }
            for (Status val : values()) {
                if (val.status == status) {
                    return val;
                }
            }
            return null;
        }
    }

}
