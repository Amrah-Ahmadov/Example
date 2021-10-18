package com.example.callcenterforloanproject.model;

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
@Table(name = "users")
@ToString
public class User {
    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", allocationSize = 1, sequenceName = "USER_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    private Long id;
    @Column(nullable = false, unique = true)
    private Long innerNumber;
    @NotEmpty(message = "Name has to be provided")
    private String name;
    @NotEmpty(message = "Surname has to be provided")
    private String surname;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Username has to be provided")
    private String username;
    @NotEmpty(message = "Password has to be provided")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Loan> loans;
}
