package com.sofka.lab.common.messages;

import java.util.Map;

public interface ErrorMessageService {

    ErrorMessageDto getMessage(String code);
    Map<String, ErrorMessageDto> getMessages() ;


}
