package com.gdg.gdgback.Agent.Service.Implement;

import com.gdg.gdgback.Agent.Context;
import com.gdg.gdgback.Agent.Service.ContextService;
import jakarta.servlet.http.HttpSession;

public class SessionContextService implements ContextService {
    private final int HISTORY_LIMIT=5;

    @Override
    public Context getContext(HttpSession session) {
        Context context = (Context)session.getAttribute("context");
        if(context == null) {
            context = new Context();
            session.setAttribute("context", context);
        }
        return context;
    }

    @Override
    public void saveContext(HttpSession session, Context context) {
        session.setAttribute("context", context);
    }
}
