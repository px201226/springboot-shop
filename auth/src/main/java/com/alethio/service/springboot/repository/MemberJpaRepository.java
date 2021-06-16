package com.alethio.service.springboot.repository;

import com.alethio.service.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member,Long> {

    public Optional<Member> findByEmail(String email);
    public boolean existsByEmail(String email);
}
