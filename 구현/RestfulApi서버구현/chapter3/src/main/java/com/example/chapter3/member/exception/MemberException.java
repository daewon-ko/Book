package com.example.chapter3.member.exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{
    private String message;
    private int code;

    public MemberException(String message, int code) {
        this.message = message;
        this.code = code;
    }


}
