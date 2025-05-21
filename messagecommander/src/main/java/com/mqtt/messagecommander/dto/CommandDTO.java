package com.mqtt.messagecommander.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vuktopalovic
 * @created 21/05/2025 - 12:58
 */
@AllArgsConstructor
@NoArgsConstructor
public class CommandDTO<T> {

    private List<Object> commands;

    public List<Object> getCommands() {
        return commands;
    }

    public void setCommands(List<Object> commands) {
        this.commands = commands;
    }
}
