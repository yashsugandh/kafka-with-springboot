package com.github.yashsugandh.kafkaexample.controller;

import com.github.yashsugandh.kafkaexample.publish.KafkaPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaExampleController {

  private final KafkaPublisher kafkaPublisher;

  public KafkaExampleController(KafkaPublisher kafkaPublisher) {
    this.kafkaPublisher = kafkaPublisher;
  }

  @PostMapping("/send")
  public void send(@RequestBody String data) {
    kafkaPublisher.sendMessage(data);
  }
}
