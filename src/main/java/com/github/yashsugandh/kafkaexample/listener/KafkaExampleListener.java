package com.github.yashsugandh.kafkaexample.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaExampleListener {

  @KafkaListener(topics = "dummy", groupId = "1")
  public void listen(String message) {
    log.debug("Received Message in group 1: {}", message);
  }

  @KafkaListener(topics = "customer.create", groupId = "1")
  public void listenFlinkMessage(String message) {
    log.debug("Received Message in group 1: {}", message);
  }
}
