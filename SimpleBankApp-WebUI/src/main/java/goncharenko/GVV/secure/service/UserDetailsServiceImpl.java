package goncharenko.GVV.secure.service;

import goncharenko.GVV.entity.BankAccount;
import goncharenko.GVV.entity.enums.UserRoleEnum;
import goncharenko.GVV.managers.BankManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BankManager bankManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BankAccount bankAccount = bankManager.getBankAccountByUserName(s);
        // specify the role for the user
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        // on the basis of the data forming the object UserDetails
        // which enables the user to check the entered login and password
        // and only then identify the user
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(bankAccount.getUserName(),
                        bankAccount.getPassword(),
                        roles);

        return userDetails;
    }
}
