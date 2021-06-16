package com.alethio.service.springboot.controller;

import com.alethio.service.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveResponseDto {

    private String email;
    private String mobileNumber;
    private Long point;
    private String address;

    public static MemberSaveResponseDto of(Member member){
        return MemberSaveResponseDto.builder()
                .email(member.getEmail())
                .mobileNumber(member.getMobileNumber())
                .point(member.getPoint())
                .address(member.getAddress())
                .build();
    }
}

