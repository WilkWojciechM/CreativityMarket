package com.wilkwm.pracainz.domain.message;

import com.wilkwm.pracainz.domain.message.dto.MessageDto;
import com.wilkwm.pracainz.domain.user.User;

import java.util.List;

public class MessageDtoMapper {
    public static MessageDto map(Message message) {
        return new MessageDto(
                message.getId(),
                message.getSender().getName(),
                message.getReceiver().getName(),
                message.getContent(),
                message.getCreatedAt(),
                message.isRead()
        );
    }
}


