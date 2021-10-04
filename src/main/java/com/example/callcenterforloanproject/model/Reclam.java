package com.example.callcenterforloanproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclam {
    @Id
    @SequenceGenerator(name = "RECLAM_ID_GENERATOR", allocationSize = 1, sequenceName = "RECLAM_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECLAM_ID_GENERATOR")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Reclam")
    private List<Loan> loans;
}
