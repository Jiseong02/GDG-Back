package com.gdg.gdgback.Message;

public class MessageNotExistsException extends RuntimeException {
    public MessageNotExistsException() {
        super("Message does not exist.");
    }
}
