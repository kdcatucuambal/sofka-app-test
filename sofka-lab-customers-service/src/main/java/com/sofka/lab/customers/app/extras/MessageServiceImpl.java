package com.sofka.lab.customers.app.extras;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Map;

public class MessageServiceImpl implements MessageService {

    private final ObjectMapper objectMapper;
    private Map<String, MessageDto> messages;

    public MessageServiceImpl(ObjectMapper objectMapper){
        Resource resource = new ClassPathResource("bl_messages.json");
        this.objectMapper = objectMapper;
        readValueJson(resource);
    }


    private void readValueJson(Resource resource){
        try {
            this.messages = objectMapper.readValue(resource.getInputStream(), new TypeReference<Map<String, MessageDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MessageDto getMessage(String code){
        return messages.get(code);
    }
    @Override
    public Map<String, MessageDto> getMessages() {
        return messages;
    }


}
