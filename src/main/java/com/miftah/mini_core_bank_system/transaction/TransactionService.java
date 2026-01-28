package com.miftah.mini_core_bank_system.transaction;

import com.miftah.mini_core_bank_system.user.User;

public interface TransactionService {

    TransactionResponse createTransaction(User user, TransactionRequest request);
}
