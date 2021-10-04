package com.example.callcenterforloanproject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", allocationSize = 1, sequenceName = "USER_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    private Long id;
    private String name;
    private String surname;
    private String address;
    private BigDecimal amount;
    private String note;
    @ManyToOne
    @JoinColumn(name = "creditId")
    private Credit credit;
    @ManyToOne
    @JoinColumn(name = "reclamId")
    private Reclam reclam;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private LocalDateTime creationTime;

}
