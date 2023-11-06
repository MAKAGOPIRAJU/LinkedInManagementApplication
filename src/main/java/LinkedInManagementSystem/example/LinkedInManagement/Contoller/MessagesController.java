package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Messages;
import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessagesController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody Messages messages) throws Exception{

        try {
            return messageService.sendMessage(messages);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    // find all send messages by the user
    @GetMapping("/find-all-sendMessages")
    public ResponseEntity allMessagesSendByUser(@RequestParam("userId")  Integer userId) throws Exception
    {

        try {
            List<String> allMessages = messageService.allMessagesSendByUser(userId);
            return new ResponseEntity(allMessages , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // find all received messages to the user
    @GetMapping("/find-all-receivedMessages")
    public ResponseEntity allRecievedMesssages(@RequestParam("userId") Integer userId) throws  Exception{

      try{
          List<String> receivedMessages = messageService.allRecievedMesssages(userId);
          return new ResponseEntity(receivedMessages , HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("/findAllMessages")
    public ResponseEntity allMessages() throws Exception{
        try {
            List<Messages> messagesList =messageService.allMessages();
            return new ResponseEntity(messagesList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
