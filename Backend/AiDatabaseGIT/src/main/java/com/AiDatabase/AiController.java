package com.AiDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/ai/")
public class AiController {
    private final AiService aiService;

    @Autowired
    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    public List<Response> getResponses() {
        return aiService.getResponses();
    }



    @PostMapping("/add")
    public void addResponses(@RequestBody Response response) {
        System.out.println("received");
        aiService.addResponse(response);
    }
}
