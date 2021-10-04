package com.example.callcenterforloanproject.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    private final ModelMapper modelMapper;

    public ConverterService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
