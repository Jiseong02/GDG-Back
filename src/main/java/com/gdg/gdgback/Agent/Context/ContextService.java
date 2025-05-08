package com.gdg.gdgback.Agent.Context;

import jakarta.servlet.http.HttpSession;

public interface ContextService {
    Context getContext(HttpSession session);
    void updateContext(HttpSession session, DialogueEntry dialogue);
    String generateUpdatedSummary(String summary, DialogueEntry dialogue);
}
