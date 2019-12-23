package com.github.yashsugandh.kafkaexample.publish;

import com.github.yashsugandh.kafkaexample.config.KafkaTopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaPublisher {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private KafkaTopicConfig kafkaTopicConfig;

  /**
   * The sendMessage is used to publish the message to kafka.
   *
   * @param message
   */
  public void sendMessage(String message) {

    ListenableFuture<SendResult<String, String>> future = kafkaTemplate
        .send(kafkaTopicConfig.topic1().name(), message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.debug(" message sent = {} with offset= {}", message,
            result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to send a message={} due to : {}", message, ex.getMessage());
      }
    });
  }
}
