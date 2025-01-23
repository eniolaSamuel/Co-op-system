package com.cooperative.system.cooperative_system.services.interfaces;



import com.cooperative.system.cooperative_system.data.models.Loan;
import com.cooperative.system.cooperative_system.dtos.requests.LoanApplicationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.LoanRepaymentRequest;

import java.util.List;
import java.util.UUID;

public interface LoanService {

    Loan applyForLoan(String memberId, LoanApplicationRequest request);

    List<Loan> getMemberLoans(String memberId);

    Loan approveLoan(UUID loanId);
    Loan rejectLoan(UUID loanId);
    Loan getLoanById(UUID loanId);
    List<Loan> getAllLoans();
    Loan repayLoan(UUID loanId, LoanRepaymentRequest request);
    List<Loan.Repayment> getLoanRepayments(UUID loanId);
}


