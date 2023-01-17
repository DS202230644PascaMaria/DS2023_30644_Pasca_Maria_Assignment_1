package ds.assign1.chat;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService SERVICE;

    @PostMapping("/send")
    public JSONRPC2Response sendMessage(@RequestBody String msg){
        return SERVICE.sendMessage(msg);
    }
}
