package com.iTMS.iTMS.dto;

import lombok.Data;

@Data
public class TaskId {
    private String client;
    private String clientId;

    public TaskId(String client, String clientId) {
        this.client = client;
        this.clientId = clientId;
    }
}
