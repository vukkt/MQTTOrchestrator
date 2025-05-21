package com.mqtt.messagecommander.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqtt.messagecommander.controller.MessageController;
import com.mqtt.messagecommander.dto.CommandDTO;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author vuktopalovic
 * @created 21/05/2025 - 12:50
 */
@Service
public class MqttConnectorService {
    private static final int KEEP_ALIVE_INTERVAL = 60;
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
    @Value("${mqtt.subscriber.client-id}")
    private String mqttSubscriberClientId;

    @Value("${mqtt.topic}")

    private String mqttTopic;
    private MqttClient client;

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    public void start() {

        try {
            client = new MqttClient(mqttSubscriberUri, mqttSubscriberClientId, new MemoryPersistence());

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(mqttSubscriberCleanSession);
            connOpts.setUserName(mqttSubscriberUsername);
            connOpts.setPassword(mqttSubscriberPassword.toCharArray());
            connOpts.setKeepAliveInterval(KEEP_ALIVE_INTERVAL);
            connOpts.setAutomaticReconnect(mqttSubscriberAutomaticReconnect);

            log.info("Connecting to broker: " + mqttSubscriberUri);
            client.connect(connOpts);
            log.info("Connected");

            // Set up callback for incoming messages
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    log.info("Mqtt Connection lost, trying to reconnect");
                    try {
                        client.connect(connOpts);
                    } catch (MqttException e) {
                        log.error("Error: " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    log.info("Message received. Topic: " + topic + ", Message: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used in this example
                }
            });

            while (client.isConnected()) {
                Thread.sleep(1000); // Adjust the delay as needed
            }

        } catch (MqttException | InterruptedException e) {
            log.error("Error: " + e.getMessage());
            try {
                Thread.sleep(5000); // Wait for 5 seconds before retrying
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void publish(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(1); // Adjust the QoS level as needed
        try {
            client.publish(topic, mqttMessage);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }

    public void publishJSON(String mqttTopic, String command, CommandDTO<?> commandDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Add the 'command' field to each command object
            List<Map<String, Object>> commandsWithCommand = new ArrayList<>();
            for (Object cmd : commandDTO.getCommands()) {
                Map<String, Object> cmdMap = objectMapper.convertValue(cmd, Map.class);
                cmdMap.put("command", command);
                commandsWithCommand.add(cmdMap);
            }

            // Convert the modified list to JSON string
            String commandsJson = objectMapper.writeValueAsString(commandsWithCommand);

            // Construct the final JSON string
            String jsonString = "{\"commands\":" + commandsJson + "}";

            // Publish the JSON string
            MqttMessage mqttMessage = new MqttMessage(jsonString.getBytes());
            mqttMessage.setQos(1); // Adjust the QoS level as needed
            client.publish(mqttTopic, mqttMessage);
        } catch (MqttException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }

    public String getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }
}



