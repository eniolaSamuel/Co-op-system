package com.cooperative.system.cooperative_system.services.implementations;


import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.data.repositories.MemberRepository;
import com.cooperative.system.cooperative_system.dtos.requests.MemberRegistrationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.MemberUpdateRequest;
import com.cooperative.system.cooperative_system.services.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member registerMember(MemberRegistrationRequest request) {
        // Logic for saving a new member with UUID generation
    }

    @Override
    public Member updateMember(String memberId, MemberUpdateRequest request) {
        // Logic for updating member details
    }

    @Override
    public Member getMemberById(String memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }

    @Override
    public void deleteMember(String memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}

