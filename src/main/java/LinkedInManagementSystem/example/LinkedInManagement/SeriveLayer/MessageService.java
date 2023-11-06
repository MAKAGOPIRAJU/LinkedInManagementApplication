package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.DidNotGetAnyMessages;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.RecieverNotFound;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.UserDidNotSendAnyMessagetYet;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.UserIdIsInvalid;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Messages;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.MessageRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;


    public String sendMessage(Messages messages) throws Exception {

        // validations

        Optional<User> optionalUser = userRepository.findById(messages.getSenderId());

        if(optionalUser.isEmpty()) {
            throw new UserIdIsInvalid("please enter the valid sender id");
        }

        Optional<User> userOptional = userRepository.findById(messages.getReceiverId());

        if(userOptional.isEmpty()) {
            throw new RecieverNotFound("please enter the valid receiver id");
        }


        User sender   = optionalUser.get();

        sender.getSendMessages().add(messages);
        messages.setUser(sender);
        messageRepository.save(messages);
        userRepository.save(sender);

        return "message add successfully to the db!";
    }

    public List<String> allMessagesSendByUser(Integer userId) throws Exception{

        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()){
            throw new UserIdIsInvalid("user id is invalid");
        }


        User user = optionalUser.get();

        List<Messages> list = user.getSendMessages();


        if(list.isEmpty()) {

            throw new UserDidNotSendAnyMessagetYet("user was not sent any messages till this point of time");
        }

        List<String > ans = new ArrayList<>();

        for(Messages messages : list) {

            String s =  " ' " + messages.getMessage() + "' send to ";

            String name = userRepository.findById(messages.getReceiverId()).get().getUserName();

            s = s + name;
            ans.add(s);
        }

        return  ans;

    }
    public List<String> allRecievedMesssages(Integer userId) throws Exception{

        List<Messages> allMessages = messageRepository.findAll();

        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()){
            throw new UserIdIsInvalid("user id is invalid");
        }

        User user = optionalUser.get();

        if(allMessages.isEmpty()) {
            throw new DidNotGetAnyMessages("did not received any messages till today");
        }

        List<String> answer = new ArrayList<>();

        for(Messages message : allMessages) {

            int receiverId = message.getReceiverId();

            if(receiverId == userId) {

                // message received from senderId

                String recieveMessage = "'"+message.getMessage() + "'  received from ";
                String name = userRepository.findById(message.getSenderId()).get().getUserName();

                recieveMessage = recieveMessage + name;

                answer.add(recieveMessage);

            }
        }

        if(answer.isEmpty()) {

            throw new DidNotGetAnyMessages("did not received any messages till today");
        }

        return answer;
    }

    public List<Messages> allMessages() throws Exception{

        List<Messages> messagesList = messageRepository.findAll();

        if(messagesList.isEmpty()) {

            throw new DidNotGetAnyMessages("did not received any messages till today");
        }
        return messagesList;
    }
}
