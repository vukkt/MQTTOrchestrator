package com.mqtt.messagecommander.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author vuktopalovic
 * @created 21/05/2025 - 12:45
 */
public interface MqttApi {

    @PostMapping(path = "create-user")
    <T> void createUser(@RequestBody T dto);

    @PostMapping(path = "create-role")
    <T> void createRole(@RequestBody T dto);

    @PostMapping("delete-user")
    <T> void deleteUser(@RequestBody T dto);

    @PostMapping("delete-role")
    <T> void deleteRole(@RequestBody T dto);

    @PostMapping("delete-role")
    <T> void addClientRole(@RequestBody T dto);

    @PostMapping("add-client-role")
    <T> void removeClientRole(@RequestBody T dto);

    @PostMapping("client-acl-receive")
    <T> void addPublishClientReceiveToRoleAcl(@RequestBody T dto);

    @PostMapping("client-acl-send")
    <T> void addPublishClientSendToRoleAcl(@RequestBody T dto);

    @PostMapping("client-acl-subscribe")
    <T> void addSubscribeLiteralToRoleAcl(@RequestBody T dto);

    @PostMapping("client-acl-subscribe-pattern")
    <T> void addSubscribePatternToRoleAcl(@RequestBody T dto);

    @PostMapping("client-acl-unsubscribe")
    <T> void unsubscribeLiteralToRoleAcl(@RequestBody T dto);

    @PostMapping("client-acl-unsubscribe-pattern")
    <T> void unsubscribePatternToRoleAcl(@RequestBody T dto);

    @PostMapping("create-group")
    <T> void createGroup(@RequestBody T dto);

    @PostMapping("add-client-to-group")
    <T> void addClientToGroup(@RequestBody T dto);

    @PostMapping("remove-client-to-group")
    <T> void removeClientFromGroup(@RequestBody T dto);

    @PostMapping("add-role-to-group")
    <T> void addRoleToGroup(@RequestBody T dto);

    @PostMapping("remove-role-from-group")
    <T> void RemoveRoleFromGroup(@RequestBody T dto);

    @PostMapping("delete-group")
    <T> void deleteGroup(@RequestBody T dto);
}
