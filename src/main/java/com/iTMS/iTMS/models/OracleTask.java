package com.iTMS.iTMS.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "problem")
@Data
public class OracleTask {
    @Id
    @Column(name = "AA_ID")
    private Long id;
    @Column(name = "CLIENT")
    private String client;
    @Column(name = "ID")
    private String clientId;
}
