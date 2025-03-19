package com.gdg.gdgback.Message;

public class MessageNotExistsException extends RuntimeException {
    public MessageNotExistsException() {
        super("존재하지 않는 메시지입니다.");
    }
}
