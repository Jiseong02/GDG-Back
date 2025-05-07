package com.gdg.gdgback.Agent.Service;

import com.gdg.gdgback.Agent.Context;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface ContextService {
    Context getContext(HttpSession session);
    void saveContext(HttpSession session, Context context);
}
