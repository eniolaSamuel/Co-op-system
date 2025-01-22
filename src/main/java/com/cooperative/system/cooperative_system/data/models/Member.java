package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private String paystackCustomerId;


}

