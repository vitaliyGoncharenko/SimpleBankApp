package goncharenko.GVV.dal.impl;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.Transaction;
import goncharenko.GVV.dal.TransactionService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("transactionDao")
public class TransactionServiceImpl implements TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private SessionFactory sessionFactory;

    public Transaction saveTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().saveOrUpdate(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactionByBankAccount(BankAccount bankAccount) {
        Long bankAccountId = bankAccount.getId();
        return sessionFactory.getCurrentSession().createQuery("select t from Transaction t where t.bankAccountId= :bankAccountId").
                setParameter("bankAccountId", bankAccountId).list();
    }

    public List<Transaction> getAllTransactions() {
        return sessionFactory.getCurrentSession().createQuery("from Transaction t").list();
    }

    public void deleteTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().delete(transaction);
        LOGGER.info("Delete transaction successful");
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
