package com.sofka.lab.common.messages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {


    private final ObjectMapper objectMapper;
    private Map<String, ErrorMessageDto> messages;

    public ErrorMessageServiceImpl(ObjectMapper objectMapper) {
        System.out.println("ErrorMessageServiceImpl constructor");
        Resource resource = new ClassPathResource("error_messages.json");
        this.objectMapper = objectMapper;
        readValueJson(resource);
    }


    private void readValueJson(Resource resource) {
        try {
            this.messages = objectMapper
                    .readValue(resource.getInputStream(),
                            new TypeReference<Map<String, ErrorMessageDto>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ErrorMessageDto getMessage(String code) {
        return messages.get(code);
    }

    @Override
    public Map<String, ErrorMessageDto> getMessages() {
        return messages;
    }
}
