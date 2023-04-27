package com.example.user.phm.dto;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String email;
    private String nickname;
    private String id;
    private Date createAt;

    private String password;
}
