<<<<<<< HEAD
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
    private Long userId;
    private Date createAt;

    private String password;
}
=======
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
    private Long userId;
    private Date createAt;

    private String password;
}
>>>>>>> 4560b6739a7fb47367bfc065c05d3048faf15b92
