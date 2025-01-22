package com.cooperative.system.cooperative_system.dtos.requests;

import lombok.Data;

@Data
public class MemberUpdateRequest {
    private String fullname;
    private String phoneNumber;
    private String department;
    private String maritalStatus;


}
