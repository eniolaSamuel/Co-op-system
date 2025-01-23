package com.cooperative.system.cooperative_system.data.models;

import com.cooperative.system.cooperative_system.data.models.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Member {
    @Id
    private String id;

    private String fullname;
    private String phoneNumber;
    private String department;
    private String gender;
    private String email;
    private String maritalStatus;

    @Embedded
    private BankDetails bankDetails;

    private String password;


    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private String paystackCustomerId;


}

