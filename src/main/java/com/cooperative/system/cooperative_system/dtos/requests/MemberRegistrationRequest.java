package com.cooperative.system.cooperative_system.dtos.requests;

import com.cooperative.system.cooperative_system.data.models.BankDetails;
import lombok.Data;

@Data
public class MemberRegistrationRequest {
    private String fullname;
    private String phoneNumber;
    private String department;
    private String gender;
    private String email;
    private String maritalStatus;
    private String accountName;
    private String accountNumber;
    private String bankName;
    private String password;

}

