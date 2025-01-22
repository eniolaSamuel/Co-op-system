package com.cooperative.system.cooperative_system.dtos.requests;

import com.cooperative.system.cooperative_system.data.models.BankDetails;
import lombok.Data;

@Data
public class MemberUpdateRequest {
    private String fullname;
    private String phoneNumber;
    private String department;
    private String maritalStatus;
    private BankDetails bankDetails;

}
