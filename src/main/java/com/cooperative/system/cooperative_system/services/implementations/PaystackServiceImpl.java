package com.cooperative.system.cooperative_system.services.implementations;


import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.data.repositories.MemberRepository;
import com.cooperative.system.cooperative_system.dtos.requests.AddCardRequest;
import com.cooperative.system.cooperative_system.dtos.responses.PaystackTransactionResponse;
import com.cooperative.system.cooperative_system.services.interfaces.PaystackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaystackServiceImpl implements PaystackService {


    private RestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Value("${paystack.secret.key}")
    private String paystackSecretKey;

    @Value("${paystack.base.url}")
    private String paystackBaseUrl;

    @Override
    public String addCard(UUID memberId, AddCardRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + paystackSecretKey);

        Map<String, Object> body = new HashMap<>();
        body.put("email", member.getEmail());
        body.put("card", request);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        PaystackTransactionResponse response = restTemplate.postForObject(
                paystackBaseUrl + "/transaction/initialize",
                entity,
                PaystackTransactionResponse.class
        );

        if (response != null && response.isStatus()) {
            member.setPaystackCustomerId(response.getData().getCustomer().getId());
            memberRepository.save(member);
            return response.getData().getAuthorizationUrl();
        } else {
            throw new RuntimeException("Failed to add card");
        }
    }

    @Override
    public PaystackTransactionResponse initiateDeposit(UUID memberId, BigDecimal amount) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + paystackSecretKey);

        Map<String, Object> body = new HashMap<>();
        body.put("email", member.getEmail());
        body.put("amount", amount.multiply(new BigDecimal("100")).intValue());
        body.put("currency", "NGN");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(
                paystackBaseUrl + "/transaction/initialize",
                entity,
                PaystackTransactionResponse.class
        );
    }

    @Override
    public PaystackTransactionResponse initiateWithdrawal(UUID memberId, BigDecimal amount) {
        // Implement withdrawal logic using Paystack's Transfer API
        // This is a simplified version and may need to be adjusted based on Paystack's actual API
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + paystackSecretKey);

        Map<String, Object> body = new HashMap<>();
        body.put("source", "balance");
        body.put("amount", amount.multiply(new BigDecimal("100")).intValue());
        body.put("recipient", member.getPaystackCustomerId());
        body.put("reason", "Withdrawal");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(
                paystackBaseUrl + "/transfer",
                entity,
                PaystackTransactionResponse.class
        );
    }

    @Override
    public boolean verifyTransaction(String reference) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + paystackSecretKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        PaystackTransactionResponse response = restTemplate.getForObject(
                paystackBaseUrl + "/transaction/verify/" + reference,
                PaystackTransactionResponse.class,
                entity
        );

        return response != null && response.isStatus() && "success".equals(response.getData().getStatus());
    }
}

