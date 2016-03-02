package goncharenko.GVV.dal;

import goncharenko.GVV.entity.BankAccount;

import java.math.BigDecimal;

/**
 * Created by Vitaliy on 29.02.2016.
 */
public interface BankAccountService {
    BankAccount getBankAccountByUserName(String userName);

    BigDecimal getAccountBalanceByUserName(String userName);

    BankAccount saveBankAccount(BankAccount bankAccount);

    void updateBankAccount(String username, BigDecimal accountBalance);

    void deleteBankAccount(BankAccount bankAccount);
}
