package ds.assign1.chat;

import com.thetransactioncompany.jsonrpc2.*;
import com.thetransactioncompany.jsonrpc2.server.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final SimpMessagingTemplate TEMPLATE;

    private final Dispatcher dispatcher;

    private final MessageHandler HANDLER;

    @Autowired
    public ChatService(SimpMessagingTemplate TEMP){
        this.TEMPLATE = TEMP;
        this.dispatcher = new Dispatcher();
        this.HANDLER = new MessageHandler(TEMPLATE);
        dispatcher.register(HANDLER);
    }

    public JSONRPC2Response sendMessage(String message){
        JSONRPC2Request request;
        JSONRPC2Parser parser = new JSONRPC2Parser();

        try {
            request = parser.parseJSONRPC2Request(message);
            return dispatcher.process(request, null);
        } catch (JSONRPC2ParseException e) {
            return new JSONRPC2Response(JSONRPC2Error.PARSE_ERROR, 0);
        }
    }

}
