package com.wilkwm.pracainz.domain.message;

import com.wilkwm.pracainz.domain.message.dto.MessageDto;
import com.wilkwm.pracainz.domain.user.User;
import com.wilkwm.pracainz.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void sendMessage(String senderUsername, String receiverUsername, String messageContent) {
        User sender = userRepository.findByName(senderUsername).orElseThrow();
        User receiver = userRepository.findByName(receiverUsername).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageContent);
        message.setCreatedAt(LocalDateTime.now());
        message.setRead(false);

        messageRepository.save(message);
    }

    public List<MessageDto> getMessages(String sender, String receiver) {
        User senderUser = userRepository.findByName(sender).orElseThrow(() -> new IllegalArgumentException("Sender user not found"));
        User receiverUser = userRepository.findByName(receiver).orElseThrow(() -> new IllegalArgumentException("Receiver user not found"));
        return messageRepository.findAllBySenderAndReceiverOrderByCreatedAtAsc(senderUser, receiverUser)
                .stream()
                .map(MessageDtoMapper::map)
                .toList();
    }

    public List<MessageDto> getAllMessagesForUser(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return messageRepository.findAllBySenderOrReceiver(user , user)
                .stream()
                .map(MessageDtoMapper::map)
                .toList();
    }
}


