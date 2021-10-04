package com.example.callcenterforloanproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class ReclamDto {
    private Long id;
    private String name;
}
