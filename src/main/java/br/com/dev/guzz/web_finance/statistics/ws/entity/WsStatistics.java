package br.com.dev.guzz.web_finance.statistics.ws.entity;

import br.com.dev.guzz.web_finance.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode

@Entity
@Table(name = "web_services_statistics")
public class WsStatistics {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String wsCalled;

    @ManyToOne
    private User user;
}
