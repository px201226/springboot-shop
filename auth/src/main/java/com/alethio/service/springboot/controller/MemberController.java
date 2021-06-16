package com.alethio.service.springboot.controller;


import com.alethio.service.domain.Member;
import com.alethio.service.springboot.security.JwtTokenProvider;
import com.alethio.service.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/join")
    public String join(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        System.out.println(memberSaveRequestDto);

        memberService.signUp(memberSaveRequestDto);
        return "success";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {

        Member member = memberService.findByUserEmail(memberLoginRequestDto.getEmail());

        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), Collections.singletonList(member.getAuthority().name()));

        return ResponseEntity.ok(jwtToken);
    }


}
