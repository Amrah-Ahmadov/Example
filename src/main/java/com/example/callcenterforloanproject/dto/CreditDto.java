package com.example.callcenterforloanproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class CreditDto {
    private Long id;
    private String name;
}
