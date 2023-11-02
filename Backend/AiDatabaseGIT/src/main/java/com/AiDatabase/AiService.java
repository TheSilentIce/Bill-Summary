package com.AiDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {
    private final AiRepository aiRepository;

    @Autowired
    public AiService(AiRepository aiRepository) {
        this.aiRepository = aiRepository;
    }

    public List<Response> getResponses() {
        return aiRepository.findAll();
    }

    public void addResponse(Response response) {
        aiRepository.save(response);
    }
}
