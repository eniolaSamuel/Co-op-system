package com.cooperative.system.cooperative_system.controllers;

import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.dtos.requests.MemberRegistrationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.MemberUpdateRequest;
import com.cooperative.system.cooperative_system.services.interfaces.MemberService;
import com.cooperative.system.cooperative_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberRegistrationRequest request) {
        Member registeredMember = memberService.registerMember(request);

        UserDetails userDetails = new User(registeredMember.getEmail(), registeredMember.getPassword(), new ArrayList<>());
        String token = jwtUtil.generateAccessToken(String.valueOf(userDetails));

        Map<String, Object> response = new HashMap<>();
        response.put("member", registeredMember);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable String memberId, @RequestBody MemberUpdateRequest request) {
        Member updatedMember = memberService.updateMember(memberId, request);
        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable String memberId) {
        Member member = memberService.getMemberById(memberId);
        if (member != null) {
            return ResponseEntity.ok(member);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}

