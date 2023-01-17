package ds.assign1.chat;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

public class MessageHandler implements RequestHandler {
    private final SimpMessagingTemplate TEMPLATE;

    public MessageHandler(SimpMessagingTemplate TEMP){
        this.TEMPLATE = TEMP;
    }

    @Override
    public String[] handledRequests() {
        return new String[]{"sendMessage"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request jsonrpc2Request, MessageContext messageContext) {
        List parameters = (List) jsonrpc2Request.getParams();

        if(jsonrpc2Request.getMethod().equals("sendMessage")){
            Object to = parameters.get(0);
            Object from = parameters.get(1);
            Object msg = parameters.get(2);
            TEMPLATE.convertAndSend("/chat/" + to + "/" + from + "/", msg);
            return new JSONRPC2Response(jsonrpc2Request.getID());
        }

        return null;
    }
}
