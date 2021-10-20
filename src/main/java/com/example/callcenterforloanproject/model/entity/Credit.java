package com.example.callcenterforloanproject.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credit {
    @Id
    @SequenceGenerator(name = "CREDIT_ID_GENERATOR", allocationSize = 1, sequenceName = "CREDIT_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_ID_GENERATOR")
    private Long id;
    @NotEmpty(message = "The name of the credit has to be provided")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "credit")
    private List<Loan> loans;

    public Credit(Long id) {
        this.id = id;
    }
}
