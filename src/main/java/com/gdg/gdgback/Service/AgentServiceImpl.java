package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;

@Service
public class AgentServiceImpl implements AgentService{
    @Autowired
    private GenerativeModel model;

    @Override
    public String invoke(Chat chat) throws IOException {
        GenerateContentResponse response = model.generateContent(chat.getText());
        return ResponseHandler.getText(response);
    }
}
