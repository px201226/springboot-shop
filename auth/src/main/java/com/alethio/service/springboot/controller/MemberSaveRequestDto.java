package com.alethio.service.springboot.controller;

import com.alethio.service.domain.Authority;
import com.alethio.service.domain.Member;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberSaveRequestDto {

    private String email;
    private String password;
    private String mobileNumber;
    private Long point;
    private String address;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .address(address)
                .mobileNumber(mobileNumber)
                .point(point)
                .authority(Authority.ROLE_USER)
                .build();
    }
}

