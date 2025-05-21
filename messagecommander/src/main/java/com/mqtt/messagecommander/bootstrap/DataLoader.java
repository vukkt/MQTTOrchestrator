package com.mqtt.messagecommander.bootstrap;

import com.mqtt.messagecommander.service.MqttConnectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author vuktopalovic
 * @created 21/05/2025 - 12:48
 */
@Component
public class DataLoader {

    private final MqttConnectorService mqttConnector;

    public DataLoader(MqttConnectorService mqttConnector) {
        this.mqttConnector = mqttConnector;
    }

    @EventListener
    public void onContextRefreshed(ContextRefreshedEvent event) {
        mqttConnector.start();
    }
}
