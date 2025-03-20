package com.gdg.gdgback.Agent.Exception;

public class AgentFailedToRespondException extends RuntimeException {
    public AgentFailedToRespondException(String message) {
        super("Agent failed to respond from : " + message);
    }
}
