package goncharenko.GVV.managers.impl;

import goncharenko.GVV.config.BlConfiguration;
import goncharenko.GVV.dal.BankAccountService;
import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.Transaction;
import goncharenko.GVV.managers.BankManager;
import org.junit.Assert;
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
@ContextConfiguration(classes = {BlConfiguration.class})
public class BankManagerImplTest {
    private static Logger LOGGER = LoggerFactory.getLogger(BankManagerImplTest.class);

    @Autowired
    private BankManager bankManager;

    @Autowired
    private BankAccountService bankAccountService;

    // for testGetAccountBalanceByUserName()
    private static String userName = "William";
    private static String password = "William1234";
    private static BigDecimal accountBalance = new BigDecimal(122);

    //for testAddAccountBalance()
    private static String userName2 = "Emily";
    private static String password2 = "Emily1234";
    private static BigDecimal accountBalance2 = new BigDecimal(145);
    private static BigDecimal accountBalanceForAdd = new BigDecimal(50);

    //for testWithdrawMoneyFromAccountBalance
    private static String userName3 = "Caroline";
    private static String password3 = "Caroline1234";
    private static BigDecimal accountBalance3 = new BigDecimal(305);
    private static BigDecimal accountBalanceForWithdrawMoney = new BigDecimal(55);

    //for testTransferMoneyToAnotherUser
    private static String userName4 = "Luis";
    private static String password4 = "Luis1234";
    private static BigDecimal accountBalance4 = new BigDecimal(30);

    private static String userName5 = "Vanessa";
    private static String password5 = "Vanessa1234";
    private static BigDecimal accountBalance5 = new BigDecimal(3000);

    private static BigDecimal accountBalanceForTransfer = new BigDecimal(100);

    //for testReportTransactions()
    private static String userName6 = "Erin";
    private static String password6 = "Erin1234";
    private static BigDecimal accountBalance6 = new BigDecimal(1000);
    private static BigDecimal accountBalanceForAdd2 = new BigDecimal(10);

    //for testGetBankAccountByUserName()
    private static String userName7 = "Melissa";
    private static String password7 = "Melissa34";
    private static BigDecimal accountBalance7 = new BigDecimal(244);

    @Test
    public void testGetAccountBalanceByUserName() throws Exception {

        LOGGER.info("Create new BankAccount");
        BankAccount bankAccount = getBankAccount(userName, password, accountBalance);

        BankAccount savedBankAccount = bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("Get AccountBalance by user name");
        BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(savedBankAccount.getUserName());
        Assert.assertEquals(accountBalance.setScale(2), currentAccountBalance);
    }

    @Test
    public void testAddAccountBalance() throws Exception {
        LOGGER.info("Create new BankAccount");
        BankAccount bankAccount = getBankAccount(userName2, password2, accountBalance2);

        BankAccount savedBankAccount = bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("Add AccountBalance by user name");
        bankManager.addAccountBalance(accountBalanceForAdd, savedBankAccount.getUserName());

        LOGGER.info("Get AccountBalance by user name");
        BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(savedBankAccount.getUserName());
        Assert.assertEquals(accountBalance2.add(accountBalanceForAdd).setScale(2), currentAccountBalance);
    }

    @Test
    public void testWithdrawMoneyFromAccountBalance() throws Exception {
        LOGGER.info("Create new BankAccount");
        BankAccount bankAccount = getBankAccount(userName3, password3, accountBalance3);

        BankAccount savedBankAccount = bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("Add AccountBalance by user name");
        bankManager.withdrawMoneyFromAccountBalance(accountBalanceForWithdrawMoney, savedBankAccount.getUserName());

        LOGGER.info("Get AccountBalance by user name");
        BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(savedBankAccount.getUserName());
        Assert.assertEquals(accountBalance3.subtract(accountBalanceForWithdrawMoney).setScale(2), currentAccountBalance);
    }

    @Test
    public void testTransferMoneyToAnotherUser() throws Exception {
        LOGGER.info("Create new BankAccounts");
        BankAccount bankAccount1 = getBankAccount(userName4, password4, accountBalance4);

        BankAccount savedBankAccount1 = bankAccountService.saveBankAccount(bankAccount1);

        BankAccount bankAccount2 = getBankAccount(userName5, password5, accountBalance5);

        BankAccount savedBankAccount2 = bankAccountService.saveBankAccount(bankAccount2);

        bankManager.transferMoneyToAnotherUser(accountBalanceForTransfer, userName4, userName5);

        LOGGER.info("Get AccountBalance by user name");
        BigDecimal currentAccountBalance1 = bankManager.getAccountBalanceByUserName(savedBankAccount1.getUserName());
        BigDecimal currentAccountBalance2 = bankManager.getAccountBalanceByUserName(savedBankAccount2.getUserName());

        Assert.assertEquals(accountBalance4.subtract(accountBalanceForTransfer).setScale(2), currentAccountBalance1);
        Assert.assertEquals(accountBalance5.add(accountBalanceForTransfer).setScale(2), currentAccountBalance2);
    }

    @Test
    public void testReportTransactions() throws Exception {
        LOGGER.info("Create new BankAccount ");
        BankAccount bankAccount = getBankAccount(userName6, password6, accountBalance6);

        BankAccount savedBankAccount = bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("Add AccountBalance by user name");
        bankManager.addAccountBalance(accountBalanceForAdd2, savedBankAccount.getUserName());

        LOGGER.info("Get transaction by user name");
        List<Transaction> transactions = bankManager.reportTransactions(userName6);
        Assert.assertEquals(accountBalanceForAdd2.setScale(2), transactions.get(0).getAmount());
    }

    @Test
    public void testGetBankAccountByUserName() throws Exception {
        LOGGER.info("Create new BankAccount ");
        BankAccount bankAccount = getBankAccount(userName7, password7, accountBalance7);

        bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("Get BankAccount by user name");
        BankAccount bankAccountCurrent = bankManager.getBankAccountByUserName(userName7);

        Assert.assertEquals(userName7, bankAccountCurrent.getUserName());
        Assert.assertEquals(password7, bankAccountCurrent.getPassword());
    }

    public static BankAccount getBankAccount(String userName, String password, BigDecimal amount) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountBalance(amount);
        bankAccount.setUserName(userName);
        bankAccount.setPassword(password);

        return bankAccount;
    }
}