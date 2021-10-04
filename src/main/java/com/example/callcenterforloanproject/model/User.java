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
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", allocationSize = 1, sequenceName = "USER_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    private Long id;
    @Column(nullable = false, unique = true)
    private Long innerNumber;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
    private List<Loan> loans;
}
