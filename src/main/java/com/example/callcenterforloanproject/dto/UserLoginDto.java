package com.example.callcenterforloanproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserLoginDto {
    private String username;
    private String password;
}
