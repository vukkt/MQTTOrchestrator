package com.mqtt.messagecommander.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * @author vuktopalovic
 * @created 21/05/2025 - 12:48
 */
@Configuration
public class MqttConfig {

    @Value("${mqtt.subscriber.uri}")
    private String mqttSubscriberUri;

    @Value("${mqtt.subscriber.username}")
    private String mqttSubscriberUsername;

    @Value("${mqtt.subscriber.password}")
    private String mqttSubscriberPassword;

    @Value("${mqtt.subscriber.automatic-reconnect}")
    private boolean mqttSubscriberAutomaticReconnect;

    @Value("${mqtt.subscriber.clean-session}")
    private boolean mqttSubscriberCleanSession;

    @Bean
    public MqttPahoClientFactory mqttClientSubFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{mqttSubscriberUri});
        options.setUserName(mqttSubscriberUsername);
        options.setPassword(mqttSubscriberPassword.toCharArray());
        options.setAutomaticReconnect(mqttSubscriberAutomaticReconnect);
        options.setCleanSession(mqttSubscriberCleanSession);
        factory.setConnectionOptions(options);
        return factory;
    }
}
