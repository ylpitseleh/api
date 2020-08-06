package com.rest.api.advice.exception;
// C = Custom
/*
CUserNotFoundException은 RuntimeException을 상속받아 작성합니다.
총 3개의 메서드가 제공되는데. 메서드 중 CUserNotFoundException()을 사용하도록 하겠습니다.
혹시 Controller에서 메시지를 받아 예외 처리 시 사용이 필요하면 CUserNotFoundException(String msg)을 사용하면 됩니다.
 */

public class CUserNotFoundException extends RuntimeException {
    public CUserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserNotFoundException(String msg) {
        super(msg);
    }

    public CUserNotFoundException() {
        super();
    }
}