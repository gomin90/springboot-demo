package com.youwon.kafkademo.common.code;

public enum CommonCode {
    responseOk("0000")
    ,requestFail("9999");

    private String code;

    CommonCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
