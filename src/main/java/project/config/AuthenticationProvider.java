package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import project.model.Accounts;
import project.model.Roles;
import project.repository.AccountRepository;
import project.service.AccountService;
import project.service.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object credential = usernamePasswordAuthenticationToken.getCredentials();
        String token = String.valueOf(credential);
        Accounts accounts = accountService.findUserByToken(token);
        Set<GrantedAuthority> grandList = new HashSet<>();
//        for (int i = 0; i < accounts.getRolesList().size(); i++) {
//            grandList.add(new SimpleGrantedAuthority(""));
//        }
        grandList.add(new SimpleGrantedAuthority("Admin"));
        User user = new User(accounts.getEmail(), accounts.getPassword(), grandList);
        return user;
    }

}
