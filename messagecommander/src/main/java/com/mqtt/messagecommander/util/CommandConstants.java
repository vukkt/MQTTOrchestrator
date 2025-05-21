package com.mqtt.messagecommander.util;

public enum CommandConstants {
    CREATE_CLIENT("createClient"),
    DELETE_CLIENT("deleteClient"),
    CREATE_ROLE("createRole"),
    DELETE_ROLE("deleteRole"),
    ADD_CLIENT_ROLE("addClientRole"),
    REMOVE_CLIENT_ROLE("removeClientRole"),

    PUBLISH_CLIENT_RECEIVE("addRoleACL"),
    ADD_ROLE_ACL("addRoleACL"),
    CREATE_GROUP("createGroup"),
    DELETE_GROUP("deleteGroup"),
    ADD_GROUP_CLIENT("addGroupClient"),
    REMOVE_GROUP_CLIENT("removeGroupClient"),
    ADD_GROUP_ROLE("addGroupRole"),
    REMOVE_GROUP_ROLE("removeGroupRole");
    public final String value;

    CommandConstants(String value) {
        this.value = value;
    }
}
