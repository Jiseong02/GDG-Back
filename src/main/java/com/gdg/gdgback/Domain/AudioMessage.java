package com.gdg.gdgback.Domain;

public interface AudioMessage {
    String getId();
    String getCounselId();
    String getRole();
    byte[] getContent();
}
