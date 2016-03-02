package goncharenko.GVV.dal.impl;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.dal.BankAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context-annotation.xml"})
public class BankAccountServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImplTest.class);

    @Autowired
    private BankAccountService bankAccountService;

    //for getBankAccountByUserNameTest
    private String userName0 = "Sophia";
    private String password0 = "Sophia12345688";
    private BigDecimal accountBalance0 = new BigDecimal(250);

    //for testGetAccountBalanceByUserName
    private String userName1 = "Harry";
    private String password1 = "12345688";
    private BigDecimal accountBalance1 = new BigDecimal(1020);

    // for testSaveBankAccount()
    private String userName2 = "Alex";
    private String password2 = "123456";
    private BigDecimal accountBalance2 = new BigDecimal(330);

    // for testUpdateBankAccount()
    private String userName3 = "Helen";
    private String password3 = "12345";
    private BigDecimal accountBalance3 = new BigDecimal(1120);

    // for testSaveBankAccount()
    private BigDecimal accountBalanceUpdate = new BigDecimal(555);

    @Test
    public void testGetBankAccountByUserName() {
        LOGGER.info("Create BankAccount");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserName(userName0);
        bankAccount.setPassword(password0);
        bankAccount.setAccountBalance(accountBalance0);

        LOGGER.info("save BankAccount");
        BankAccount bankAccountSaved = bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("get BankAccount by userName");
        BankAccount bankAccountActual = bankAccountService.getBankAccountByUserName(userName0);

        Assert.assertEquals(bankAccountSaved.getUserName(), bankAccountActual.getUserName());
        Assert.assertEquals(bankAccountSaved.getPassword(), bankAccountActual.getPassword());
    }

    @Test
    public void testGetAccountBalanceByUserName() throws Exception {
        LOGGER.info("Create BankAccount");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserName(userName1);
        bankAccount.setPassword(password1);
        bankAccount.setAccountBalance(accountBalance1);

        LOGGER.info("save BankAccount");
        BankAccount bankAccountSaved = bankAccountService.saveBankAccount(bankAccount);

        LOGGER.info("get AccountBalance by userName");
        BigDecimal amount = bankAccountService.getAccountBalanceByUserName(userName1);
        LOGGER.info("Get account balance by userName " + userName1 + ", is - " + amount);

        bankAccountService.deleteBankAccount(bankAccountSaved);
    }

    @Test
    public void testSaveBankAccount() throws Exception {
        LOGGER.info("Create BankAccount");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserName(userName2);
        bankAccount.setPassword(password2);
        bankAccount.setAccountBalance(accountBalance2);

        BankAccount bankAccountSaved = bankAccountService.saveBankAccount(bankAccount);
        LOGGER.info("save BankAccount successfully");
        bankAccountService.deleteBankAccount(bankAccountSaved);
    }

    @Test
    public void testUpdateBankAccount() throws Exception {
        LOGGER.info("Create BankAccount");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserName(userName3);
        bankAccount.setPassword(password3);
        bankAccount.setAccountBalance(accountBalance3);

        BankAccount bankAccountSaved = bankAccountService.saveBankAccount(bankAccount);
        LOGGER.info("save BankAccount successfully");


        bankAccountService.updateBankAccount(bankAccount.getUserName(), accountBalanceUpdate);
        LOGGER.info("Update BankAccount successfully");

        bankAccountService.deleteBankAccount(bankAccountSaved);
    }
}