package com.miftah.core_bank_system.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUserId(UUID userId);

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByCardNumber(String cardNumber);

    boolean existsByAccountNumberAndIdNot(String accountNumber, UUID id);

    boolean existsByCardNumberAndIdNot(String cardNumber, UUID id);
}
