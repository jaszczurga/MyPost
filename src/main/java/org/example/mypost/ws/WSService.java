package org.example.mypost.ws;

import lombok.AllArgsConstructor;
import org.example.mypost.services.Auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;
    private final AuthenticationService authenticationService;






    public void notifyUser(String id, final String message) {
        ResponseMessage response = new ResponseMessage(message,String.valueOf(authenticationService.getLoggedInUserId()));

       // notificationService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages",response);
    }
}