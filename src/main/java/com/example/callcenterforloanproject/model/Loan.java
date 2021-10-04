package com.example.callcenterforloanproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @SequenceGenerator(name = "LOAN_ID_GENERATOR", allocationSize = 1, sequenceName = "LOAN_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOAN_ID_GENERATOR")
    private Long id;
    private String name;
    private String surname;
    private String address;
    private BigDecimal amount;
    private String note;
    @ManyToOne
    @JoinColumn(name = "creditId", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Credit credit;
    @ManyToOne
    @JoinColumn(name = "reclamId", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Reclame reclame;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
    private LocalDateTime creationTime;

}
