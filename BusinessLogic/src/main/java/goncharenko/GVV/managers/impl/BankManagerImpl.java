package goncharenko.GVV.managers.impl;

import goncharenko.GVV.dal.BankAccountService;
import goncharenko.GVV.dal.TransactionService;
import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.Transaction;
import goncharenko.GVV.managers.BankManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("bankManager")
public class BankManagerImpl implements BankManager {
    private static Logger LOGGER = LoggerFactory.getLogger(BankManagerImpl.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public BigDecimal getAccountBalanceByUserName(String userName) {
        LOGGER.info("Get account balance by userName");
        return bankAccountService.getAccountBalanceByUserName(userName);
    }

    @Override
    public void addAccountBalance(BigDecimal amount, String userName) {
        LOGGER.info("Get current BankAccount");
        BankAccount currentBankAccount = bankAccountService.getBankAccountByUserName(userName);
        BigDecimal currentBalance = currentBankAccount.getAccountBalance();
        BigDecimal forUpdateBalance = currentBalance.add(amount);
        bankAccountService.updateBankAccount(userName, forUpdateBalance);

        LOGGER.info("Create transaction");
        Transaction transaction = new Transaction();
        transaction.setBankAccount(currentBankAccount);
        transaction.setAmount(amount);
        transactionService.saveTransaction(transaction);
    }

    @Override
    public void withdrawMoneyFromAccountBalance(BigDecimal amount, String userName) {
        LOGGER.info("Get current BankAccount");
        BankAccount currentBankAccount = bankAccountService.getBankAccountByUserName(userName);
        BigDecimal currentBalance = currentBankAccount.getAccountBalance();
        BigDecimal forUpdateBalance = currentBalance.subtract(amount);
        bankAccountService.updateBankAccount(userName, forUpdateBalance);

        LOGGER.info("Create transaction");
        Transaction transaction = new Transaction();
        transaction.setBankAccount(currentBankAccount);
        transaction.setAmount(amount.negate());
        transactionService.saveTransaction(transaction);
    }

    @Override
    public void transferMoneyToAnotherUser(BigDecimal amount, String senderUserName, String receiverUserName) {
        LOGGER.info("Get BankAccount sender and receiver");
        BankAccount senderBankAccount = bankAccountService.getBankAccountByUserName(senderUserName);
        BankAccount receiverBankAccount = bankAccountService.getBankAccountByUserName(receiverUserName);
        BigDecimal senderBalance = senderBankAccount.getAccountBalance();
        BigDecimal receiverBalance = receiverBankAccount.getAccountBalance();

        LOGGER.info("Update BankAccount sender and receiver");
        bankAccountService.updateBankAccount(senderUserName, senderBalance.subtract(amount));
        bankAccountService.updateBankAccount(receiverUserName, receiverBalance.add(amount));

        LOGGER.info("Create transaction for sender");
        Transaction senderTransaction = new Transaction();
        senderTransaction.setBankAccount(senderBankAccount);
        senderTransaction.setAmount(amount.negate());
        transactionService.saveTransaction(senderTransaction);

        LOGGER.info("Create transaction for receiver");
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setBankAccount(receiverBankAccount);
        receiverTransaction.setAmount(amount);
        transactionService.saveTransaction(receiverTransaction);
    }

    @Override
    public List<Transaction> reportTransactions(String userName) {
        LOGGER.info("Get all transactions by userName");
        BankAccount bankAccount = bankAccountService.getBankAccountByUserName(userName);

        return transactionService.getAllTransactionByBankAccount(bankAccount);
    }

    @Override
    public BankAccount getBankAccountByUserName(String userName) {
        LOGGER.info("Select BankAccount by userName " + userName);
        BankAccount bankAccount = bankAccountService.getBankAccountByUserName(userName);
        LOGGER.info("BankAccount - " + bankAccount);
        return bankAccount;
    }
}
