package myDB.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "iTMS")
@Data
public class PostgreSQLTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CLIENT")
    private String client;
    @Column(name = "ID")
    private String clientId;

    public PostgreSQLTask(String client, String clientId) {
        this.client = client;
        this.clientId = clientId;
    }
}
