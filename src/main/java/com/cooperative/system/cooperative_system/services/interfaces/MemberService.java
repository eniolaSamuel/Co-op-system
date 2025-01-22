package com.cooperative.system.cooperative_system.services.interfaces;

import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.dtos.requests.MemberRegistrationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.MemberUpdateRequest;

import java.util.List;

public interface MemberService {
    Member registerMember(MemberRegistrationRequest request);
    Member updateMember(String memberId, MemberUpdateRequest request);
    Member getMemberById(String memberId);
    void deleteMember(String memberId);
    List<Member> getAllMembers();
}

