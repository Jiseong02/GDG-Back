package com.gdg.gdgback.Agent.Service;

import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import jakarta.servlet.http.HttpSession;

public interface AgentService {
    String replyByText(HttpSession session, AgentTextRequestDto agentTextRequestDto);
    String summarize(String prompt);
    byte[] replyByAudio(HttpSession session, AgentTextRequestDto agentTextRequestDto);
    byte[] replyByAudio(HttpSession session, AgentAudioRequestDto agentAudioRequestDto);
}
