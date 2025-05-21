package com.mqtt.messagecommander.controller;

/**
 * @author vuktopalovic
 * @created 21/05/2025 - 12:57
 */

import com.mqtt.messagecommander.api.MqttApi;
import com.mqtt.messagecommander.dto.CommandDTO;
import com.mqtt.messagecommander.service.MqttConnectorService;
import com.mqtt.messagecommander.util.CommandConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/message")
public class MessageController implements MqttApi {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    private MqttConnectorService mqttConnector;

//    @Override
//    @PostMapping(path = "subscribe")
//    public String subscribe(@RequestBody MqttOptionsDTO mqttOptionsDTO) {
//        mqttConnector.subscribe(mqttOptionsDTO.getTopic());
//        return mqttOptionsDTO.getTopic();
//    }

//    @Override
//    @PostMapping(path = "publish")
//    public void publish(@RequestBody MqttOptionsDTO mqttOptionsDTO) throws NoSuchMethodException {
//        mqttConnector.publish(mqttOptionsDTO.getTopic(), mqttOptionsDTO.getMessage());
//    }


    @Override
    @PostMapping(path = "create-user")
    public <T> void createUser(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.CREATE_CLIENT.value, commandDTO);
        log.info("User creation command sent successfully");
    }

    @Override
    @PostMapping("create-role")
    public <T> void createRole(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.CREATE_ROLE.value, commandDTO);
        log.info("Role creation command sent successfully");
    }

    @Override
    @PostMapping("delete-user")
    public <T> void deleteUser(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.DELETE_CLIENT.value, commandDTO);
        log.info("User deleted successfully");
    }

    @Override
    @PostMapping("delete-role")
    public <T> void deleteRole(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.DELETE_ROLE.value, commandDTO);
        log.info("Role deleted successfully");
    }

    @Override
    @PostMapping("add-client-role")
    public <T> void addClientRole(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_CLIENT_ROLE.value, commandDTO);
        log.info("Role added to user successfully");
    }

    @Override
    @PostMapping("remove-client-role")
    public <T> void removeClientRole(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.REMOVE_CLIENT_ROLE.value, commandDTO);
        log.info("Role added to user successfully");
    }

    @Override
    @PostMapping("client-acl-receive")
    public <T> void addPublishClientReceiveToRoleAcl(@RequestBody T dto) {
        if (dto instanceof java.util.Map<?, ?> dtoMap) {
            ((java.util.Map<String, Object>) dtoMap).put("allow", true);
        }
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.PUBLISH_CLIENT_RECEIVE.value, commandDTO);
        log.info("Client acl receive to user successfully");
    }


    @Override
    @PostMapping("client-acl-send")
    public <T> void addPublishClientSendToRoleAcl(@RequestBody T dto) {
        if (dto instanceof java.util.Map<?, ?> dtoMap) {
            ((java.util.Map<String, Object>) dtoMap).put("allow", true);
        }
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_ROLE_ACL.value, commandDTO);
        log.info("Client acl send added to role successfully");
    }


    @Override
    @PostMapping("client-acl-subscribe")
    public <T> void addSubscribeLiteralToRoleAcl(@RequestBody T dto) {
        if (dto instanceof java.util.Map<?, ?> dtoMap) {
            ((java.util.Map<String, Object>) dtoMap).put("allow", true);
        }
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_ROLE_ACL.value, commandDTO);
        log.info("Client acl subscribe to user successfully");
    }


    @Override
    @PostMapping("client-acl-subscribe-pattern")
    public <T> void addSubscribePatternToRoleAcl(@RequestBody T dto) {
        if (dto instanceof java.util.Map<?, ?> dtoMap) {
            ((java.util.Map<String, Object>) dtoMap).put("allow", true);
        }
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_ROLE_ACL.value, commandDTO);
        log.info("Client acl subscribe-pattern to user successfully");
    }


    @Override
    @PostMapping("client-acl-unsubscribe")
    public <T> void unsubscribeLiteralToRoleAcl(@RequestBody T dto) {
        if (dto instanceof java.util.Map<?, ?> dtoMap) {
            ((java.util.Map<String, Object>) dtoMap).put("allow", true);
        }
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_ROLE_ACL.value, commandDTO);
        log.info("Client acl unsubscribe literal added to role successfully");
    }


    @Override
    @PostMapping("client-acl-unsubscribe-pattern")
    public <T> void unsubscribePatternToRoleAcl(@RequestBody T dto) {
        if (dto instanceof java.util.Map<?, ?> dtoMap) {
            ((java.util.Map<String, Object>) dtoMap).put("allow", true);
        }
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_ROLE_ACL.value, commandDTO);
        log.info("Client acl unsubscribe-pattern added to role successfully");
    }


    @Override
    @PostMapping("create-group")
    public <T> void createGroup(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.CREATE_GROUP.value, commandDTO);
        log.info("Group created successfully");
    }

    @Override
    @PostMapping("add-client-to-group")
    public <T> void addClientToGroup(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_GROUP_CLIENT.value, commandDTO);
        log.info("Client added to group successfully");
    }

    @Override
    @PostMapping("remove-client-to-group")
    public <T> void removeClientFromGroup(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.REMOVE_GROUP_CLIENT.value, commandDTO);
        log.info("Client removed from group successfully");
    }

    @Override
    @PostMapping("add-role-to-group")
    public <T> void addRoleToGroup(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.ADD_GROUP_ROLE.value, commandDTO);
        log.info("Role added to group successfully");
    }

    @Override
    @PostMapping("remove-role-from-group")
    public <T> void RemoveRoleFromGroup(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.REMOVE_GROUP_ROLE.value, commandDTO);
        log.info("Role removed from group successfully");
    }

    @Override
    @PostMapping("delete-group")
    public <T> void deleteGroup(@RequestBody T dto) {
        CommandDTO<T> commandDTO = new CommandDTO<>();
        commandDTO.setCommands(Collections.singletonList(dto));
        mqttConnector.publishJSON(mqttConnector.getMqttTopic(), CommandConstants.DELETE_GROUP.value, commandDTO);
        log.info("Group deleted successfully");
    }
    }
