package com.example.lms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Member;
import com.example.lms.repositories.MemberRepository;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    
    public Optional<Member> getMember(Long id) {
        return memberRepository.findById(id);
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }
}
