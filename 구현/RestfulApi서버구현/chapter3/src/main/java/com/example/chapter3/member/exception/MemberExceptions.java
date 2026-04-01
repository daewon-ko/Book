package com.example.chapter3.member.exception;

public enum MemberExceptions {

    NOT_FOUND("NOT_FOUND", 404),
    DUPLICATE("DUPLICATE", 409),
    INVALID("INVALID_PARAMS", 400),
    BAD_CREDENTIALS("BAD_CREDENTIALS", 401);

    private MemberException memberException;


    MemberExceptions(String message, int code) {
        this.memberException = new MemberException(message, code);
    }

    public MemberException getMemberException() {
        return memberException;
    }
}
