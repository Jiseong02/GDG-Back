package com.gdg.gdgback.Service.Implement;


import com.gdg.gdgback.Domain.Prompt;
import com.gdg.gdgback.Service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("!test")
public class CounselingServiceImpl implements CounselingService {
    @Autowired
    GoogleAgentService agentService;

    @Override
    public String respondByText(Prompt prompt) throws IOException {
        return agentService.getTextReply(prompt);
    }

    @Override
    public byte[] respondByVoice(Prompt prompt) throws IOException{
        return agentService.getVoiceReply(prompt);
    }
}
