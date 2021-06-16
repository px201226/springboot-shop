package com.alethio.service.springboot.controller;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberLoginRequestDto {

    private String email;
    private String password;

}
