package com.example.callcenterforloanproject.dto;

import lombok.*;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserDto {
    private Long id;
    private Long innerNumber;
    private String name;
    private String surname;
    private String username;
}
