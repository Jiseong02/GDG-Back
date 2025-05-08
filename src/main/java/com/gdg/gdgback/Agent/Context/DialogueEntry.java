package com.gdg.gdgback.Agent.Context;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DialogueEntry {
    String user;
    String model;
}
