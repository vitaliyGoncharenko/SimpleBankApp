package goncharenko.GVV.controller;

import goncharenko.GVV.entity.Transaction;
import goncharenko.GVV.managers.BankManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class BankAccountController {
    private static Logger LOGGER = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    private BankManager bankManager;

    @RequestMapping(value = "/getAccountBalance", method = RequestMethod.GET)
    public String getAccountBalance(Model model) {
        LOGGER.info("Get authentication user");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("Get account balance by user name");
        BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(user.getUsername());
        model.addAttribute("accountBalance", currentAccountBalance);
        return "accountBalance";
    }

    @RequestMapping(value = "/addAccountBalance", method = RequestMethod.GET)
    public String addAccountBalance(Model model) {
        return "addAccountBalance";
    }


    @RequestMapping(value = "/addBalance", method = RequestMethod.GET)
    public String addBalance(HttpServletRequest request, Model model) throws InterruptedException {
        String amount = request.getParameter("amount");
        if (amount.equals("")) {
            String message = "Заполните поле для пополнения счета";
            model.addAttribute("addAccountBalanceMessage", message);
        } else {
            model.addAttribute("amounts", "Пополняем счет на сумму" + amount);

            BigDecimal amountBD = new BigDecimal(amount);
            LOGGER.info("Get authentication user");
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            LOGGER.info("Add account balance");
            bankManager.addAccountBalance(amountBD, user.getUsername());
            String message = "add AccountBalance is complete";
            model.addAttribute("addAccountBalanceMessage", message);

            LOGGER.info("Get current account balance");
            BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(user.getUsername());
            model.addAttribute("accountBalanceAfterAdd", "Ваш текущий баланс : " + currentAccountBalance);
        }
        return "addAccountBalance";
    }

    @RequestMapping(value = "/withdrawAccountBalance", method = RequestMethod.GET)
    public String withdrawAccountBalance(Model model) {
        return "withdrawAccountBalance";
    }

    @RequestMapping(value = "/withdrawBalance", method = RequestMethod.GET)
    public String withdrawBalance(HttpServletRequest request, Model model) {
        String amount = request.getParameter("amount");
        if (amount.equals("")) {
            String message = "Заполните поле для пополнения счета";
            model.addAttribute("withdrawAccountBalanceMessage", message);
        } else {
            model.addAttribute("amounts", "Выводим деньги на сумму " + amount);

            LOGGER.info("Get authentication user");
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            LOGGER.info("Withdraw account balance");
            bankManager.withdrawMoneyFromAccountBalance(new BigDecimal(amount), user.getUsername());
            String message = "withdraw AccountBalance is complete";
            model.addAttribute("withdrawAccountBalanceMessage", message);

            LOGGER.info("Get current account balance");
            BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(user.getUsername());
            model.addAttribute("accountBalanceAfterWithdraw", "Ваш текущий баланс : " + currentAccountBalance);
        }
        return "withdrawAccountBalance";
    }

    @RequestMapping(value = "/reportTransaction", method = RequestMethod.GET)
    public String reportTransaction(Model model) {
        LOGGER.info("Get authentication user");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("Get report transactions");
        List<Transaction> transactions = bankManager.reportTransactions(user.getUsername());
        model.addAttribute("transactions", transactions);

        return "reportTransaction";
    }

    @RequestMapping(value = "/transferMoneyPage", method = RequestMethod.GET)
    public String transferMoneyPage(Model model) {
        return "transferMoney";
    }

    @RequestMapping(value = "/transferMoney", method = RequestMethod.GET)
    public String transferMoney(HttpServletRequest request, Model model) {
        String amount = request.getParameter("amount");
        String nameAccount = request.getParameter("nameAccount");
        if (amount.equals("") && nameAccount.equals("") || amount.equals("") || nameAccount.equals("")) {
            String message = "Заполните поля для ввода";
            model.addAttribute("transferMoneyMessage", message);
        } else {
            model.addAttribute("amounts", "Переводим деньги на сумму " + amount);

            LOGGER.info("Get authentication user");
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            LOGGER.info("Transfer money to another user");
            bankManager.transferMoneyToAnotherUser(new BigDecimal(amount), user.getUsername(), nameAccount);
            String message = "transfer money to another user is complete";
            model.addAttribute("transferMoneyMessage", message);

            LOGGER.info("Get current account balance");
            BigDecimal currentAccountBalance = bankManager.getAccountBalanceByUserName(user.getUsername());
            model.addAttribute("accountBalanceAfterTransfer", "Ваш текущий баланс : " + currentAccountBalance);
        }
        return "transferMoney";
    }
}
