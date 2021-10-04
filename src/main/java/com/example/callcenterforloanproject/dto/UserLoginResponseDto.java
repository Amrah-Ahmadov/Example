package com.example.callcenterforloanproject.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserLoginResponseDto {
    private String username;
    String jwt;
}
