package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.Prompt;

import java.io.IOException;

public interface CounselingService {
    String respondByText(Prompt prompt) throws IOException;
    byte[] respondByVoice(Prompt prompt) throws IOException;
}
