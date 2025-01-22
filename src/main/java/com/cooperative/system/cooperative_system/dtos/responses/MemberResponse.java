package com.cooperative.system.cooperative_system.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private String id;
    private String fullname;
    private String email;
    private String department;
    private LocalDateTime createdDate;


}

