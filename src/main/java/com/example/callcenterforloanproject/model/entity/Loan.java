package com.example.callcenterforloanproject.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Loan {
    @Id
    @SequenceGenerator(name = "LOAN_ID_GENERATOR", allocationSize = 1, sequenceName = "LOAN_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOAN_ID_GENERATOR")
    private Long id;
    @NotEmpty(message = "Name has to be provided")
    private String name;
    @NotEmpty(message = "Surname has to be provided")
    private String surname;
    @NotEmpty(message = "Address has to be provided")
    private String address;
    @NotNull(message = "Please provide a how much do you want to borrow")
    @DecimalMin("3000.00")
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
