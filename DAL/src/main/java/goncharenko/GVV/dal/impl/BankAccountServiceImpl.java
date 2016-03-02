package goncharenko.GVV.dal.impl;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.dal.BankAccountService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Transactional
@Repository("bankAccountDao")
public class BankAccountServiceImpl implements BankAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    private SessionFactory sessionFactory;

    @Override
    public BankAccount getBankAccountByUserName(String userName) {
        return (BankAccount) sessionFactory.getCurrentSession().createQuery("select b from BankAccount b where b.userName= :userName").
                setParameter("userName", userName).uniqueResult();
    }

    @Transactional(readOnly = true)
    public BigDecimal getAccountBalanceByUserName(String userName) {
        return (BigDecimal) sessionFactory.getCurrentSession().
                getNamedQuery("BankAccount.findByUserName").
                setParameter("userName", userName).uniqueResult();
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        sessionFactory.getCurrentSession().saveOrUpdate(bankAccount);
        LOGGER.info("BankAccount save with id - " + bankAccount.getId());
        return bankAccount;
    }

    public void updateBankAccount(String userName, BigDecimal accountBalance) {
        sessionFactory.getCurrentSession().createQuery("update BankAccount b set b.accountBalance= :accountBalance where b.userName= :userName").
                setParameter("accountBalance", accountBalance).setParameter("userName", userName).executeUpdate();
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        sessionFactory.getCurrentSession().delete(bankAccount);
        LOGGER.info("Delete BankAccount successful");
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
