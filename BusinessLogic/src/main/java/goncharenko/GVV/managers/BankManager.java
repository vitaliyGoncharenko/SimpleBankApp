package goncharenko.GVV.managers;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vitaliy on 01.03.2016.
 */
public interface BankManager {
    BigDecimal getAccountBalanceByUserName(String userName);

    void addAccountBalance(BigDecimal amount, String userName);

    void withdrawMoneyFromAccountBalance(BigDecimal amount, String userName);

    void transferMoneyToAnotherUser(BigDecimal amount, String senderUserName, String receiverUserName);

    List<Transaction> reportTransactions(String userName);

    BankAccount getBankAccountByUserName(String userName);
}
