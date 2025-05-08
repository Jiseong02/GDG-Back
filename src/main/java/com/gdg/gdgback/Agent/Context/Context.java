package com.gdg.gdgback.Agent.Context;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Context {
    String summary;
    List<DialogueEntry> history;

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[Summary of previous dialogue]\n");
        if (summary == null) {
            sb.append("(No summary yet)");
        } else {
            sb.append(summary);
        }
        sb.append("\n\n");

        sb.append("[Recent History]\n");
        if (history == null || history.isEmpty()) {
            sb.append("(No dialogue history)");
        } else {
            for (DialogueEntry entry : history) {
                sb.append("User: ").append(entry.getUser()).append("\n");
                sb.append("Therapist: ").append(entry.getModel()).append("\n");
            }
        }
        return sb.toString();
    }
}
