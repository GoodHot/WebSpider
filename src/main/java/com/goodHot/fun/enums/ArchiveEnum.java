package com.goodHot.fun.enums;

public class ArchiveEnum {

    public enum Status {
        WAIT(10), REJECT(20), PASS(30);
        public int status;

        Status(int status) {
            this.status = status;
        }
    }

}
