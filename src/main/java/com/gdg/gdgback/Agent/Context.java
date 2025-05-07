package com.gdg.gdgback.Agent;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Context {
    public Context() {}

    List<String> history;
    String summary;
}
