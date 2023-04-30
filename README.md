0. 환경
Spring boot / JPA / ThymeLeaf / MariaDB / Spring Security

1. 화면 구성
   1-1. 회원가입 ( 현민님 )
    1) 이메일 중복 체크
    2) 닉네임 중복 체크
    3) 회원 생성

1-2. 로그인 ( 배찬님 )
1) 로그인 처리
2) 로그인 후 회원정보 가져오기

2. DB 설계 ( 현재 : MariaDB )

CREATE TABLE users
(
user_id int(5) PRIMARY KEY AUTO_INCREMENT COMMENT '회원_PK',
email varchar(100) NOT NUll COMMENT '회원_이메일주소', 	
password varchar(100) NOT NULL COMMENT '패스워드',
nickname varchar(25) NOT NULL COMMENT '회원_닉네임',
created_at date not null comment '회원_생성일자',
updated_at date not null comment '회원_수정일자'
);
