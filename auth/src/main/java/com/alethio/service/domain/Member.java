package com.alethio.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private String mobileNumber;

    private Long point;

    private String address;

    @Builder
    public Member(String email, String password, Authority authority, String mobileNumber, Long point, String address) {
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.mobileNumber = mobileNumber;
        this.point = point;
        this.address = address;
    }

    public void setPassword(String password){
        this.password = password;
    }
}