<<<<<<< HEAD
package com.example.user.phm.entity;

import com.example.user.phm.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicInsert
@Table(name = "USERS")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(nullable = false, length = 100, unique = true)
    private String email;// 이메일

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, length = 100)
    private String nickname;
}
=======
package com.example.user.phm.entity;

import com.example.user.phm.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicInsert
@Table(name = "USERS")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(nullable = false, length = 100, unique = true)
    private String email;// 이메일

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, length = 100)
    private String nickname;
}
>>>>>>> 4560b6739a7fb47367bfc065c05d3048faf15b92
