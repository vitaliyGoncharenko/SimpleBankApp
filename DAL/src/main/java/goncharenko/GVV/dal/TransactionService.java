package goncharenko.GVV.dal;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.Transaction;

import java.util.List;

/**
 * Created by Vitaliy on 29.02.2016.
 */
public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionByBankAccount(BankAccount bankAccount);

    void deleteTransaction(Transaction transaction);
}
