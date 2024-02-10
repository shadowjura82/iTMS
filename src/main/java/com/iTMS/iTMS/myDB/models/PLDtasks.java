package com.iTMS.iTMS.myDB.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pld_tasks")
public class PLDtasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "task")
    private String task;
    @Column(name = "support_proc")
    private boolean ifTaskIsProcessedBySupport;
    @Column(name = "pld_proc")
    private boolean ifTaskIsProcessedByPLD;
    @Column(name = "status")
    private String status;

    public PLDtasks(String task, boolean ifTaskIsProcessedBySupport, boolean ifTaskIsProcessedByPLD, String status) {
        this.task = task;
        this.ifTaskIsProcessedBySupport = ifTaskIsProcessedBySupport;
        this.ifTaskIsProcessedByPLD = ifTaskIsProcessedByPLD;
        this.status = status;
    }

    public PLDtasks() {
    }

    public String taskToString() {
        return "\n" + task;
    }
}