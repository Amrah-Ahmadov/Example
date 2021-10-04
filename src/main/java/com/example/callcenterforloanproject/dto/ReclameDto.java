package com.example.callcenterforloanproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class ReclameDto {
    private Long id;
    private String name;
}
