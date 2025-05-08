package com.gdg.gdgback.Agent.Context;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Context {
    List<DialogueEntry> history;
    String summary;
}
