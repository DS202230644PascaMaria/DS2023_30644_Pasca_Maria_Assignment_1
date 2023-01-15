package ds.assign1.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WebsocketController {
    @Autowired
    SimpMessagingTemplate template;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody String message) {
        MessageDTO dto = new MessageDTO();
        dto.setMessage(message);
        System.out.println(dto.getMessage());
        template.convertAndSend("/topic", dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/application")
    public void receiveMessage(@Payload MessageDTO messageDTO) {
        // receive message from client
    }

    @SendTo("/topic")
    public MessageDTO broadcastMessage(@Payload MessageDTO messageDTO) {
        return messageDTO;
    }
}
