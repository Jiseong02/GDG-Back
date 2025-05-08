package com.gdg.gdgback.Agent.Context;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DialogueEntry {
    public static DialogueEntry of(String user, String model) {
        return new DialogueEntry(user, model);
    }
    String user;
    String model;
}
