package com.sofka.lab.customers.app.extras;

import java.util.Map;

public interface MessageService {

    MessageDto getMessage(String code);

    Map<String, MessageDto> getMessages() ;


}
