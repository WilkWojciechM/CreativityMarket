package com.wilkwm.pracainz.domain.message;


import com.wilkwm.pracainz.domain.message.dto.MessageDto;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import com.wilkwm.pracainz.domain.user.UserService;
import com.wilkwm.pracainz.domain.user.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final UserRepository userRepository;

    public MessageController(MessageService messageService, UserService userService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/send-message")
    public String sendMessage(Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String userName = user.getName();

        Message message = new Message();
        message.setSender(user);
        model.addAttribute("message", message);
        model.addAttribute("allUsers", userRepository.findAll());

        return "send-message";
    }
    @PostMapping("/send-message")
    public String sendMessage(@ModelAttribute MessageDto message) {
        messageService.sendMessage(message.getSender(), message.getReceiver(), message.getContent());
        return "redirect:/send-message";
    }

//    @GetMapping("/messages")
//    public String getMessages(Model model, Authentication authentication, @ModelAttribute("message") MessageDto messageDto) {
//        String userEmail = authentication.getName();
//        String receiver = "creator";
//        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("User not found"));
//        String userName = user.getName();
//        List<UserDto> allUsers = userService.findAllRegisteredUsers();
//
//        List<MessageDto> messages = messageService.getMessages(userName, receiver);
//        model.addAttribute("receiver", receiver);
//        model.addAttribute("allUsers", allUsers);
//        model.addAttribute("messages", messages);
//        model.addAttribute("sender", userName);
//        return "messages";
//    }
//    @PostMapping("/messages")
//    public String getMessages(@ModelAttribute MessageDto message) {
//        messageService.sendMessage(message.getSender(), message.getReceiver(), message.getContent());
//        return "redirect:/messages";
//    }

    @GetMapping("/messages")
    public String getMessages(Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String userName = user.getName();

        List<MessageDto> messages = messageService.getAllMessagesForUser(userName);
        model.addAttribute("messages", messages);

        return "messages";
    }
}

