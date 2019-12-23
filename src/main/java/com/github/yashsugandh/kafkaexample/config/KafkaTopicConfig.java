package com.github.yashsugandh.kafkaexample.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

  @Value(value = "${bootstrap.servers}")
  private String bootstrapAddress;

  /**
   * KafkaAdmin bean automatically add topics to the broker.
   *
   * @return
   */
  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  /**
   * Beans for every topic needs to be created.
   *
   * @return
   */
  @Bean
  public NewTopic topic1() {
    return new NewTopic("dummy", 3, (short) 1);
  }

  @Bean
  public NewTopic flinkTopic() {
    return new NewTopic("customer.create", 3, (short) 1);
  }
}
