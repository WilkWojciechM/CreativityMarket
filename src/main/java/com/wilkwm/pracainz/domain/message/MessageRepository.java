package com.wilkwm.pracainz.domain.message;


import com.wilkwm.pracainz.domain.user.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

  List<Message> findAllBySenderAndReceiverOrderByCreatedAtAsc(User sender, User receiver);
  List<Message> findAllBySenderOrReceiver(User sender, User receiver);
}
