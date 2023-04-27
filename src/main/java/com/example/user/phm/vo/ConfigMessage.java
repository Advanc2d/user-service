package com.example.user.phm.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigMessage {
    @Value("${welcome.message}")
    private String message;
}
