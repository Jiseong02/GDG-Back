package com.gdg.gdgback.Agent.Context;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface ContextService {
    Context getContext(HttpSession session);
    void updateContext(HttpSession session, DialogueEntry dialogue);
    String generateUpdatedSummary(String summary, DialogueEntry dialogue);
}
