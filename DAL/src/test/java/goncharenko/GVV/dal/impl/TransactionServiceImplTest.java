package goncharenko.GVV.dal.impl;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.Transaction;
import goncharenko.GVV.dal.BankAccountService;
import goncharenko.GVV.dal.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context-annotation.xml"})
public class TransactionServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImplTest.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    // for saveBankAccount()
    private String userName = "Vitaliy";
    private String password = "1234";
    private BigDecimal accountBalance = new BigDecimal(10);
    //for testSaveTransaction()
    private static BigDecimal amount = new BigDecimal(108);

    //for getAllTransactionByBankAccount
    private String userName2 = "Oleksandr";
    private String password2 = "123421";
    private BigDecimal accountBalance2 = new BigDecimal(10);
    private static BigDecimal amount2 = new BigDecimal(15);

    @Test
    public void testSaveTransaction() throws Exception {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserName(userName);
        bankAccount.setPassword(password);
        bankAccount.setAccountBalance(accountBalance);

        BankAccount bankAccountSaved = bankAccountService.saveBankAccount(bankAccount);
        LOGGER.info("saved BankAccount successfully");

        Transaction transactionForSave = new Transaction();
        transactionForSave.setBankAccount(bankAccountSaved);
        transactionForSave.setAmount(amount);
        Transaction transactionSaved = transactionService.saveTransaction(transactionForSave);
        LOGGER.info("saved transaction successfully");

        transactionService.deleteTransaction(transactionSaved);
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        List<Transaction> transactions = transactionService.getAllTransactions();
        for (Transaction transaction : transactions) {
            LOGGER.info("Get transaction - " + transaction);
        }
    }

    @Test
    public void getAllTransactionByBankAccount() throws Exception {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserName(userName2);
        bankAccount.setPassword(password2);
        bankAccount.setAccountBalance(accountBalance2);

        BankAccount bankAccountSaved = bankAccountService.saveBankAccount(bankAccount);
        LOGGER.info("saved BankAccount successfully");

        Transaction transactionForSave = new Transaction();
        transactionForSave.setBankAccount(bankAccountSaved);
        transactionForSave.setAmount(amount2);
        Transaction transactionSaved = transactionService.saveTransaction(transactionForSave);
        LOGGER.info("saved transaction successfully");

        List<Transaction> transactions = transactionService.getAllTransactionByBankAccount(bankAccount);
        for (Transaction transaction : transactions) {
            LOGGER.info("Get transaction - " + transaction);
        }

        transactionService.deleteTransaction(transactionSaved);

        bankAccountService.deleteBankAccount(bankAccountSaved);
    }
}