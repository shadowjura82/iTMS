package com.iTMS.iTMS.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "problem")
@Data
public class OracleTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AA_ID")
    private Long id;
    @Column(name = "CLIENT")
    private String client;
    @Column(name = "ID")
    private String clientId;
}
