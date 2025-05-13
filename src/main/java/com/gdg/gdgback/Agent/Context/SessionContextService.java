package com.gdg.gdgback.Agent.Context;

import com.gdg.gdgback.Agent.Core.GenerativeModelApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SessionContextService implements ContextService {
    private final GenerativeModelApi model;

    @Autowired
    SessionContextService(GenerativeModelApi model) {
        this.model = model;
    }

    @Override
    public Context getContext(HttpSession session) {
        Context context = (Context)session.getAttribute("context");
        if(context == null) {
            context = Context.builder()
                    .summary("")
                    .history(new ArrayList<>())
                    .build();
            session.setAttribute("context", context);
        }
        return context;
    }

    @Override
    public void updateContext(HttpSession session, DialogueEntry dialogue) {
        final int HISTORY_LIMIT = 2;

        Context context = (Context)session.getAttribute("context");
        context.history.add(dialogue);

        if(context.history.size() > HISTORY_LIMIT) {
            context.summary = generateUpdatedSummary(context.summary, context.history.getFirst());
            context.history = new ArrayList<>(context.history.subList(1, HISTORY_LIMIT + 1));
        }

        session.setAttribute("context", context);
    }

    @Override
    public String generateUpdatedSummary(String summary, DialogueEntry dialogue) {
        final String DIRECTION = """
            [Direction of summarization]
            - Summarize the core content of the preceding dialogue.
            - Emphasize any **changes or trends** in the user's emotional or physical state.
            - Clearly present the relationship between the user's concerns and the counselor's interventions.
            - The summary must not exceed 3 sentences.
            
            [Example]
            User: {Initial emotions/symptoms → recent changes}
            Counselor: {Follow-up questions, emotional validation, or coping strategies}
            """;

        String prompt = DIRECTION
                + "\n\n[Previous summary]\n" + summary
                + "\n\n[New dialogue]\n" + dialogue.toString()
                + "\n\n[Result of summarization]\n";

        return model.generateResponseInJapan(prompt);
    }
}
