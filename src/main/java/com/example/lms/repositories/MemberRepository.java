package com.example.lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lms.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
