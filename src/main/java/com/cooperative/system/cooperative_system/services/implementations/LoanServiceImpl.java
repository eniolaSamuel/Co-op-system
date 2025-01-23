package com.cooperative.system.cooperative_system.services.implementations;

import com.cooperative.system.cooperative_system.data.models.Loan;
import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.data.models.enums.LoanStatus;
import com.cooperative.system.cooperative_system.data.repositories.LoanRepository;
import com.cooperative.system.cooperative_system.data.repositories.MemberRepository;
import com.cooperative.system.cooperative_system.dtos.requests.LoanApplicationRequest;
import com.cooperative.system.cooperative_system.dtos.requests.LoanRepaymentRequest;
import com.cooperative.system.cooperative_system.services.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Loan applyForLoan(String memberId, LoanApplicationRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Loan loan = new Loan();
        loan.setMemberId(memberId);
        loan.setLoanDescription(request.getLoanDescription());
        loan.setLoanAmount(request.getLoanAmount());
        loan.setDateRequested(LocalDate.now());
        loan.setLoanDuration(request.getLoanDuration());
        loan.setLoanStatus(LoanStatus.valueOf("PENDING"));

        return loanRepository.save(loan);
    }


    @Override
    public List<Loan> getMemberLoans(String memberId) {
        return loanRepository.findByMemberId(memberId);
    }

    @Override
    public Loan approveLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        loan.setLoanStatus(LoanStatus.valueOf("APPROVED"));
        loan.setDateApproved(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusMonths(loan.getLoanDuration()));
        loan.setRepaymentAmount(loan.getLoanAmount().multiply(BigDecimal.valueOf(1.1)));
        return loanRepository.save(loan);
    }

    @Override
    public Loan rejectLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        loan.setLoanStatus(LoanStatus.valueOf("REJECTED"));
        loan.setDateRejected(LocalDate.now());
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(UUID loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan repayLoan(UUID loanId, LoanRepaymentRequest request) {
        Loan loan = getLoanById(loanId);
        Loan.Repayment repayment = new Loan.Repayment();
        repayment.setDate(LocalDate.now());
        repayment.setAmount(request.getAmount());
        loan.getRepayments().add(repayment);

        BigDecimal totalRepaid = loan.getRepayments().stream()
                .map(Loan.Repayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalRepaid.compareTo(loan.getRepaymentAmount()) >= 0) {
            loan.setLoanStatus(LoanStatus.valueOf("PAID"));
        }

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan.Repayment> getLoanRepayments(UUID loanId) {
        Loan loan = getLoanById(loanId);
        return loan.getRepayments();
    }

}




