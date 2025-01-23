package com.cooperative.system.cooperative_system.services.interfaces;

import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.dtos.requests.MemberRegistrationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.MemberUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface MemberService {
    Member registerMember(MemberRegistrationRequest request);
    Member updateMember(String memberId, MemberUpdateRequest request);
    Member getMemberById(String memberId);
    void deleteMember(String memberId);
    List<Member> getAllMembers();
    Member approveMember(UUID memberId);
    Member suspendMember(UUID memberId);
    List<Member> getPendingMembers();
}



