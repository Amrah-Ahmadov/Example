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
@ToString
public class Reclame {
    @Id
    @SequenceGenerator(name = "RECLAM_ID_GENERATOR", allocationSize = 1, sequenceName = "RECLAM_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECLAM_ID_GENERATOR")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclame")
    private List<Loan> loans;

    public Reclame(Long id) {
        this.id = id;
    }
}
