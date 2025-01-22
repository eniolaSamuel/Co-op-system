package com.cooperative.system.cooperative_system.services.impl;

import com.cooperative.system.cooperative_system.data.models.BankDetails;
import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.dtos.requests.MemberRegistrationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.MemberUpdateRequest;
import com.cooperative.system.cooperative_system.data.repositories.MemberRepository;
import com.cooperative.system.cooperative_system.services.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Member registerMember(MemberRegistrationRequest request) {
        Member member = new Member();
        member.setFullname(request.getFullname());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setDepartment(request.getDepartment());
        member.setGender(request.getGender());
        member.setEmail(request.getEmail());
        member.setMaritalStatus(request.getMaritalStatus());
        member.setPassword(passwordEncoder.encode(request.getPassword()));

        BankDetails bankDetails = new BankDetails();
        bankDetails.setAccountName(request.getAccountName());
        bankDetails.setAccountNumber(request.getAccountNumber());
        bankDetails.setBankName(request.getBankName());
        member.setBankDetails(bankDetails);

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(String memberId, MemberUpdateRequest request) {
        Member member = getMemberById(memberId);
        if (member != null) {
            member.setFullname(request.getFullname());
            member.setPhoneNumber(request.getPhoneNumber());
            member.setDepartment(request.getDepartment());
            member.setMaritalStatus(request.getMaritalStatus());

            BankDetails bankDetails = member.getBankDetails();
            if (bankDetails == null) {
                bankDetails = new BankDetails();
            }
            bankDetails.setAccountName(request.getBankDetails().getAccountName());
            bankDetails.setAccountNumber(request.getBankDetails().getAccountNumber());
            bankDetails.setBankName(request.getBankDetails().getBankName());
            member.setBankDetails(bankDetails);

            return memberRepository.save(member);
        }
        return null;
    }

    @Override
    public Member getMemberById(String memberId) {
        return memberRepository.findById(UUID.fromString(memberId)).orElse(null);
    }

    @Override
    public void deleteMember(String memberId) {
        memberRepository.deleteById(UUID.fromString(memberId));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}

