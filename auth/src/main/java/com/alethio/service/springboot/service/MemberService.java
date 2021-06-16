package com.alethio.service.springboot.service;

import com.alethio.service.domain.Member;
import com.alethio.service.springboot.controller.MemberSaveRequestDto;
import com.alethio.service.springboot.controller.MemberSaveResponseDto;
import com.alethio.service.springboot.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberJpaRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getAuthority().toString()));

        return new User(
                member.getEmail(),
                member.getPassword(),
                authorities
        );
    }

    @Transactional
    public MemberSaveResponseDto signUp(MemberSaveRequestDto memberSaveRequestDto) {

        if (memberRepository.existsByEmail(memberSaveRequestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }


        Member member = memberSaveRequestDto.toEntity();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);

        return MemberSaveResponseDto.of(member);
    }

    @Transactional
    public Member findByUserEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));

    }
}